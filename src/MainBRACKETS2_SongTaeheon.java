import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainBRACKETS2_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String input;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {

        boolean result = true;
        Stack<Character> queue = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '{' || ch == '('){
                queue.add(ch);
            } else {

                if (queue.isEmpty()) {
                    result = false;
                    break;
                }

                char out = queue.pop();
                if(!isMatched(out, ch)) {
                    result = false;
                    break;
                }
            }
        }

        if (!queue.isEmpty()) {
            result = false;
        }

        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static boolean isMatched(char open, char close) {

        if (open == '(' && close == ')') return true;
        if (open == '{' && close == '}') return true;
        if (open == '[' && close == ']') return true;

        return false;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        input = st.nextToken();
    }
}
