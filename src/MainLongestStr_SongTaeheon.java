import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainLongestStr_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int L;
    static String input;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void solve() {

        // 접미사 문자열은 시작위치 index(Integer) 로 표현한다. (convertSuffixString(int value): String = return input.substring(value))
        Integer[] sortedSuffixArr = getSortedSuffixArr(input);

        int result = 0;
        for (int i = 1; i < input.length(); i++) {
            result = Math.max(result, findDuplicatedCount(sortedSuffixArr[i], sortedSuffixArr[i - 1]));
        }
        System.out.println(result);
    }

    private static int findDuplicatedCount(Integer index1, Integer index2) {
        int result = 0;
        while (index1 < input.length() && index2 < input.length()) {
            if (input.charAt(index1) != input.charAt(index2)) break;
            result += 1;
            index1 += 1;
            index2 += 1;
        }
        return result;
    }

    private static void printArr(Integer[] sortedSuffixArr) {
        for (int i = 0; i < sortedSuffixArr.length; i++) {
            System.out.println(i + " : " + sortedSuffixArr[i]);
        }
    }

    // group 배열을 통해 SortedSuffix 배열을 만든다.
    private static Integer[] getSortedSuffixArr(String input) {
        int len = input.length();
        Integer[] sortedSuffixArr = new Integer[len];
        int[] group = new int[len + 1];

        initGroupArr(input, group);
        initSortedSuffixArr(len, sortedSuffixArr);

        int t = 1; // 2배씩 올리면서 비교!

        while(t < len) {
            // 1. t개 문자열을 기준으로 suffix str 정렬
            SuffixComparator comparator = new SuffixComparator(t, group);
            Arrays.sort(sortedSuffixArr, comparator);

            // 2. t를 2배로 변경
            t *= 2;

            // 3. t개 문자열을 기준으로 group 재설정
            int[] newGroup = new int[len + 1];
            newGroup[len] = -1;

            newGroup[sortedSuffixArr[0]] = 0; // 정렬 기준 제일 먼저 오는 suffix 의 group 을 0으로 지정
            for (int i = 1; i < len; i++) {
                if (comparator.compare(sortedSuffixArr[i], sortedSuffixArr[i-1]) > 0) {
                    newGroup[sortedSuffixArr[i]] = newGroup[sortedSuffixArr[i-1]] + 1;
                } else {
                    newGroup[sortedSuffixArr[i]] = newGroup[sortedSuffixArr[i-1]];
                }
            }
            group = newGroup;
        }

        return sortedSuffixArr;
    }

    private static void initSortedSuffixArr(int len, Integer[] sortedSuffixArr) {
        for (int i = 0; i < len; i++) {
            sortedSuffixArr[i] = i; // 일단 정렬 없이 그대로!
        }
    }

    private static void initGroupArr(String input, int[] group) {
        int len = input.length();
        for (int i = 0; i < len; i++) {
            group[i] = input.charAt(i) - 'a'; // group 의 초기값은 suffix 의 첫 번째 문자열을 기준으로 나눈다.
        }
        group[len] = -1; // group[len]은 -1을 넣는다. 문자열이 없다는 의미! 문자열이 있는 것과 없는 것을 비교할 때 최우선 순위를 주기 위해서
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        input = st2.nextToken();
    }


    private static class SuffixComparator implements Comparator<Integer> {

        private final int term;
        private final int[] group;

        SuffixComparator(int term, int[] group) {
            this.term = term;
            this.group = group;
        }

        @Override
        public int compare(Integer suffix1, Integer suffix2) {

            if (group[suffix1] != group[suffix2]) return group[suffix1] - group[suffix2];

            return group[suffix1 + term] - group[suffix2 + term];
        }
    }
}
