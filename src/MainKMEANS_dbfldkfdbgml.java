import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/** 
 * 대충 짜본 Kmeans 알고리즘
 * @author leedongjun
 *
 */
public class MainKMEANS_dbfldkfdbgml {

	private static final int DEFAULT_VALUE = 100_000;
	private static final int REPEAT = 1000;
	private static Point[] points;
	private static int[] answers;
	private static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		Random r = new Random();
		points = new Point[DEFAULT_VALUE];
		for (int i = 0; i < DEFAULT_VALUE; i++) {
			points[i] = new Point(r.nextDouble() * DEFAULT_VALUE, r.nextDouble() * DEFAULT_VALUE);
		}

		answers = new int[K];
		for (int i = 0; i < K; i++) {
			int index = r.nextInt(DEFAULT_VALUE);
			boolean isDuplicated = false;
			for (int j = 0; j < K; j++) {
				if (answers[j] == index) {
					isDuplicated = true;
				}
			}
			if (!isDuplicated) {
				answers[i] = index;
			}
		}

		double prev = 0.0;
		for (int i = 0; i < REPEAT; i++) {
			ArrayList<Integer>[] cluster = new ArrayList[K];
			for (int j = 0; j < K; j++) {
				cluster[j] = new ArrayList<>();
			}

			for (int j = 0; j < DEFAULT_VALUE; j++) {
				Point p = points[j];
				int index = getBestCentroidOfAnswers(p);
				cluster[index].add(j);
			}

			for (int j = 0; j < K; j++) {
				int candi = getNextCentroidCandiofCluster(cluster[j]);
				answers[j] = candi;
			}
			
			double totalDistance = getTotalDistance(cluster);
			if(Math.abs(totalDistance - prev) > 0.00000001) {
				printAnswer(cluster, totalDistance);				
				prev = totalDistance;
			}else {
				System.out.println(i + "번째 반복만에 끝");
				break;
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void printAnswer(ArrayList<Integer>[] cluster, double distance) {
		System.out.print("distance is : " + String.format("%.3f", distance) + ", ");
		for (Integer answer : answers) {
			System.out.print(points[answer] + ", ");
		}
		System.out.println();
	}

	private static double getTotalDistance(ArrayList<Integer>[] cluster) {
		double distance = 0.0;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < cluster[i].size(); j++) {
				distance += getDistance(points[answers[i]], points[cluster[i].get(j)]);
			}
		}
		
		return Math.sqrt(distance);
	}

	private static int getNextCentroidCandiofCluster(ArrayList<Integer> cluster) {

		double averX = 0.0, averY = 0.0;
		for (Integer e : cluster) {
			averX += points[e].x;
			averY += points[e].y;
		}
		averX /= cluster.size();
		averY /= cluster.size();

		int retIndex = getBestCentroidOfCluster(new Point(averX, averY), cluster);

		return retIndex;
	}

	private static int getBestCentroidOfCluster(Point p, ArrayList<Integer> cluster) {
		int retIndex = 0;
		double retValue = Double.MAX_VALUE;
		for (Integer index : cluster) {
			double distance = getDistance(p, points[index]);
			if (Double.compare(distance, retValue) < 0) {
				retValue = distance;
				retIndex = index;
			}
		}
		return retIndex;
	}

	private static int getBestCentroidOfAnswers(Point p) {
		int retIndex = 0;
		double retValue = Double.MAX_VALUE;
		for (int index = 0; index < K; index++) {
			double distance = getDistance(p, points[answers[index]]);
			if (Double.compare(distance, retValue) < 0) {
				retValue = distance;
				retIndex = index;
			}
		}
		return retIndex;
	}

	private static double getDistance(Point p1, Point p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

	private static class Point {
		double x, y;

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(x=" + String.format("%.2f", x) + ", y=" + String.format("%.2f", y) + ")";
		}

	}

}