import java.io.*;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MainINSERTION_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] input;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {
        int[] arr = new int[N];

        // init
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        //reverse
        for (int i = 0; i < N; i++) {
            int moveCount = input[N - 1 - i];
            int pos = N - 1 - i - moveCount;
            int data = arr[pos];

            for (int j = 0; j < moveCount; j++) {
                arr[pos + j] = arr[pos + j + 1];
            }
            arr[pos + moveCount] = data;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        input = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st1.nextToken());
        }
    }
}
