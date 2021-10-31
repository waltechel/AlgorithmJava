import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainMATCHFIX_SongTaeheon {
    private static final int INF = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] wins = new int[20];
    static int[][] matches = new int[200][2];

    static int[][] capacity = new int[222][222]; // 경기 수 + 선수 수 + 2 (source, sink 1개 씩)
    static int[][] flow = new int[222][222]; // 경기 수 + 선수 수 + 2 (source, sink 1개 씩)

    static int flowResult = 0;


    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {

        // init capacity
        init();

        int possibleMatches = 0;
        for (int i = 0; i < M; i++) {
            if (matches[i][0] == 0 || matches[i][1] == 0) {
                possibleMatches ++;
            }
        }

        for (int i = wins[0]; i <= wins[0] + possibleMatches; i++) {
            if (canWinWith(i)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    // 유량 그래프를 그린다.
    // 0 : source
    // 1 : sink
    // 2 ~ 2+m-1 : 경기 node
    // 2+m ~ 2 + m + n : 선수 번호
    private static void init() {

        flowResult = 0;

        for (int i = 0; i < capacity.length; i++) {
            Arrays.fill(capacity[i], 0);
        }

        for (int i = 0; i < flow.length; i++) {
            Arrays.fill(flow[i], 0);
        }

        for (int i = 0; i < M; i++) {
            // source -> 경기
            capacity[0][2 + i] = 1;

            // 경기 -> 선수
            capacity[2 + i][2 + M + matches[i][0]] = 1; // i번 경기의 첫 번째 선수
            capacity[2 + i][2 + M + matches[i][1]] = 1; // i번 경기의 두 번째 선수
        }

        // 선수 -> sink
        // 이 부분은 totalWins에 따라 다르기 때문에 canWinWith로 이동.
    }

    private static boolean canWinWith(int totalWins) {

        // totalWins보다 이미 많이 이긴 선수가 있으면 return false
        for (int i = 1; i < N; i++) {
            if (wins[i] > totalWins) return false;
        }

        // 선수 -> sink 연결
        capacity[2 + M][1] = totalWins - wins[0];
        if (capacity[2 + M][1] < 0) return false;
        for (int i = 1; i < N; i++) {
            capacity[2 + M + i][1] = totalWins - wins[i] - 1;
            if (capacity[2 + M + i][1] < 0) return false;
        }

        int result = networkFlow();
        return result == M;// && capacity[2 + M][1] - flow[2 + M][1] == 0;
    }

    // node 수 : 2 + M + N
    // start 0, end 1로 고정
    private static int networkFlow() {

        int result = 0;

        for (int i = 0; i < flow.length; i++) {
            Arrays.fill(flow[i], 0);
        }

        int nodeSize = 2 + M + N;
        int[] parent = new int[nodeSize];

        int start = 0;
        int end = 1;

        while (true) {

            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();

            q.add(start);
            parent[start] = start;

            while (!q.isEmpty()) {
                int now = q.poll();

                for (int next = 0; next < nodeSize; next++) {
                    if (parent[next] == -1 && capacity[now][next] - flow[now][next] > 0) {
                        q.add(next);
                        parent[next] = now;
                    }
                }
            }

            if (parent[end] == -1) {
                break; // end에 도달하지 못했다! 끝났음.
            }

            int minAmount = INF;
            for (int u = end; u != start; u = parent[u]) {
                minAmount = Math.min(minAmount, capacity[parent[u]][u] - flow[parent[u]][u]);
            }

            for (int u = end; u != start; u = parent[u]) {
                flow[parent[u]][u] += minAmount;
                flow[u][parent[u]] -= minAmount;
//                System.out.print(u + " <- ");
            }
//            System.out.println();

            result += minAmount;
//            flowResult += minAmount;
        }
        return result;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 선수 수
        M = Integer.parseInt(st.nextToken()); // 경기 수

        Arrays.fill(wins, 0);

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            wins[i]= Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < matches.length; i++) {
            Arrays.fill(matches[i], 0);
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            matches[i][0] = Integer.parseInt(st3.nextToken());
            matches[i][1] = Integer.parseInt(st3.nextToken());
        }
    }


    private static void printCapa(int totalWins) {

        System.out.println("print capacity : " + totalWins);

        int nodeSize = 2 + M + N;

        for (int i = 0; i < nodeSize; i++) {
            for (int j = 0; j < nodeSize; j++) {
                System.out.print(capacity[i][j]);
            }
            System.out.println();
        }
    }
    private static void printFlow() {

        System.out.println("print flow");

        int nodeSize = 2 + M + N;

        for (int i = 0; i < nodeSize; i++) {
            for (int j = 0; j < nodeSize; j++) {
                System.out.print(flow[i][j]);
            }
            System.out.println();
        }
    }

}
