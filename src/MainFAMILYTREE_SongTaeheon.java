import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainFAMILYTREE_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int MAX_VALUE = 100_001;

    static int N, Q;
    static int[] input;
    static int[] a;
    static int[] b;

    static int[] trip; // index = 순서, value = 노드번호(serial)
    static int[] serial; // index = 노드번호(input) value = 노드번호(serial)
    static int[] serial2input; // index = 노드번호(serial) value = 노드번호(input)
    static int[] firstTripPos; // index = 노드번호(serial) value = trip에서 몇 번째에 처음으로 나타났는지!
    static int[] depth; // index = 노드번호(serial), value = depth
    static int serialNum;
    static int tripOrder;

    static int[] tripSegmentTree;


    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() throws IOException {

        init();

        // dfs 돌면서 노드 번호(serial)를 다시 매긴다. (parent의 node번호가 항상 작도록)
        dfs(0, 0);

        // trip을 기준으로 segment tree를 만든다.
        createSegmentTree();

        // a, b를 trip에서 가장 먼저 나온 위치를 찾고, 그 사이 범위의 가장 작은 노드(serial)를 찾는다.
        for (int i = 0; i < Q; i++) {
            int serialA = serial[a[i]];
            int serialB = serial[b[i]];

            int start = firstTripPos[serialA];
            int end = firstTripPos[serialB];

            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }

            // 두 위치 사이에 가장 작은 serial Num가 공통 조상!
            int commonAncestorSerial = findMinTripBetween(1, start, end, 0, 2*N-2);

            int result = depth[serialA] + depth[serialB] - depth[commonAncestorSerial] * 2;
            bw.write(result + "\n");
        }
        bw.flush();
    }

    private static void printArr(int[] ints) {
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int findMinTripBetween(int nodeNum, int start, int end, int nodeRangeStart, int nodeRangeEnd) {

        // 공집합.
        if (start > nodeRangeEnd || end < nodeRangeStart) {
            return MAX_VALUE;
        }

        //포함
        if (start <= nodeRangeStart && end >= nodeRangeEnd) {
            return tripSegmentTree[nodeNum];
        }

        int nodeRangeMid = (nodeRangeStart + nodeRangeEnd) / 2;
        int leftMin = findMinTripBetween(nodeNum * 2, start, end, nodeRangeStart, nodeRangeMid);
        int rightMin = findMinTripBetween(nodeNum * 2 + 1, start, end, nodeRangeMid + 1, nodeRangeEnd);

        return Math.min(leftMin, rightMin);
    }

    private static void createSegmentTree() {
        tripSegmentTree = new int[(2 * N - 1) * 4];

        Arrays.fill(tripSegmentTree, MAX_VALUE); // 100_001은 nodeNum가 넘을 수 없는 값

        initSegmentTree(1, 0, 2*N-2);
    }

    private static int initSegmentTree(int nodeNum, int start, int end) {

        if (start == end) {
            tripSegmentTree[nodeNum] = trip[start];
            return tripSegmentTree[nodeNum];
        }

        int mid = (start + end) / 2;
        int leftMin = initSegmentTree(nodeNum * 2, start, mid);
        int rightMin = initSegmentTree(nodeNum * 2 + 1, mid + 1, end);

        tripSegmentTree[nodeNum] = Math.min(leftMin, rightMin);
        return tripSegmentTree[nodeNum];
    }

    private static void init() {
        trip = new int[2 * N - 1];
        serial = new int[N];
        serial2input = new int[N];
        firstTripPos = new int[N];
        depth = new int[N];
        tripOrder = 0;
        serialNum = 0;
    }

    private static void dfs(final int inputNow, final int depthNum) {
        serial[inputNow] = serialNum;
        trip[tripOrder] = serial[inputNow];
        firstTripPos[serial[inputNow]] = tripOrder;
        depth[serial[inputNow]] = depthNum;

        tripOrder += 1;
        serialNum += 1;

        for (int i = 0; i < N; i++) {
            if (input[i] == inputNow) { // 아들인 경우
                dfs(i, depthNum + 1);
                trip[tripOrder++] = serial[inputNow];
            }
        }
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N
        Q = Integer.parseInt(st.nextToken()); // Q

        input = new int[N + 1];
        input[0] = -1;

        StringTokenizer stInput = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            input[i] = Integer.parseInt(stInput.nextToken());
        }

        a = new int[Q];
        b = new int[Q];

        for (int i = 0; i < Q; i++) {
            StringTokenizer stPos = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(stPos.nextToken());
            b[i] = Integer.parseInt(stPos.nextToken());
        }
    }
}
