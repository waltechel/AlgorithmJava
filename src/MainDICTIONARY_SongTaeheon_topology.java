import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainDICTIONARY_SongTaeheon_topology {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String[] input;
    static int[][] graph;

    static boolean[] isVisited;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            makeGraph();
            dfsAll();
            if (!checkCycle()) {
                System.out.println("INVALID HYPOTHESIS");
            } else {
                result.stream().map((integer) -> (char) (integer + 'a')).forEach(System.out::print);
                System.out.println();
            }

        }

    }

    private static void makeGraph() {

        for (int i = 0; i < N - 1; i++) {
            int front = input[i].charAt(0) - 'a';
            int back = input[i+1].charAt(0) - 'a';

            int minLength = Math.min(input[i].length(), input[i + 1].length());

            int index;

            for (index = 0; index < minLength; index++) {
                front = input[i].charAt(index) - 'a';
                back = input[i+1].charAt(index) - 'a';

                if (front != back) break;
            }

            if (index >= minLength) continue; // ex) abc, abcd

//            System.out.println((char) (front+'a') + " -> " + (char) (back+'a'));
            graph[front][back] = 1;
        }
    }

    private static boolean checkCycle() {

        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                if (graph[result.get(j)][result.get(i)] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void dfsAll() {
        for (int i = 0; i < 26; i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }

        Collections.reverse(result);
    }

    private static void dfs(int ch) {
        isVisited[ch] = true;

        for (int i = 0; i < 26; i++) {
            if (ch == i) continue;
            if (isVisited[i]) continue;

            if (graph[ch][i] == 1) {
                dfs(i);
            }
        }

        result.add(ch);
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        input = new String[N];
        graph = new int[26][26];
        isVisited = new boolean[26];
        result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            input[i] = st1.nextToken();
        }
    }
}
