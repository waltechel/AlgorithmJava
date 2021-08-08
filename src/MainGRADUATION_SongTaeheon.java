import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainGRADUATION_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int totalClassCount, requiredClassCount, maxSemesterCount, maxClassCountPerSemester;

    static Map<Integer, Integer> openedClassBitForSemester = new HashMap<>();
    static Map<Integer, Integer> prerequisitesBitForClass = new HashMap<>();

    static int maxClassBit; // bit : 과목수 ex. 5인 경우 11111

    final static int INF = 11; // 최대 학기 수보다 큰 수

    // memoization
    static int[][] cache;
    static boolean[][] cacheSaved;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();

            initValueFromInput();


            solveProblem();
        }
    }

    private static void solveProblem() {
        int result = selectSubject(0, 0);
        if (result == INF) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    private static int selectSubject(int semester, int takenClassBit) {

        if (getBitCount(takenClassBit) == requiredClassCount) {
            return 0;
        }

        if (semester >= maxSemesterCount) {
            return INF;
        }

        // 4가지 조건
        // 1. 한 학기 최대 과목 수 체크 (maxClassCountPerSemester) boolean classCountPerSemesterCheck = getBitCount(newClassesBit) <= maxClassCountPerSemester
        // 2. 선수 과목 체크 (들을 수 있는 과목이 맞는지 확인) boolean prerequisiteCheck = (prerequisitesBitForClass.get(classNum) | takenClassBit) == takenClassBit
        // 3. 수강을 안했던 과목이어야함 int bit = maxClassesBit & ~takenClassBit
        // 4. 이번 학기에 열렸어야함. int bit = openedClassBitForSemester.get(semester)

        if (cacheSaved[semester][takenClassBit]) {
            return cache[semester][takenClassBit];
        }

        int minSemester = INF;

        int openedClassBit = openedClassBitForSemester.get(semester);
        int notTakenClassBit = (maxClassBit & ~takenClassBit);
        int possibleClassBit = (notTakenClassBit & openedClassBit);

        for (int newClassBit = possibleClassBit; newClassBit > 0; newClassBit = possibleClassBit & (newClassBit - 1)) {
            boolean classCountPerSemesterCheck = getBitCount(newClassBit) <= maxClassCountPerSemester;
            boolean prerequisiteCheck = prerequisiteCheck(newClassBit, takenClassBit);

            if (classCountPerSemesterCheck && prerequisiteCheck) {
                int nextTakenClassBit = (takenClassBit | newClassBit);
                int result = selectSubject(semester + 1, nextTakenClassBit) + 1;

                minSemester = Math.min(minSemester, result);
            }
        }

        // 휴학하는 경우
        int result = selectSubject(semester + 1, takenClassBit);
        minSemester = Math.min(minSemester, result);

        cacheSaved[semester][takenClassBit] = true;
        cache[semester][takenClassBit] = minSemester;

        return minSemester;
    }

    private static boolean prerequisiteCheck(int newClassBit, int takenClassBit) {

        for(int classNum = 0; classNum < totalClassCount; classNum++) {
            if (isBitOn(newClassBit, classNum)) {
                boolean prerequisiteCheck = (prerequisitesBitForClass.get(classNum) | takenClassBit) == takenClassBit;
                if (!prerequisiteCheck) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isBitOn(int bit, int pos) {
        return (bit & (1 << pos)) != 0;
    }

    private static int getBitCount(int bit) {

        int bitCount = 0;

        while (bit != 0) {
            if ((bit&1) == 1) {
                bitCount += 1;
            }
            bit = bit >> 1;
        }

        return bitCount;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void initValueFromInput() {
        maxClassBit = (1<<totalClassCount) - 1;
        cache = new int[maxSemesterCount][maxClassBit];
        cacheSaved = new boolean[maxSemesterCount][maxClassBit];
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        totalClassCount = Integer.parseInt(st.nextToken()); // N
        requiredClassCount = Integer.parseInt(st.nextToken()); // K
        maxSemesterCount = Integer.parseInt(st.nextToken()); // M
        maxClassCountPerSemester = Integer.parseInt(st.nextToken()); // L

        prerequisitesBitForClass = inputNLinesToBitMap(totalClassCount);
        openedClassBitForSemester = inputNLinesToBitMap(maxSemesterCount);

//        printInput();
    }



    /**
     * inputNLinesToMap
     *
     * example1
     * input      key value
     * 0        -> 0, 0 (0000)
     * 1 0      -> 1, 1 (0001)
     * 3 0 1 3  -> 2, 11 (1011)
     * 0        -> 3, 0 (0000)
     *
     * example2
     * input      key value
     * 4 0 1 2 3 -> 0, 15 (1111)
     * 4 0 1 2 3 -> 1, 15 (1111)
     * 3 0 1 3   -> 2, 11 (1011)
     * 4 0 1 2 3 -> 3, 15 (1111)
     * */
    private static Map<Integer, Integer> inputNLinesToBitMap(int lineCount) throws IOException {

        Map<Integer, Integer> result = new HashMap<>();

        for (int index = 0; index < lineCount; index++) {
            StringTokenizer semesterSt = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(semesterSt.nextToken());

            int binaryStatus = 0; // 0000
            for (int i = 0; i < count; i++) {
                int id = Integer.parseInt(semesterSt.nextToken());
                binaryStatus = binaryStatus | (1<<id);
            }

//            printBit(binaryStatus);
            result.put(index, binaryStatus);
        }

        return result;
    }

    private static void printInput() {
        System.out.println("N = " + totalClassCount);
        System.out.println("K = " + requiredClassCount);
        System.out.println("M = " + maxSemesterCount);
        System.out.println("L = " + maxClassCountPerSemester);
        System.out.println("prerequisitesForSubject = " + prerequisitesBitForClass);
        System.out.println("openedSubjectsForSemester = " + openedClassBitForSemester);
    }

    private static void printBit(int input) {
        StringBuilder sb = new StringBuilder();

        int num = input;

        while (num != 0) {
            if ((num&1) == 1) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            num = num >> 1;
        }

        System.out.println("num : " + input + ", bit : " + sb.reverse());
    }




}
