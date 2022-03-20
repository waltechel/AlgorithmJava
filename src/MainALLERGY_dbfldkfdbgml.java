import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainALLERGY_dbfldkfdbgml {

	private static int friendLength, foodLength;
	private static int[][] matrixFriendToFood;
	private static ArrayList[] adjListFoodToFriend;
	private static int answer;
	private static int[] friend, food, foodOrder;
	private static int[] numberOfFoodByFriends;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			friendLength = Integer.parseInt(st.nextToken());
			foodLength = Integer.parseInt(st.nextToken());
			matrixFriendToFood = new int[friendLength][foodLength];
			
			st = new StringTokenizer(br.readLine());
			HashMap<String, Integer> friendToIndex = new HashMap<>();
			for (int i = 0; i < friendLength; i++) {
				String s = st.nextToken();
				friendToIndex.put(s, i);
			}
			for (int foodIndex = 0; foodIndex < foodLength; foodIndex++) {
				st = new StringTokenizer(br.readLine());
				int t = Integer.parseInt(st.nextToken());
				for (int j = 0; j < t; j++) {
					String friend = st.nextToken();
					int friendIndex = friendToIndex.get(friend);
					matrixFriendToFood[friendIndex][foodIndex] = 1;
				}
			}

			// 음식이 적용되는 친구의 수
			food = new int[foodLength];
			for (int j = 0; j < foodLength; j++) {
				for (int i = 0; i < friendLength; i++) {
					food[j] += matrixFriendToFood[i][j];
				}
			}
			// 친구가 먹을 수 있는 음식의 종류 수
			friend = new int[friendLength];
			for (int i = 0; i < friendLength; i++) {
				for (int j = 0; j < foodLength; j++) {
					friend[i] += matrixFriendToFood[i][j];
				}
			}

			adjListFoodToFriend = new ArrayList[foodLength];
			for (int i = 0; i < foodLength; i++) {
				adjListFoodToFriend[i] = new ArrayList<Integer>();
			}
			for (int j = 0; j < foodLength; j++) {
				for (int i = 0; i < friendLength; i++) {
					if (matrixFriendToFood[i][j] == 1) {
						adjListFoodToFriend[j].add(i);
					}
				}
			}

			// 음식 중에 필수품이 있음(이거는 반드시 선택해야 하는 음식)
			ArrayList<int[]> list = new ArrayList<>();
			for (int i = 0; i < foodLength; i++) {
				int isNecessary = 0;
				for (int j = 0; j < friendLength; j++) {
					// 한 음식 종류밖에 못 먹는 친구가 있음
					if (matrixFriendToFood[j][i] == 1 && friend[j] == 1) {
						isNecessary = 1;
						break;
					}
				}
				list.add(new int[] { i, food[i], isNecessary });
			}

			// 음식 정렬하기
			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// 필수품은 반드시 앞에 놓는다
					if (o2[2] != o1[2]) {
						return o2[2] - o1[2];
						// 이왕이면 많은 사람을 만족시킬 수 있는 음식이 좋다
					} else if (o2[1] != o1[1]) {
						return o2[1] - o1[1];
						// 그다음에는 그냥 오름차순이다
					} else {
						return o1[0] - o2[0];
					}
				}
			});

			foodOrder = new int[foodLength];
			for (int i = 0; i < list.size(); i++) {
				foodOrder[i] = list.get(i)[0];
			}

			// 51 is maximum of this problem
			answer = 51;
			numberOfFoodByFriends = new int[friendLength];
			dfs(0, true, 1);
			reset(0);
			dfs(0, false, 0);
			bw.write(answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * index 번째 음식을 flag 하느냐 마느냐, 그리고 그 결과 총 선택한 음식의 수
	 * @param index index 번째 음식
	 * @param flag flag 하느냐 마느냐
	 * @param cnt 그 결과 총 선택한 음식의 수
	 */
	private static void dfs(int index, boolean flag, int cnt) {
		// 선택했을 때
		if (flag) {
			int foodIndex = foodOrder[index];
			boolean check = true;
			for (int friendIndex = 0; friendIndex < friendLength; friendIndex++) {
				numberOfFoodByFriends[friendIndex] += matrixFriendToFood[friendIndex][foodIndex];
				if (numberOfFoodByFriends[friendIndex] == 0) {
					check = false;
				}
			}
			// 모든 사람이 음식을 다 먹었을 때
			if (check) {
				answer = Math.min(cnt, answer);
				return;
			}
		}

		if (cnt + 1 < answer && index + 1 < foodLength) {
			boolean isPossible = false;
			int foodIndex = foodOrder[index + 1];

			// 음식을 먹음으로써 배가 부르는 친구가 발생했을 때
			for (Object friendIndex : adjListFoodToFriend[foodIndex]) {
				if (numberOfFoodByFriends[(Integer) friendIndex] + 1 == 1) {
					isPossible = true;
					break;
				}
			}

			if (isPossible) {
				dfs(index + 1, true, cnt + 1);
				reset(index + 1);
			}
			dfs(index + 1, false, cnt);
		}

	}

	private static void reset(int index) {
		int j = foodOrder[index];
		for (Object friendIndex : adjListFoodToFriend[j]) {
			numberOfFoodByFriends[(Integer) friendIndex]--;
		}
	}

}
