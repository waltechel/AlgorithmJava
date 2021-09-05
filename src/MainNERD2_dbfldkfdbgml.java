import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MainNERD2_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int C = Integer.parseInt(br.readLine());
			// 레드블랙트리를 구현하기 위한 트리맵
			TreeMap<Integer, Integer> map = new TreeMap<>();
			long answer = 0;
			for (int i = 0; i < C; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				Entry<Integer, Integer> entry = map.ceilingEntry(x);
				// 자기보다 오른쪽에 바로 있는 것을 찾아서 포함되는지를 비교한다.
				if (entry == null) {
					answer += process(map, x, y);
					// 자기보다 오른쪽에 있는 것이 더 위치가 높다면 그냥 버린다
				} else if (y < entry.getValue()) {
					answer += map.size();
					// 자기를 추가하고, 자기보다 왼쪽에 있는 것들 중 높이가 낮은 걸 버린다
				} else {
					answer += process(map, x, y);
				}
			}
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static long process(TreeMap<Integer, Integer> map, int x, int y) {
		while (true) {
			Entry<Integer, Integer> leftEntry = map.floorEntry(x);
			if (leftEntry != null && leftEntry.getValue() < y) {
				map.remove(leftEntry.getKey());
			} else {
				break;
			}
		}
		map.put(x, y);
		return map.size();
	}

}