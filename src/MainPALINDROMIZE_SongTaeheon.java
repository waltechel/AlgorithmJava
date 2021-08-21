import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainPALINDROMIZE_SongTaeheon {

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
        String reverseString = new StringBuilder(input).reverse().toString();
        int[] pi = makePartialTable(reverseString);

        int longestMatchedLength = 1; // 1개는 항상 맞음. (뒤집은 문자열의 비교이기 때문)

        int begin = 0;
        int matched = 0;

        while (begin + matched < input.length()) {

            if (input.charAt(begin + matched) == reverseString.charAt(matched)) {
                matched++;

                if (begin + matched == input.length()) {
                    longestMatchedLength = matched;
                    break;
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin = begin + matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        int result = input.length() * 2 - longestMatchedLength;
        System.out.println(result);
    }

    private static int[] makePartialTable(String reverseString) {
        int[] pi = new int[reverseString.length()];

        int begin = 1;
        int matched = 0;

        while(begin + matched < reverseString.length()) {

            if (reverseString.charAt(begin + matched) == reverseString.charAt(matched)) {
                pi[begin + matched] = matched + 1;
                matched++;
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
        input = st.nextToken();
    }
}
