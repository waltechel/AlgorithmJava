import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainJESOPHUS_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {
        List<Integer> people = IntStream.range(1, N + 1).boxed().collect(Collectors.toList());
        int position = 0;

        while(people.size() > 2) {
            people.remove(position);

            position = (position + (K-1)) % people.size(); // remove되면 한 칸씩 땡겨지기 때문에 K가 아닌 K-1을 더한다.
        }

        System.out.println(people.get(0) + " " + people.get(1));

    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N
        K = Integer.parseInt(st.nextToken()); // K
    }
}
