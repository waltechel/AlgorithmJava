import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainCHSRITMAS_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int boxCount, childCount;
    static int[] box;

    static int[] accBox;

    static int lastSelectedBox;
    static boolean[] possibleSubsetWithEnd; // 0 ~ index까지만 박스를 선택할 때, 선택가능한 subset

    static int resultDivisor = 20091101;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            init();
            input();

            solveProblem();
        }
    }


    private static void solveProblem() {

        int result1 = 0;
        int result2 = 0;

        // accumulate box 계산
        accBox[0] = box[0];
        for (int i = 1; i < boxCount; i++) {
            accBox[i] = accBox[i-1] + box[i];
        }

        // box start ~ end 선택
        for (int end = 0; end < boxCount; end++) {

            for (int start = end; start >= 0; start--) { // 0 ~ end

                int startMinusOne = start -1;

                // start ~ end까지의 sumOfDull을 찾는다.
                int accBoxOfBeforeStart = 0;
                if (startMinusOne >= 0) {
                    accBoxOfBeforeStart = accBox[startMinusOne];
                }

                int sumOfDull = accBox[end] - accBoxOfBeforeStart; // accBox[end] - accBox[start - 1]

                // 선택 가능한지 확인한다.
                if (sumOfDull % childCount == 0) {

//                    System.out.println(start + "~" + end + " : sumOfDull - " + sumOfDull);
                    result1 = (result1 + 1) % resultDivisor;

                    // lastSelectedBox가 start보다 작으면 선택 가능! -> 2번째 질문을 위해!
                    if (lastSelectedBox < start) {
                        possibleSubsetWithEnd[end] = true;
                        lastSelectedBox = end;
                    }
                }
            }
        }

        // 2번 째 질문의 답을 찾는다.
        for (int end = 0; end < boxCount; end++) {
            if (possibleSubsetWithEnd[end]) {
                result2 += 1;
            }
        }

        System.out.println(result1 + " " + result2);
    }

    private static void printAcc() {
        for (int i = 0 ; i < boxCount; i++) {
            System.out.print(accBox[i] + " ");
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
        for (int i = 0; i < boxCount; i++) {
            box[i] = Integer.parseInt(stBox.nextToken());
        }
    }

    private static void init() {
        box = new int[100000];
        accBox = new int[100000];
        lastSelectedBox = -1;
        possibleSubsetWithEnd = new boolean[100000];
    }

}
