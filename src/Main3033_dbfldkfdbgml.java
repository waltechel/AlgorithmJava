import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3033_dbfldkfdbgml {

	private static String line;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int L = Integer.parseInt(br.readLine());
		line = br.readLine();

		List<Item> suffixArray = new ArrayList<>();
		for (int startIndex = 0; startIndex < line.length(); startIndex++) {
			int[] pair = new int[] { (int) (line.charAt(startIndex) - 'a'), 0 };
			Item item = new Item(pair, 0, startIndex);
			suffixArray.add(item);
		}

		Collections.sort(suffixArray);

		HashMap<String, Integer> rankMap = new HashMap<>();
		for (int rank = 0, cri1 = -1, cri2 = -1, i = 0; i < suffixArray.size(); i++) {
			Item item = suffixArray.get(i);
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
			rankMap.put(line.charAt(item.startIndex) + "", rank);
		}

//		print(list, rankMap);

		for (int i = 1; i <= L; i *= 2) {
			for (Item item : suffixArray) {
				item.pair[0] = item.rank;
				String tempString = subString(line.substring(item.startIndex), i, i + i);
				int rank = rankMap.containsKey(tempString) ? rankMap.get(tempString) : 0;
				item.pair[1] = rank;
			}
			Collections.sort(suffixArray);
			rankMap.clear();
			int rank = 0;
			for (int cri1 = -1, cri2 = -1, j = 0; j < suffixArray.size(); j++) {
				Item item = suffixArray.get(j);
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
				rankMap.put(subString(line.substring(item.startIndex), 0, i + i), rank);
			}
			// 이미 랭크를 다 매겨서 더 정렬을 안해도 될 때
			if (rank == L) {
				break;
			}

//			print(list, rankMap);
		}

//		print(list, rankMap);
		Map<Integer, Integer> indexToStartIndex = new HashMap<>();
		Map<Integer, Integer> startIndexToIndex = new HashMap<>();
		for (int i = 0; i < suffixArray.size(); i++) {
			indexToStartIndex.put(i, suffixArray.get(i).startIndex);
			startIndexToIndex.put(suffixArray.get(i).startIndex, i);
		}
		int[] lcpArray = new int[suffixArray.size()];
		for (int startIndexA = 0, k = 0; startIndexA < line.length(); startIndexA++) {
			String stringA = line.substring(startIndexA);
			int indexA = startIndexToIndex.get(startIndexA);
			if (indexA == 0) {
				lcpArray[indexA] = 0;
				continue;
			}
			int startIndexB = indexToStartIndex.get(indexA - 1);
			String stringB = line.substring(startIndexB);
			if (k > 0) {
				k--;
			}
			while (stringA.length() > k && stringB.length() > k && stringA.charAt(k) == stringB.charAt(k)) {
				k++;
			}
			lcpArray[indexA] = k;
		}

		int answer = 0;
		for (int i = 0; i < lcpArray.length; i++) {
			answer = Math.max(lcpArray[i], answer);
		}
		bw.write(answer + "");

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
		int startIndex;

		public Item(int[] pair, int rank, int startIndex) {
			super();
			this.pair = pair;
			this.rank = rank;
			this.startIndex = startIndex;
		}

		@Override
		public String toString() {
			return "Item [pair=" + Arrays.toString(pair) + ", rank=" + rank + ", substr=" + line.substring(startIndex)
					+ ", startIndex=" + startIndex + "]";
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