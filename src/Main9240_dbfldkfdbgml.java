import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main9240_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		ArrayList<Point> points = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0, x = 0, y = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			points.add(new Point(x, y));
		}

		bw.write(rotatingCalipers(points) + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	private static String rotatingCalipers(ArrayList<Point> points) {
		Point temp = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		for (int i = 0; i < points.size(); i++) {
			if (isSmaller(temp, points.get(i))) {
				temp = points.get(i);
			}
		}
		final Point minPoint = temp;

		Collections.sort(points, new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				int ccw = ccw(minPoint, p1, p2);
				// 반시계방향이므로 그대로 쓴다.
				if (ccw == 1) {
					return -1;
				} else if (ccw == -1) {
					return 1;
				} else {
					long dist1 = getDistance(minPoint, p1);
					long dist2 = getDistance(minPoint, p2);
					if (dist1 < dist2) {
						return -1;
					} else if (dist1 > dist2) {
						return 1;
					} else {
						return 0;
					}
				}
			}

		});

		ArrayList<Point> stack = new ArrayList<Point>();
		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), p) <= 0) {
				stack.remove(stack.size() - 1);
			}
			stack.add(p);
		}

		// 회전하는 캘리퍼스
		int pt = 1;
		long ret = 0;
		for (int i = 0; i < stack.size(); i++) {
			ret = Math.max(ret, getDistance(stack.get(i), stack.get((pt) % stack.size())));
			while (pt < stack.size() && getDistance(stack.get(i), stack.get(pt)) <= getDistance(stack.get(i), stack.get((pt + 1) % stack.size()))) {
				ret = Math.max(ret, getDistance(stack.get(i), stack.get((pt + 1) % stack.size())));
				pt++;
			}
		}
		return Math.sqrt(ret) + "";
	}

	private static boolean check(Point p1, Point p2, Point p3, Point p4) {
		Point t1 = new Point(p2.x - p1.x, p2.y - p1.y);
		Point t2 = new Point(p4.x - p3.x, p4.y - p3.y);

		return ccw(new Point(0, 0), t1, t2) >= 0;
	}

	protected static long getDistance(Point p0, Point p1) {
		long ret = (long) (p0.x - p1.x) * (long) (p0.x - p1.x) + (long) (p0.y - p1.y) * (long) (p0.y - p1.y);
		return ret;
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