import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MainNERD2_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {


        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            solve();
        }

    }

    private static void solve() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        int result = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>(); //problems, ramens

        for (int i = 0; i < N; i++) {
            StringTokenizer inputSt = new StringTokenizer(br.readLine());
            int problem = Integer.parseInt(inputSt.nextToken());
            int ramen = Integer.parseInt(inputSt.nextToken());


            Entry<Integer, Integer> ceilingEntry = map.higherEntry(problem); // key가 problem[i]보다 큰 것 중에 가장 작은 entry

            // 1. problem을 나보다 많이 푼 사람이 없음. 들어갈 수 있음.
            // or
            //2. 나보다 많이 푼 사람 중 라면을 내가 제일 많이 먹음.
            // 만약 key가 더 큰 사람 중에 라면을 더 많이 먹는 사람이 있다면? -> key가 더 큰 사람 중 가장 작은 사람만 보면됨!
            // key가 더 큰 사람 중에 value도 더 큰 사람은 없기 때문.
            if (ceilingEntry == null || ceilingEntry.getValue() < ramen) {
                map.put(problem, ramen);
                removeDominatedParticipations(map, problem, ramen);
            }

            result += map.size();
        }

        System.out.println(result);

    }

    private static void removeDominatedParticipations(TreeMap<Integer, Integer> map, int key, int value) {
//        SortedMap<Integer, Integer> lowerProblemMap = map.headMap(key);
//        Object[] problems = lowerProblemMap.keySet().toArray();
//        for (int j = problems.length - 1; j >= 0; j--) { // 들어가면서 나와야 할 것들을 뺀다.
//            Integer problem = (Integer) problems[j];
//            Integer ramen = map.get(problem);
//            if (ramen < value) {
//                map.remove(problem);
//            } else {
//                break;
//            }
//        }
        while (true) {
            int lower;
            if (map.lowerKey(key) == null) {
                return;
            } else {
                lower = map.lowerKey(key);
            }

            if (value > map.get(lower)) {
                map.remove(lower);
            } else {
                break;
            }
        }

    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

}
