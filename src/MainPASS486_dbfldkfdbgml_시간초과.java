import java.util.HashMap;
import java.util.Map.Entry;

public class MainPASS486_dbfldkfdbgml_시간초과 {

	private static final int MAX = 10_000_001;

	public static void main(String[] args) throws Exception {

		long beforeT = System.currentTimeMillis();

		long[] answer = new long[MAX];
		HashMap<Integer, Integer>[] list = new HashMap[MAX];
		for (int i = 0; i < MAX; i++) {
			list[i] = new HashMap<Integer, Integer>();
		}
		boolean[] isPrime = new boolean[MAX];
		for (int i = 0; i < MAX; i++) {
			isPrime[i] = true;
		}
		for (int i = 2; i < MAX; i++) {
			if (isPrime[i]) {
				for (int j = i * 2; j < MAX; j += i) {
					isPrime[j] = false;
					int k = j;
					int cnt = 0;
					while (k % i == 0) {
						k /= i;
						cnt++;
					}
					list[j].put(i, cnt);
				}
				answer[i] = 1;
			} else {
				answer[i] = 1;
				for (Entry<Integer, Integer> e : list[i].entrySet()) {
					answer[i] *= (e.getValue() + 1);
				}
			}
		}

		System.out.println("끝");
		long afterT = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long diff = (afterT - beforeT) / 1000; // 두 시간에 차 계산
		System.out.println("시간차이(s) : " + diff);

	}

}
