import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainBISHOPS_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static char[][] board;

    // 0 : 왼쪽 아래 대각선, 1: 오른쪽 아래 대각선
    static final int LEFT_DOWN_DIAGONAL = 0;
    static final int RIGHT_DOWN_DIAGONAL = 1;
    static int[] dCol = new int[]{-1, 1};
    static int[] dRow = new int[]{1, 1};

    static int[][][] id = new int[2][8][8]; // dir, row, col
    static int[] count = new int[2];
    static boolean[][] adj = new boolean[64][64]; // row, col

    // bipartite Match
    static int groupCountA, groupCountB;
    static int[] matchA;
    static int[] matchB;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            init();
            solve();
        }
    }

    private static void init() {

        // id[][][] -1로 세팅
        for (int dir = 0; dir < id.length; dir++) {
            for (int i = 0; i < id[dir].length; i++) {
                for (int j = 0; j < id[dir][i].length; j++) {
                    id[dir][i][j] = -1;
                }
            }
        }

        // count
        count[LEFT_DOWN_DIAGONAL] = 0;
        count[RIGHT_DOWN_DIAGONAL] = 0;

        // adj
        for (int i = 0; i < adj.length; i++) {
            Arrays.fill(adj[i], false);
        }
    }

    private static void solve() {
        makeIdArr();
        makeBiGraph();
        int result = bipartiteMatch();
        System.out.println(result);
    }

    private static void makeIdArr() {
        for (int dir = 0; dir < 2; dir++) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (board[row][col] == '.' && id[dir][row][col] == -1) {
                        setIdAt(dir, row, col);
                    }
                }
            }
        }
    }

    private static void setIdAt(int dir, int row, int col) {
        int nextRow = row;
        int nextCol = col;

        while (checkBound(nextRow, nextCol) && board[nextRow][nextCol] == '.') {

            id[dir][nextRow][nextCol] = count[dir];
            nextRow += dRow[dir];
            nextCol += dCol[dir];
        }
        count[dir] += 1;
    }

    private static boolean checkBound(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            return false;
        }
        return true;
    }

    private static void makeBiGraph() {

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == '.') {
                    int groupLeftDown = id[LEFT_DOWN_DIAGONAL][row][col];
                    int groupRightDown = id[RIGHT_DOWN_DIAGONAL][row][col];
                    adj[groupLeftDown][groupRightDown] = true;
                }
            }
        }
    }

    private static int bipartiteMatch() {
        groupCountA = count[LEFT_DOWN_DIAGONAL];
        groupCountB = count[RIGHT_DOWN_DIAGONAL];

        matchA = new int[groupCountA];
        matchB = new int[groupCountB];
        Arrays.fill(matchA, -1);
        Arrays.fill(matchB, -1);

        int size = 0;
        for (int start = 0; start < groupCountA; start++) {
            visited = new boolean[groupCountA];
            if (dfs(start)) {
                size += 1;
            }
        }

        return size;
    }

    // a가 groupB 중 연결할 b를 찾았으면 true
    private static boolean dfs(int a) {
        if (visited[a]) return false; // visited는 한 쌍 정할 때마다 초기화됨.. 서로 내놔 하지 않도록 방어.
        visited[a] = true;

        for (int b = 0; b < groupCountB; b++) {
            if (!adj[a][b]) continue;
            if (matchB[b] == -1 || dfs(matchB[b])) { // b가 매치된게 없거나, b와 match된 a가 다른 거랑 매칭할 수 있으면
                matchA[a] = b;
                matchB[b] = a;
                return true;
            }
        }
        return false;
    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 선수 수

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            String line = st2.nextToken();

            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }
    }
}
