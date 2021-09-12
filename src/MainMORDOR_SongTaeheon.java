import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainMORDOR_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MAX_VALUE = 20_001;
    static final int MIN_VALUE = -1;


    static int N, Q;
    static int[] height;
    static int[] startPos;
    static int[] endPos;

    static int[] minRange;
    static int[] maxRange;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {
        initSegmentTree();

        printArr(minRange);

        for (int i = 0; i < Q; i++) {
            int start = startPos[i];
            int end = endPos[i];

            int min = findMinValueBetween(1, start, end, 0, N-1);
            int max = findMaxValueBetween(1, start, end, 0, N-1);

            System.out.println(max-min);
        }
    }

    private static void printArr(int[] minRange) {
        for (int i : minRange) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int findMinValueBetween(int nodeNum, int start, int end, int nodeRangeStart, int nodeRangeEnd) {

        // node의 범위를 모두 포함하는 경우
        if (start <= nodeRangeStart && end >= nodeRangeEnd) {
            return minRange[nodeNum];
        }

        // 겹치는 범위가 없는 경우
        if (start > nodeRangeEnd || end < nodeRangeStart) {
            return MAX_VALUE;
        }

        int nodeRangeMid = (nodeRangeStart + nodeRangeEnd) / 2;
        int leftMin = findMinValueBetween(nodeNum * 2, start, end, nodeRangeStart, nodeRangeMid);
        int rightMin = findMinValueBetween(nodeNum * 2 + 1, start, end, nodeRangeMid + 1, nodeRangeEnd);

        return Math.min(leftMin, rightMin);
    }

    private static int findMaxValueBetween(int nodeNum, int start, int end, int nodeRangeStart, int nodeRangeEnd) {

        // node의 범위를 모두 포함하는 경우
        if (start <= nodeRangeStart && end >= nodeRangeEnd) {
            return maxRange[nodeNum];
        }

        // 겹치는 범위가 없는 경우
        if (start > nodeRangeEnd || end < nodeRangeStart) {
            return MIN_VALUE;
        }

        int nodeRangeMid = (nodeRangeStart + nodeRangeEnd) / 2;
        int leftMax = findMaxValueBetween(nodeNum * 2, start, end, nodeRangeStart, nodeRangeMid);
        int rightMax = findMaxValueBetween(nodeNum * 2 + 1, start, end, nodeRangeMid + 1, nodeRangeEnd);

        return Math.max(leftMax, rightMax);
    }

    private static void initSegmentTree() {
        initMinSegmentTree();
        initMaxSegmentTree();
    }


    private static void initMinSegmentTree() {

        minRange = new int[N*4];
        Arrays.fill(minRange, MAX_VALUE);

        initMinRange(1,0, N-1);
    }

    private static int initMinRange(int nodeNum, int start, int end) {

        if (start == end) {
            minRange[nodeNum] = height[start];
            return height[start];
        }

        int mid = (start + end) / 2;
        int leftMin = initMinRange(nodeNum * 2, start, mid);
        int rightMin = initMinRange(nodeNum * 2 + 1, mid + 1, end);

        minRange[nodeNum] = Math.min(leftMin, rightMin);

        return minRange[nodeNum];
    }

    private static void initMaxSegmentTree() {
        maxRange = new int[N * 4];
        Arrays.fill(maxRange, MIN_VALUE);

        initMaxRange(1,0, N-1);
    }

    private static int initMaxRange(int nodeNum, int start, int end) {
        if (start == end) {
            maxRange[nodeNum] = height[start];
            return height[start];
        }

        int mid = (start + end) / 2;
        int leftMax = initMaxRange(nodeNum * 2, start, mid);
        int rightMax = initMaxRange(nodeNum * 2 + 1, mid + 1, end);

        maxRange[nodeNum] = Math.max(leftMax, rightMax);

        return maxRange[nodeNum];
    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N
        Q = Integer.parseInt(st.nextToken()); // Q

        height = new int[N];

        StringTokenizer stHeight = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(stHeight.nextToken());
        }

        startPos = new int[Q];
        endPos = new int[Q];

        for (int i = 0; i < Q; i++) {
            StringTokenizer stPos = new StringTokenizer(br.readLine());
            startPos[i] = Integer.parseInt(stPos.nextToken());
            endPos[i] = Integer.parseInt(stPos.nextToken());
        }
    }
}
