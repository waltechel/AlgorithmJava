import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MainITES_SongTaeheon {

    private static final int FIRST_INPUT = 1983;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int K, N;

    public static void main(String[] args) throws IOException {
        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {
        long ai = FIRST_INPUT;

        int index = 0;
        int result = 0;

        Deque<Long> queue = new LinkedList<>();
        long sum = 0;

        while(index < N) {

            if (sum < K) {
                long signal = calNextSignal(ai);
                ai = calNextA(ai);

                queue.addFirst(signal);
                sum += signal;
                index += 1;
            } else if (sum == K) {
                long value = queue.removeLast();
                sum -= value;
                result += 1;
            } else if (sum > K) {
                long value = queue.removeLast();
                sum -= value;
            }
        }

        System.out.println(result);
    }

    private static long calNextSignal(long ai) {
        return (ai % 10_000) + 1;
    }

    private static long calNextA(long now) {
        return (now * 214_013L + 2_531_011L) % (1L << 32L);
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

    }
}
