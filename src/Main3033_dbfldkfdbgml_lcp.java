import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main3033_dbfldkfdbgml_lcp {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int L = Integer.parseInt(br.readLine().trim());
		String buffer = br.readLine().trim();

		ArrayList<Item> list = new ArrayList<>();
		for (int i = 0; i < buffer.length(); i++) {
			String suffix = buffer.substring(i);
			int[] pair = new int[] { (int) (suffix.charAt(0) - 'a'), 0 };
			Item item = new Item(pair, 0, suffix);
			list.add(item);
		}

		Collections.sort(list);

		HashMap<String, Integer> rankMap = new HashMap<>();
		for (int rank = 0, cri1 = -1, cri2 = -1, i = 0; i < list.size(); i++) {
			Item item = list.get(i);
			if (item.pair[0] > cri1) {
				item.rank = ++rank;
				cri1 = item.pair[0];
				cri2 = item.pair[1];
			} else if (item.pair[0] == cri1 && item.pair[1] > cri2) {
				item.rank = ++rank;
				cri2 = item.pair[1];
			} else {
				item.rank = rank;
			}
			rankMap.put(item.suffix.charAt(0) + "", rank);
		}

		 print(list, rankMap);

		for (int i = 1; i <= L; i *= 2) {
			for (Item item : list) {
				item.pair[0] = item.rank;
				String tempString = subString(item.suffix, i, i + i);
				int rank = rankMap.containsKey(tempString) ? rankMap.get(tempString) : 0;
				item.pair[1] = rank;
			}
			Collections.sort(list);
			rankMap.clear();
			int rank = 0;
			for (int cri1 = -1, cri2 = -1, j = 0; j < list.size(); j++) {
				Item item = list.get(j);
				if (item.pair[0] > cri1) {
					item.rank = ++rank;
					cri1 = item.pair[0];
					cri2 = item.pair[1];
				} else if (item.pair[0] == cri1 && item.pair[1] > cri2) {
					item.rank = ++rank;
					cri2 = item.pair[1];
				} else {
					item.rank = rank;
				}
				rankMap.put(subString(item.suffix, 0, i + i), rank);
			}
			// 이미 랭크를 다 매겨서 더 정렬을 안해도 될 때
			if (rank == L) {
				break;
			}

			 print(list, rankMap);
		}

		 print(list, rankMap);

		HashMap<String, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			indexMap.put(list.get(i).suffix, i);
		}
		// System.out.println(Arrays.toString(strings));
		// System.out.println(indexMap);

		int[] lcpArray = new int[L];
		for (int i = 0, k = 0; i < list.size(); i++, k = Math.max(0, k - 1)) {
			String suffix = list.get(i).suffix;
			int index = indexMap.get(suffix);
			if (index == list.size() - 1) {
				continue;
			}
			String suffix2 = list.get(index + 1).suffix;
			if (k == 0) {
				for (int j = 0; j < Math.min(suffix.length(), suffix2.length()); j++) {
					if (suffix.charAt(j) == suffix2.charAt(j)) {
						k++;
					} else {
						break;
					}
				}
				// 이미 이전에 같은 게 몇 개 있을 때
			} else {
				for (int j = k; j < Math.min(suffix.length(), suffix2.length()); j++) {
					if (suffix.charAt(j) == suffix2.charAt(j)) {
						k++;
					} else {
						break;
					}
				}
			}

			lcpArray[index] = k;
		}

		int answer = 0;
		for (int i = 0; i < lcpArray.length; i++) {
			answer = Math.max(lcpArray[i], answer);
		}
		bw.write(answer + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	private static void print(ArrayList<Item> list, HashMap<String, Integer> rankMap) {
		System.out.println();
		for (Item item : list) {
			System.out.println(item);
		}
		System.out.println(rankMap.toString());
	}

	private static String subString(String suffix, int from, int to) {
		if (suffix.length() < from) {
			return "";
		}
		if (suffix.length() < to) {
			return suffix.substring(from, suffix.length());
		}
		return suffix.substring(from, to);
	}

	private static class Item implements Comparable<Item> {

		int[] pair;
		int rank;
		String suffix;

		public Item(int[] pair, int rank, String suffix) {
			super();
			this.pair = pair;
			this.rank = rank;
			this.suffix = suffix;
		}

		@Override
		public String toString() {
			return "Item [pair=" + Arrays.toString(pair) + ", rank=" + rank + ", suffix=" + suffix + "]";
		}

		@Override
		public int compareTo(Item item) {
			if (this.pair[0] != item.pair[0]) {
				return this.pair[0] - item.pair[0];
			} else {
				return this.pair[1] - item.pair[1];
			}
		}

	}
}
