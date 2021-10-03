import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class MainMEETINGROOM_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] input;
    static List<List<Integer>> graph;

    final static int START_INDEX = 0;
    final static int END_INDEX = 1;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {
        makeGraph();
        int[] result = solve2SAT();

        if (result.length == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println("POSSIBLE");
            for (int i: result) {
                if (i == 1) {
                    System.out.println(input[i][0] + " " + input[i][1]);
                }
            }
        }
    }

    private static int[] solve2SAT() {
        int n = graph.size();

        List<Integer> label = tarjanSCC();

        // check possible
        for (int i = 0; i < 2 * N; i++) {
            if (Objects.equals(label.get(i), label.get(i + 1))) {
                return new int[0];
            }
        }

        int[] value = new int[2 * n];
        Arrays.fill(value, -1);

        List<Pair> order = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) {
            order.add(new Pair(label.get(i) * -1, i));
        }

        order.sort(Comparator.comparing(Pair::getFirst));

        for (int i = 0; i < 2 *n; i++) {
            int vertex = order.get(i).second;
            int variable = vertex / 2;
            int isFalse = vertex % 2 == 0 ? 0 : 1;

            if (value[variable] != -1) continue;

            value[variable] = isFalse;
        }
        return value;

    }


    static List<List<Integer>> adj;
    static int[] sccId;
    static int[] discovered;
    static Stack<Integer> st;
    static int sccCounter, vertexCounter;

    private static List<Integer> tarjanSCC() {
        init();

        for (int i = 0; i < adj.size(); i++) {
            if (discovered[i] == -1) scc(i);
        }
        return Arrays.stream(sccId).boxed().collect(toList());
    }

    private static int scc(int here) {

        vertexCounter += 1;
        int ret = vertexCounter;
        discovered[here] = vertexCounter;
        st.push(here);

        for (int i = 0; i < adj.get(here).size(); i++) {
            int there = adj.get(here).get(i);

            if (discovered[there] == -1) ret = Math.min(ret, scc(there));
            else if (sccId[there] == -1) ret = Math.min(ret, discovered[there]);
        }

        if (ret == discovered[here]) {
            while (true) {
                int t = st.pop();
                sccId[t] = sccCounter;
                if (t == here) break;
            }
            sccCounter += 1;
        }
        return ret;
    }

    private static void init() {
        adj = new ArrayList<>();
        sccId = new int[2 * N];
        discovered = new int[2 * N];
    }

    private static void makeGraph() {
        graph = new ArrayList<>();
        for (int i = 0; i < 4 * N; i++) { // 회의1 ~회의1 회의2 ~회의2
            graph.add(new ArrayList<>());
        }

        for (int meeting1 = 0; meeting1 < 2 * N; meeting1 += 2) {
            int meeting2 = meeting1 + 1; // meeting1의 번호, meeting2의 번호

            // meeting1 또는 meeting2를 참여해야함.
            graph.get(meeting1 * 2 + 1).add(meeting2 * 2); // ~meeting1 -> meeting2
            graph.get(meeting2 * 2 + 1).add(meeting1 * 2); // ~meeting2 -> meeting1
        }

        for (int i = 0; i < 2 * N; i++) {
            for (int j = 0; j < i; j++) {
                if (!disjoint(input[i], input[j])) { //i와 j가 겹치는 경우
                    graph.get(i * 2).add(j * 2 + 1); // i -> ~j
                    graph.get(j * 2).add(i * 2 +1); // j -> ~i
                }
            }
        }
    }

    private static boolean disjoint(int[] meeting1, int[] meeting2) {
        return meeting1[END_INDEX] <= meeting2[START_INDEX] || meeting2[END_INDEX] <= meeting1[START_INDEX];
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        input = new int[2 * N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start1 = Integer.parseInt(st1.nextToken());
            int end1 = Integer.parseInt(st1.nextToken());
            input[i][0] = start1;
            input[i][1] = end1;

            int start2 = Integer.parseInt(st1.nextToken());
            int end2 = Integer.parseInt(st1.nextToken());
            input[i + 1][0] = start2;
            input[i + 1][1] = end2;
        }
    }

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }
    }
}
