import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 이 소스는 종만북에 나온 책을 그냥 보고 함
 * 
 * @author leedongjun
 *
 */
public class MainDARPA2_dbfldkfdbgml {
	
	private static ArrayList<Double> location;
	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			location = new ArrayList<Double>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < M ; i++) {
				location.add(Double.parseDouble(st.nextToken()));
			}
			
			bw.write(String.format("%.2f\n", optimize(N)));
			
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean decision(int cameras, double gap) {
		// 카메라를 설치할 수 있을 때마다 설치하는 탐욕적 알고리즘
		double limit = -1;
		int installed = 0;
		for(int i = 0 ; i < location.size(); i++) {
			if(limit <= location.get(i)) {
				installed++;
				//location.get(i) + gap 이후는 되어야 카메라를 설치할 수 있다.
				limit = location.get(i) + gap;
			}
		}
		return installed >= cameras;
	}
	
	// 최적화 문제 : 정렬되어 있는 locations 중 cameras를 선택해 최소 간격을 최대화하낟.
	private static double optimize(int cameras) {
		double lo = 0, hi = 241;
		for(int i = 0 ; i < 100 ; i++) {
			double mid = (lo + hi) / 2;
			// 간격이 mid 이상이 되도록 할 수 있으면 답은 [mid, hi]에 있다.
			if(decision(cameras, mid)) {
				lo = mid;
			}else {
				hi = mid;
			}
		}
		return lo;
	}
}