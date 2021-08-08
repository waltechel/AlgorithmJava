import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainCHSRITMAS_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int boxCount, childCount;
    static int[] box;

    static int[] accBoxRemainder; // 사실은 나머지만 저장

    static int lastSelectedBox;
    static boolean[] possibleSubsetWithEnd; // 0 ~ index까지만 박스를 선택할 때, 선택가능한 subset

    static int resultDivisor = 20091101;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            init();
            input();

            makeAccBox();

            int result1 = solveProblem1();
            int result2 = solveProblem2();
            System.out.println(result1 + " " + result2);
        }
    }

    private static void makeAccBox() {
        // accumulate box 의 나머지
        accBoxRemainder[0] = 0;
        for (int i = 1; i <= boxCount; i++) {
            accBoxRemainder[i] = (accBoxRemainder[i - 1] + box[i]) % childCount;
        }
    }

    private static int solveProblem1() {

        int result = 0;

        //나머지 별 개수를 구한다.
        int[] count = new int[childCount];
        for (int i = 0; i < boxCount + 1; i++) { // i = 0인 경우는
            count[accBoxRemainder[i]]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 1) {
                // start - 1까지의 합과 end까지의 합을 뺐을 때, 나머지가 나눠떨지면 선택 가능. -> start-1까지의 합의 나머지와 end까지의 합의 나머지가 같으면 가능!
                // 같은게 2개인 경우는 항상 1가지 경우만 나옴.
                // 같은게 3개 이상인 경우는 nC2 (n개 중에 2개를 선택하는 경우와 같음.)
                result = (result + (count[i] * (count[i] - 1)) / 2) % resultDivisor;
            }
        }

        return result;
    }


    private static int solveProblem2() {

        int[] subsetCountUntil = new int[boxCount + 1];
        int[] prevRemainderPosition = new int[childCount + 1];
        boolean[] prevRemainderExist = new boolean[childCount + 1];

        for (int until = 0; until < boxCount + 1; until++) {

            // until까지만 봤을 때, 겹치지 않는 subset 개수 찾기
            if (until == 0) {
                subsetCountUntil[until] = 0;
            } else {
                subsetCountUntil[until] = subsetCountUntil[until - 1];
            }

            // accBoxRemainder의 값이 같은 걸 찾는다. (그 위치 + 1 또는 그냥 그대로)
            if (prevRemainderExist[accBoxRemainder[until]]) {
                int loc = prevRemainderPosition[accBoxRemainder[until]];
                subsetCountUntil[until] = Math.max(subsetCountUntil[until], subsetCountUntil[loc] + 1);
            }

            prevRemainderPosition[accBoxRemainder[until]] = until; // remainder의 위치 기억
            prevRemainderExist[accBoxRemainder[until]] = true;
        }

        return subsetCountUntil[boxCount];
    }


    private static void printAcc() {
        for (int i = 0 ; i < boxCount; i++) {
            System.out.print(accBoxRemainder[i] + " ");
        }
        System.out.println();
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer stNumber = new StringTokenizer(br.readLine());
        boxCount = Integer.parseInt(stNumber.nextToken()); // N
        childCount = Integer.parseInt(stNumber.nextToken()); // K

        StringTokenizer stBox = new StringTokenizer(br.readLine());

        box[0] = 0;
        for (int i = 1; i <= boxCount; i++) {
            box[i] = Integer.parseInt(stBox.nextToken());
        }
    }

    private static void init() {
        box = new int[100001];
        accBoxRemainder = new int[100001];
        lastSelectedBox = -1;
        possibleSubsetWithEnd = new boolean[100001];
    }

    private static void printArr(int[] count) {
        for (int i = 0; i < count.length; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();
    }


}
