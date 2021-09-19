import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainMEASURETIME_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] input;
    static int[] tree;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {
        int result = 0;

        for (int value: input) {
            result += sumByFenwick(999_999) - sumByFenwick(value);
            addToFenwickTree(value);
        }
        System.out.println(result);
    }

    private static void addToFenwickTree(int index) { // value를 index로 사용한다.

        int pos = index + 1; // 1 base

        while (pos < tree.length) {
            tree[pos] += 1; // 펜윅트리에는 자신의 개수 (1개)를 넣는다.
            pos += (pos & -pos); // 마지막 bit(1)을 찾아서 더한다.
        }
    }

    private static int sumByFenwick(int index) {
        int pos = index + 1; // 1 based
        int result = 0;

        while (pos > 0) { // 삭제할 비트(1)이 있으면 계속한다.
            result += tree[pos];
            pos = pos & (pos - 1); // 마지막 1비트 삭제 - 그림 참조.
        }

        return result;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        tree = new int[1_000_001];
        input = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st1.nextToken());
        }

    }
}
