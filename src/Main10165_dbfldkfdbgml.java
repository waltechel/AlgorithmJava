import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main10165_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<int[]> busList = new ArrayList<>();

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (from < to) {
				busList.add(new int[] { i, from, to, 1 });
			} else {
				busList.add(new int[] { i, from, to + N, 1 });
			}
		}

		// 시작점과 끝점 가지고 정렬
		Collections.sort(busList, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] != o2[1]) {
					return o1[1] - o2[1];
				} else {
					return o2[2] - o1[2];
				}
			}
		});

//		printBusList("맨처음 정렬", busList);
		int right = -1;
		for (int[] bus : busList) {
			if (bus[2] <= right) {
				bus[3] = 0;
			}
			if (right < bus[2]) {
				right = bus[2];
			}
		}

//		printBusList("1차 제거", busList);
		for (int[] bus : busList) {
			if (bus[2] < N && bus[2] <= right - N) {
				bus[3] = 0;
			}
		}

//		printBusList("2차 제거", busList);

		ArrayList<Integer> answerList = new ArrayList<Integer>();
		for (int[] bus : busList) {
			if (bus[3] == 1) {
				answerList.add(bus[0]);
			}
		}

		Collections.sort(answerList);
		for (Integer e : answerList) {
			bw.write(e + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void printBusList(String string, ArrayList<int[]> busList) {

		System.out.println(string);
		for (int[] bus : busList) {
			if (bus[3] == 1)
				System.out.print(bus[0] + " ");
		}
		System.out.println();

	}

}
