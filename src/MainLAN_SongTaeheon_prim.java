import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainLAN_SongTaeheon_prim {
    private static final double INF = Double.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N;
    static int[] x;
    static int[] y;

    static double[][] adj;
    static InputConnected[] alreadyConnected;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            init();
            solve();
        }

    }

    private static void solve() {

        double result = 0;

        boolean[] selected = new boolean[N];
        double[] minWeight = new double[N];
        int[] parent = new int[N];

        Arrays.fill(minWeight, INF);

        minWeight[0] = -1; // 거리가 0도 있기 때문에 최소값을 음수로 선택한다.
        parent[0] = 0; // parent없음. 자기 자신으로 세팅한다.

        for (int i = 0; i < N; i++) {

            // minWeight가 가장 작은 것 선택
            int minVertex = -1;
            for (int vertex = 0; vertex < N; vertex++) {
                if (!selected[vertex] && (minVertex == -1 || minWeight[minVertex] > minWeight[vertex])) {
                    minVertex = vertex;
                }
            }

            // 선택여부 체크
            selected[minVertex] = true;

            // 선택된 간선의 길이 추가
            if (minVertex != 0) { // 0인 경우는 첫 번째 선택이기 때문에, 간선 없음
                result += adj[parent[minVertex]][minVertex]; // parent ~ minVertex 사이의 간선 길이
            }

            // minWeight 업데이트, parent 업데이트
            for (int near = 0; near < N; near++) { // 전부 연결되어 있음!
                if (!selected[near] && minWeight[near] > adj[minVertex][near]) {
                    minWeight[near] = adj[minVertex][near];
                    parent[near] = minVertex;
                }
            }
        }

        System.out.println(result);
    }

    private static void init() {

        adj = new double[N][N];

        // 거리 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double distance = calcDistance(i, j);
                adj[i][j] = distance;
                adj[j][i] = distance;
            }
        }

        // 연결된 곳은 거리를 0으로 만든다.
        for (InputConnected input: alreadyConnected) {
            adj[input.a][input.b] = 0;
            adj[input.b][input.a] = 0;
        }
    }

    private static double calcDistance(int i, int j) {
        return Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 건물 수
        M = Integer.parseInt(st.nextToken()); // 이미 연결된 케이블 수

        x = new int[N];
        y = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st2.nextToken());
        }

        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            y[i] = Integer.parseInt(st3.nextToken());
        }

        alreadyConnected = new InputConnected[M];

        for (int i = 0; i < M; i++) {
            StringTokenizer st4 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st4.nextToken());
            int b = Integer.parseInt(st4.nextToken());
            alreadyConnected[i] = new InputConnected(a, b);
        }
    }

    private static class InputConnected {
        int a, b;
        public InputConnected(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
