import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainJAEHASAFE_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int moveCount = 0;
    static String[] inputs = new String[51];

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {

        int result = 0;
        boolean isClockwise = true;
        for (int i = 0; i < moveCount; i++) {
            int ticks = findTicks(inputs[i], inputs[i + 1], isClockwise);

            result += ticks;
            isClockwise = !isClockwise;
        }
        System.out.println(result);
    }

    private static int findTicks(String prev, String next, boolean isClockwise) {
        int result = 0;

        String doubledNext = next + next;
        int[] pi = createPartialTable(prev);

        int begin = 0;
        int matched = 0;
        while (begin + matched < doubledNext.length()) {
            if (doubledNext.charAt(begin + matched) == prev.charAt(matched)) {
                matched += 1;
                if (matched == prev.length()) {
                    result = begin;

                    if (isClockwise) { // clockWise면 제일 먼저 걸린게 최단
                        break;
                    } else { // counterClockwise면 제일 나중에 걸린게 최단
                        begin = begin + matched - pi[matched - 1];
                        matched = pi[matched - 1];
                    }
                }
            } else {
                if (matched == 0) {
                    begin += 1;
                } else {
                    begin = begin + matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        if (!isClockwise) {
            result = prev.length() - result;
        }


        if (result == 0) {
            result = prev.length();
        }
        return result;
    }

    private static int[] createPartialTable(String str) {

        int[] pi = new int[str.length()];

        int begin = 1;
        int matched = 0;

        while (begin + matched < str.length()) {
            if (str.charAt(begin + matched) == str.charAt(matched)) {
                pi[begin + matched] = matched + 1;
                matched += 1;
            } else {
                if (matched == 0) {
                    begin += 1;
                } else {
                    begin = begin + matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return pi;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        moveCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < moveCount + 1; i++) {
            StringTokenizer stStr = new StringTokenizer(br.readLine());
            inputs[i] = stStr.nextToken();
        }
    }
}
