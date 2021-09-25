import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainWORDCHAIN_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String[] input;
    static int[][] graph;
    static int[] outDegree;
    static int[] inDegree;
    static List<Integer> endResult;
    static List<Integer> result; // prev가 없는 경우 -1
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            makeGraph();

            if (!checkEuler()) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            int start = findStart();
            findEuler(start);

            if (result.size() - 1 != N) {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            printResult();
        }

    }

    private static int findStart() {

        for (int i = 0; i < 26; i++) {
            if (outDegree[i] == inDegree[i] + 1) return i;
        }

        for (int i = 0; i < 26; i++) {
            if (outDegree[i] == inDegree[i] && outDegree[i] > 0) return i;
        }

        System.out.println("findStart wrong");
        return 0;
    }

    private static void makeGraph() {
        for (String str: input) {
            int start = str.charAt(0) - 'a';
            int end = str.charAt(str.length() - 1) - 'a';

            graph[start][end] += 1;
            outDegree[start] += 1;
            inDegree[end] += 1;
        }
    }

    private static boolean checkEuler() {

        int plus = 0, minus = 0;

        for (int i = 0; i < 26; i++) {
            int delta = outDegree[i] - inDegree[i];

            if (delta < -1 || delta > 1) return false;
            if (delta == 1) plus += 1;
            if (delta == -1) minus += 1;
        }

        return (plus == 1 && minus == 1) || (plus == 0 && minus == 0);
    }

    private static void findEuler(int now) {

        for (int i = 0; i < 26; i++) {
            while (graph[now][i] > 0) {
                graph[now][i] -= 1;
                findEuler(i);
            }
        }

        result.add(now);
    }

    private static void printResult() {
        Collections.reverse(result);

        for (int i = 1; i < result.size(); i++) {
            String r = findInputStr(result.get(i - 1), result.get(i));
            System.out.print(r);
            if (i != result.size() -1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static String findInputStr(Integer start, Integer end) {

        char startChar = (char) (start + 'a');
        char endChar = (char) (end + 'a');

        for (int i = 0; i < N; i++) {
            if (isUsed[i]) continue;

            if (input[i].charAt(0) == startChar && input[i].charAt(input[i].length() - 1) == endChar) {
                isUsed[i] = true;
                return input[i];
            }
        }

        return "something wrong";
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        input = new String[N];
        graph = new int[26][26];
        outDegree = new int[26];
        inDegree = new int[26];
        result = new ArrayList<>();
        isUsed = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            input[i] = st1.nextToken();
        }
    }
}