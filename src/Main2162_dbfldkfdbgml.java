import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2162_dbfldkfdbgml {

	private static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		ArrayList<Line> lineList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x1, y1, x2, y2;
			x1 = Double.parseDouble(st.nextToken());
			y1 = Double.parseDouble(st.nextToken());
			x2 = Double.parseDouble(st.nextToken());
			y2 = Double.parseDouble(st.nextToken());
			lineList.add(new Line(x1, y1, x2, y2));
		}

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (lineIntersects(lineList.get(i), lineList.get(j))) {
					union(i, j);
				}
			}
		}

		int countOfGroup = 0;
		for (int i = 0; i < N; i++) {
			if (i == find(i)) {
				countOfGroup++;
			}
		}
		bw.write(countOfGroup + "\n");

		int[] size = new int[N];
		for (int i = 0; i < N; i++) {
			size[parent[i]]++;
		}
		int maxSize = 0;
		for (int i = 0; i < N; i++) {
			if (size[i] > maxSize) {
				maxSize = size[i];
			}
		}
		bw.write(maxSize + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA < rootB) {
			parent[rootB] = rootA;
		} else {
			parent[rootA] = rootB;
		}
	}

	private static boolean lineIntersects(Line l1, Line l2) {
		return lineIntersects(l1.A, l1.B, l2.A, l2.B);
	}

	private static int find(int i) {
		if (parent[i] == i) {
			return i;
		}
		return parent[i] = find(parent[i]);
	}

	private static class Line {
		Point A, B;

		public Line(double x1, double y1, double x2, double y2) {
			A = new Point(x1, y1);
			B = new Point(x2, y2);
		}

	}

	private static boolean lineIntersects(Point p1, Point p2, Point p3, Point p4) {

		int ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
		int ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

		if (p1.equals(p3) || p1.equals(p4) || p2.equals(p3) || p2.equals(p4)) {
			return true;
		}

		if (ccw1 == 0 && ccw2 == 0) {
			Point t1 = min(p1, p2);
			Point t2 = max(p1, p2);
			Point t3 = min(p3, p4);
			Point t4 = max(p3, p4);
			return !(isSmaller(t2, t3) || isSmaller(t4, t1));
		}

		return ccw1 <= 0 && ccw2 <= 0;
	}

	private static boolean isSmaller(Point p1, Point p2) {
		return p1.x < p2.x || (p1.x == p2.x && p1.y < p2.y);
	}

	private static Point max(Point p1, Point p2) {
		if (isSmaller(p1, p2)) {
			return p2;
		} else {
			return p1;
		}
	}

	private static Point min(Point p1, Point p2) {
		if (isSmaller(p1, p2)) {
			return p1;
		} else {
			return p2;
		}
	}

	private static int ccw(Point p0, Point p1, Point p2) {
		return ccw(p0.x, p0.y, p1.x, p1.y, p2.x, p2.y);
	}

	private static int ccw(double x1, double y1, double x2, double y2, double x3, double y3) {
		double ret = x1 * y2 + x2 * y3 + x3 * y1 - (y1 * x2 + y2 * x3 + y3 * x1);
		if (ret > 0) {
			return 1;
		} else if (ret < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	private static class Point {
		double x, y;

		public Point(double x1, double y1) {
			super();
			this.x = x1;
			this.y = y1;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Point) {
				return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
			} else {
				return false;
			}
		}

	}

}