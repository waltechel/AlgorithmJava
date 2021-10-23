import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainPROMISES_SongTaeheon {
    private static final int INF = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, M, N;
    static long[][] distance;
    static NewRoad[] newRoads;


    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {

        int result = 0;

        findShortestDistance();

        for (int i = 0; i < N; i++) {
            if (!update(newRoads[i])) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    private static boolean update(NewRoad newRoad) {

        boolean isUpdatable = false;

        int a = newRoad.a;
        int b = newRoad.b;
        int c = newRoad.c;

//        System.out.println("a : " + a + ", b : " + b + ", c : " + c);

        if (distance[a][b] < c) return false;

        for (int start = 0; start < V; start++) {
            for (int end = 0; end < V; end++) {
                long throughNewRoad = Math.min(distance[start][a] + c + distance[b][end], distance[start][b] + c + distance[a][end]);

                if (distance[start][end] > throughNewRoad) {
//                    System.out.println(start + " ~ " + end + " : before : " + distance[start][end] + ", after : " + throughNewRoad);

                    distance[start][end] = throughNewRoad;
                    isUpdatable = true;

                }
            }
        }

        return isUpdatable;
    }

    private static void findShortestDistance() {
        for (int via = 0; via < V; via++) {
            for (int start = 0; start < V; start++) {
                for (int end = 0; end < V; end++) {
                    distance[start][end] = Math.min(distance[start][end], distance[start][via] + distance[via][end]);
                }
            }
        }
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        distance = new long[V][V];
        newRoads = new NewRoad[N];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    distance[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            long c = Long.parseLong(st2.nextToken());

            distance[a][b] = Math.min(distance[a][b], c);
            distance[b][a] = Math.min(distance[a][b], c);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int c = Integer.parseInt(st2.nextToken());

            newRoads[i] = new NewRoad(a, b, c);
        }
    }

    static class NewRoad {
        int a, b, c;

        public NewRoad(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
