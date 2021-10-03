import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class MainSORTGAME_SongTaeheon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] input;
    static Map<List<Integer>, Integer> toSort = new HashMap<>();

    public static void main(String[] args) throws IOException {

        preCalc();

        int testCount = readTestCount();
        for (int i = 0; i < testCount; i++) {
            input();
            System.out.println(solve());
        }

    }

    private static void preCalc() {
        int MaxCount = 8;

        List<Integer> perm = new ArrayList<>();

        for (int i = 0; i < MaxCount; i++) perm.add(i);

        Queue<List<Integer>> q = new LinkedList<>();
        q.add(perm);
        toSort.put(perm, 0);

        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            int cost = toSort.get(here);

            for (int i = 0; i < MaxCount; i++) {
                for (int j = i + 1; j < MaxCount; j++) {
                    List<Integer> reversed = reverseList(here, i, j);
                    if (toSort.get(reversed) == null) {
                        toSort.put(reversed, cost + 1);
                        q.add(reversed);
                    }
                }
            }
        }
    }

    private static List<Integer> reverseList(List<Integer> list, int from, int to) {
        List<Integer> result = new ArrayList<>();

        if (from != 0) {
            result.addAll(list.subList(0, from));
        }

        List<Integer> reversedPart = new ArrayList<>(list.subList(from, to + 1));
        Collections.reverse(reversedPart);

        result.addAll(reversedPart);
        result.addAll(list.subList(to + 1, list.size()));

        return result;
    }

    private static Integer solve() {
        int[] fixed = new int[N];

        for (int i = 0; i < N; i++) {

            int smaller = 0;

            for (int j = 0; j < N; j++) {
                if (input[i] > input[j]) {
                    ++smaller;
                }
            }

            fixed[i] = smaller;
        }

        List<Integer> result = Arrays.stream(fixed).boxed().collect(toList());

        for (int i = N; i < 8; i++) {
            result.add(i);
        }

        return toSort.get(result);
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        input = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st1.nextToken());
        }
    }
}
