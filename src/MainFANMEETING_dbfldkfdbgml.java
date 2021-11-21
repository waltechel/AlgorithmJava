import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainFANMEETING_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {

			String members = br.readLine().trim();
			String fans = br.readLine().trim();

			int answer = hugs(members, fans);
			bw.write(answer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static int hugs(String members, String fans) {
		int N = members.length();
		int M = fans.length();
		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			A.add(members.charAt(i) == 'M' ? 1 : 0);
		}

		for (int i = 0; i < M; i++) {
			B.add(fans.charAt(i) == 'M' ? 1 : 0);
		}

		ArrayList<Integer> C = karatsuba(A, B);

		int allHugs = 0;

		for (int i = N - 1; i < M; i++) {
			if (C.get(i) == 0) {
				allHugs++;
			}
		}
		return allHugs;
	}

	private static ArrayList<Integer> karatsuba(ArrayList<Integer> a, ArrayList<Integer> b) {
		// member
		int an = a.size();
		// fan
		int bn = b.size();

		// a가 b보다 짧을 경우 둘을 바꾼다.
		if (an < bn) {
			return karatsuba(b, a);
		}

		// base case : a or b is empty
		if (an == 0 || bn == 0) {
			return new ArrayList<>();
		}

		// base case : a 가 비교적 짧은 경우 O(N^2) multiply로 변경한다.
		if (an <= 50) {
			return multiply(a, b);
		}

		int half = an / 2;
		// a, b divide

		ArrayList<Integer> a0 = new ArrayList<>();
		ArrayList<Integer> a1 = new ArrayList<>();
		ArrayList<Integer> b0 = new ArrayList<>();
		ArrayList<Integer> b1 = new ArrayList<>();

		// z2 = a1 * b1
		ArrayList<Integer> z2 = karatsuba(a1, b1);
		// z0 = a0 * b0
		ArrayList<Integer> z0 = karatsuba(a0, b0);
		// a0 = a0 + a1
		// b0 = b0 + b1
		addTo(a0, a1, 0);
		addTo(b0, b1, 0);
		// z1 = (a0 * b0) - z0 - z2;
		ArrayList<Integer> z1 = karatsuba(a0, b0);

		subFrom(z1, z0);
		subFrom(z1, z2);

		// ret = z0 + z1 * 10^half + z2 * 10^(half*2)
		ArrayList<Integer> ret = new ArrayList<>();
		addTo(ret, z0, 0);
		addTo(ret, z1, half);
		addTo(ret, z2, half + half);
		return ret;
	}

	private static void subFrom(ArrayList<Integer> a, ArrayList<Integer> b) {
		for (int i = 0; i < b.size(); i++) {
			a.set(i, a.get(i) - b.get(i));
		}
		
	}

	private static void addTo(ArrayList<Integer> a, ArrayList<Integer> b, int k) {
		int size = Math.max(a.size() + k, b.size());

		for(int i = a.size() ; i < size ; i++) {
			a.add(0);
		}
		
		for (int i = 0; i < Math.min((int) b.size(), size - k); i++) {
			int n = a.get(i + k);
			a.set(i + k, n + b.get(i));
		}
		
	}

	private static ArrayList<Integer> multiply(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> c = new ArrayList<>(a.size() + b.size() + 1);
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				// 남남일 때만 1이 된다.
				if(i + j < c.size()) {
					int n = c.get(i + j);					
					c.set(i + j, n + a.get(i) * b.get(j));
				}else {
					c.add(i + j, a.get(i) * b.get(j));
				}
			}
		}
		// normalize(c);
		return c;
	}

}