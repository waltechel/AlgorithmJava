import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainRUNNINGMEDEIAN_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, a, b;
    static int firstValue = 1983;
    static int input;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {

        int result = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // init
        Integer mid = input;
        result += mid;

        for (int i = 1; i < N; i++) {

            input = getNextInput();

            if (mid >= input) {
                maxHeap.add(input);

                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(mid);
                    mid = maxHeap.poll();
                }

            } else {
                minHeap.add(input);

                if (minHeap.size() > maxHeap.size() + 1) {
                    maxHeap.add(mid);
                    mid = minHeap.poll();
                }
            }

            result = (result + mid) % 20090711;
        }
        System.out.println(result);
    }

    private static int getNextInput() {
        return (int)(((long) input * a + b) % 20090711);
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        input = firstValue;

    }
}
