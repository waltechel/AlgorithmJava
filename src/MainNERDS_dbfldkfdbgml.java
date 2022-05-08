import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 이 문제가 선형 분리 문제인데
 * 1. 퍼셉트론을 활용해서 풀 수 있다.
 * 그런데 내가 생짜로 딥러닝을 구현해보려고 하니까 못하겠어서 못했다
 * 2. 볼록 껍질
 * 볼록 껍질이 구분이 되는지
 * 
 * @author leedongjun
 *
 */
public class MainNERDS_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Point> A = new ArrayList<>();
			ArrayList<Point> B = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (command == 0) {
					A.add(new Point(x, y));
				} else {
					B.add(new Point(x, y));
				}
			}

			// 1. 최하단점을 잡는다.
			final Point criA = getCriPoint(A);

			// 2. 각도 순 정렬합니다.
			pointSort(A, criA);

			// 3. 모노톤 스택을 활용한 그라함 스캔
			final Stack<Point> stackA = getConvexHull(A);

			// 1. 최하단점을 잡는다.
			final Point criB = getCriPoint(B);

			// 2. 각도 순 정렬합니다.
			pointSort(B, criB);

			// 3. 모노톤 스택을 활용한 그라함 스캔
			final Stack<Point> stackB = getConvexHull(B);

			if (convexhullIntersects(stackA, stackB)) {
				bw.write("THEORY IS INVALID\n");
			} else {
				bw.write("THEORY HOLDS\n");
			}

		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean convexhullIntersects(Stack<Point> stackA, Stack<Point> stackB) {
		int N = stackA.size();
		int M = stackB.size();

		// 1. 우선 한 다각형이 다른 다각형에 포함되어 있는 경우를 확인
		if (isInside(stackA.get(0), stackB) || isInside(stackB.get(0), stackA)) {
			return true;
		}
		// 2. 서로 닿는 두 변을 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (lineIntersects(stackA.get(i), stackA.get((i + 1) % N), stackB.get(i), stackB.get((i + 1) % M))) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean lineIntersects(Point p1, Point p2, Point p3, Point p4) {

		int ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
		int ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

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

	private static boolean isInside(Point p, Stack<Point> stack) {
		int crosses = 0;
		for (int i = 0; i < stack.size(); i++) {
			int j = (i + 1) % stack.size();
			if ((stack.get(i).y > p.y) != (stack.get(j).y > p.y)) {
				double atX = (double) (stack.get(j).x - stack.get(i).x) * (p.y - stack.get(i).y) / (stack.get(j).y - stack.get(i).y) + stack.get(i).x;
				if (p.x < atX) {
					crosses++;
				}
			}
		}
		return crosses % 2 > 0;
	}

	private static Stack<Point> getConvexHull(ArrayList<Point> A) {
		Stack<Point> stack = new Stack<Point>();
		for (int i = 0; i < A.size(); i++) {
			while (stack.size() >= 2 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), A.get(i)) <= 0) {
				stack.pop();
			}
			stack.add(A.get(i));
		}
		return stack;
	}

	private static void pointSort(ArrayList<Point> A, Point cri) {
		Collections.sort(A, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				int ccw = ccw(cri, p1, p2);
				if (ccw == 1) {
					return -1;
				} else if (ccw == -1) {
					return 1;
				} else {
					long dist1 = dist(A.get(0), p1);
					long dist2 = dist(A.get(0), p2);
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
	}

	private static Point getCriPoint(ArrayList<Point> A) {
		Point temp = new Point(100000, 100000);
		for (int i = 0; i < A.size(); i++) {
			if (A.get(i).x < temp.x || (A.get(i).x == temp.x && A.get(i).y < temp.y)) {
				temp = A.get(i);
			}
		}
		return temp;
	}

	private static long dist(Point p0, Point p1) {
		return dist(p0.x, p0.y, p1.x, p1.y);
	}

	private static long dist(long x0, long y0, long x1, long y1) {
		return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
	}

	private static int ccw(Point p0, Point p1, Point p2) {
		return ccw(p0.x, p0.y, p1.x, p1.y, p2.x, p2.y);
	}

	private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
		long ret = x1 * y2 + x2 * y3 + x3 * y1 - (y1 * x2 + y2 * x3 + y3 * x1);
		if (ret > 0) {
			return 1;
		} else if (ret < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}