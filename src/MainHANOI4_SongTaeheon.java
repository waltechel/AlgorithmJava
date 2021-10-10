import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainHANOI4_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int COLUMN_COUNT = 4;
    static int N;
    static int[] input;

    static int[] count;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
           input();
            int begin = makeTargetStatus();
            int end = 0;
            for(int j=0; j< N ; ++j)
                end = set(end, j,3);
            System.out.println(solve(begin, end));
        }
    }

    private static int solve(int begin, int end) {

        if (begin == end) {
            return 0;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(begin);
        q.add(end);

        count[begin] = 1;
        count[end] = -1;

        while(!q.isEmpty()) {
            int here = q.poll();

            int[] top = {-1, -1, -1, -1};
            for (int i = N-1; i >=0; i--) {
                top[get(here, i)] = i;
            }

            // i -> j
            for (int i = 0; i < COLUMN_COUNT; i++) {
                if (top[i] == -1) continue; // top 에 아무것도 없음.
                for (int j = 0; j < COLUMN_COUNT; j++) {
                    if (i != j && (top[j] == -1 || top[j] > top[i])) {
                        int there = set(here, top[i], j);
                        if (count[there] == 0) {
                            count[there] = incr(count[here]);
                            q.add(there);
                        } else if (sgn(count[there]) != sgn(count[here])) {
                            return Math.abs(count[there]) + Math.abs(count[here]) - 1;
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static int incr(int count) {
        if (count == 0) return 0;
        if (count < 0) return count - 1;
        return count + 1;
    }

    private static int sgn(int value) {
        return Integer.compare(value, 0);
    }

    static int get(int state, int index) {
        return (state >> (index * 2)) & 3;
    }

    static int set(int state, int index, int value) {
        return (state & ~(3 << (index * 2))) | (value << (index * 2));
    }

    private static int makeTargetStatus() {
        int status = 0;
        for (int i = 0; i < N; i++) {
            status = set(status, i, input[i]);
        }
        return status;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        input = new int[N];
        count = new int[(1 << (12 * 2))];

//        for (int i = COLUMN_COUNT -1; i >= 0; i--) { // 제일 왼쪽 기둥 index 3
        for (int i = 0; i < COLUMN_COUNT; i++) { // 제일 왼쪽 기둥 index 3
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int discCount = Integer.parseInt(st1.nextToken());

            for (int j = 0; j < discCount; j++) {
                int discId = Integer.parseInt(st1.nextToken());
                input[discId - 1] = i;
            }
        }
    }

}