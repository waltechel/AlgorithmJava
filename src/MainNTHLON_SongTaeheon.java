import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainNTHLON_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M;
    static int[] A;
    static int[] B;

    static List<List<Node>> graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            init();
            solve();
        }

    }

    private static void solve() {

        // make graph
        for (int diff = -200; diff <= 200; diff++) {
            for (int game = 0; game < M; game++) {
                int nextDiff = diff + A[game] - B[game]; // 현재 diff에서 다음 diff로 가는 방법
                if (nextDiff > 200 || nextDiff < -200) continue; // -200 ~ 200 (해당 범위 외로 가는 경우는 범위 내로 가는 케이스로 바꿀 수 있기 때문)
                graph.get(vertex(diff)).add(new Node(nextDiff, A[game]));
            }
        }

        // dijkstra
        int result = getMinTime();

        if (result == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }

    }

    private static int getMinTime() {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
        pq.add(new Node(0, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.diff == 0 && distance[vertex(now.diff)] != 0) return distance[vertex(now.diff)];

            for (Node adjNode: graph.get(vertex(now.diff))) {
                if (distance[vertex(adjNode.diff)] == 0 || distance[vertex(adjNode.diff)] > distance[vertex(now.diff)] + adjNode.time) {
                    distance[vertex(adjNode.diff)] = distance[vertex(now.diff)] + adjNode.time;
                    pq.add(new Node(adjNode.diff, distance[vertex(adjNode.diff)]));
                }
            }
        }

        return -1;
    }

    static int vertex(int diff) { return diff + 200; }


    private static void init() {
        distance = new int[401];
        graph = new ArrayList<>();
        for (int i = 0; i <= 400; i++) graph.add(new ArrayList<>());
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        A = new int[M];
        B = new int[M];

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st2.nextToken());
            B[i] = Integer.parseInt(st2.nextToken());
        }
    }

    static class Node {
        int diff;
        int time;

        public Node(int diff, int time) {
            this.diff = diff;
            this.time = time;
        }
    }
}
