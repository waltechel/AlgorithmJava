import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainTREASURE_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
			int N = Integer.parseInt(st.nextToken());

			ArrayList<int[]> island = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				island.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
			}

			ArrayList<double[]> polygon = intersection(rectangle, island);

//			printTest(polygon);

			bw.write(String.format("%.10f\n", area(polygon)));

//			ArrayList<double[]> testData = new ArrayList<>();
//			testData.add(new double[] {2, 0});
//			testData.add(new double[] {5, 3});
//			testData.add(new double[] {2, 6});
//			testData.add(new double[] {-1, 3});
//			
//			bw.write(String.format("%.10f\n", area(testData)));
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void printTest(ArrayList<double[]> polygon) {
		for (double[] p : polygon) {
			System.out.println(Arrays.toString(p));
		}
	}

	private static double areaTest(ArrayList<int[]> island) {
		ArrayList<double[]> polygon = new ArrayList<double[]>();
		for (int[] p : island) {
			polygon.add(new double[] { p[0], p[1] });
		}
		return area(polygon);
	}

	private static ArrayList<double[]> intersection(Rectangle rectangle, ArrayList<int[]> island) {
		ArrayList<double[]> ret = new ArrayList<double[]>();
		for (int i = 0; i < island.size(); i++) {
			int[] p1 = island.get(i);
			int[] p2 = island.get((i + 1) % island.size());

			if (!isInside(p1, rectangle) && !isInside(p2, rectangle)) {
				ArrayList<double[]> temp = new ArrayList<>();
				for (int j = 0; j < rectangle.getAllPoints().size(); j++) {
					int[] p3 = rectangle.getAllPoints().get(j);
					int[] p4 = rectangle.getAllPoints().get((j + 1) % rectangle.getAllPoints().size());
					if (isIntersect(p1, p2, p3, p4)) {
						double[] p5 = calculateIntersect(p1, p2, p3, p4);
						if(!has(ret, p5)) {
							temp.add(p5);							
						}
					}
				}
				Collections.sort(temp , (o1, o2) -> Double.compare(dist(new double[] {p1[0], p1[1]}, o1), dist(new double[] {p1[0], p1[1]}, o2)));
				ret.addAll(temp);
			} else if (!isInside(p1, rectangle) && isInside(p2, rectangle)) {
				ArrayList<double[]> temp = new ArrayList<>();
				for (int j = 0; j < rectangle.getAllPoints().size(); j++) {
					int[] p3 = rectangle.getAllPoints().get(j);
					int[] p4 = rectangle.getAllPoints().get((j + 1) % rectangle.getAllPoints().size());
					if (isIntersect(p1, p2, p3, p4)) {
						double[] p5 = calculateIntersect(p1, p2, p3, p4);
						if(!has(ret, p5)) {
							temp.add(p5);							
						}
					}
				}
				Collections.sort(temp , (o1, o2) -> Double.compare(dist(new double[] {p1[0], p1[1]}, o1), dist(new double[] {p1[0], p1[1]}, o2)));
				ret.addAll(temp);
				if(!has(ret, p2)) {
					ret.add(new double[] { p2[0], p2[1] });					
				}
			} else if (isInside(p1, rectangle) && isInside(p2, rectangle)) {
				if(!has(ret, p2)) {
					ret.add(new double[] { p2[0], p2[1] });
				}
			} else if (isInside(p1, rectangle) && !isInside(p2, rectangle)) {
				ArrayList<double[]> temp = new ArrayList<>();
				for (int j = 0; j < rectangle.getAllPoints().size(); j++) {
					int[] p3 = rectangle.getAllPoints().get(j);
					int[] p4 = rectangle.getAllPoints().get((j + 1) % rectangle.getAllPoints().size());
					if (isIntersect(p1, p2, p3, p4)) {
						double[] p5 = calculateIntersect(p1, p2, p3, p4);
						if(!has(ret, p5)) {
							temp.add(p5);							
						}
					}
				}
				Collections.sort(temp , (o1, o2) -> Double.compare(dist(new double[] {p1[0], p1[1]}, o1), dist(new double[] {p1[0], p1[1]}, o2)));
				ret.addAll(temp);
			}

		}
		return ret;
	}
	
	private static double dist(double[] p0, double[] p1) {
		return (p0[0] - p1[0]) * (p0[0] - p1[0]) + (p0[1] - p1[1]) * (p0[1] - p1[1]) ;
	}

	private static boolean has(ArrayList<double[]> ret, int[] p5) {
		return has(ret, new double[] {p5[0], p5[1]});
	}

	private static boolean has(ArrayList<double[]> ret, double[] p5) {
		for (double [] p : ret) {
			if(Math.abs(p[0] - p5[0]) < 0.000000001 && Math.abs(p[1] - p5[1]) < 0.00000001) {
				return true;
			}
		}
		return false;
	}

	private static double[] getIntersectOf(int[] p1, int[] p2, MainTREASURE_dbfldkfdbgml.Rectangle rectangle) {
		for (int j = 0; j < rectangle.getAllPoints().size(); j++) {
			int[] p3 = rectangle.getAllPoints().get(j);
			int[] p4 = rectangle.getAllPoints().get((j + 1) % rectangle.getAllPoints().size());
			if (isIntersect(p1, p2, p3, p4)) {
				return calculateIntersect(p1, p2, p3, p4);
			}
		}
		return null;
	}

	private static boolean isIntersect(int[] p1, int[] p2, int[] p3, int[] p4) {
		int ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
		int ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

		if (ccw1 == 0 && ccw2 == 0) {
			int[] t1 = min(p1, p2);
			int[] t2 = max(p1, p2);
			int[] t3 = min(p3, p4);
			int[] t4 = max(p3, p4);
			return !(isSmaller(t2, t3) || isSmaller(t4, t1));
		}

		return ccw1 <= 0 && ccw2 <= 0;
	}

	private static int[] max(int[] p1, int[] p2) {
		if (isSmaller(p1, p2)) {
			return new int[] { p2[0], p2[1] };
		} else {
			return new int[] { p1[0], p1[1] };
		}
	}

	private static int[] min(int[] p1, int[] p2) {
		if (isSmaller(p1, p2)) {
			return new int[] { p1[0], p1[1] };
		} else {
			return new int[] { p2[0], p2[1] };
		}
	}

	private static boolean isSmaller(int[] p1, int[] p2) {
		return p1[0] != p2[0] ? p1[0] < p2[0] : p1[1] < p2[1];
	}

	private static int ccw(int[] p1, int[] p2, int[] p3) {
		return ccw(p1[0], p1[1], p2[0], p2[1], p3[0], p3[1]);
	}

	private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		long ret = (long) ((x2 - x1) * (y3 - y1)) - (long) ((y2 - y1) * (x3 - x1));
		if (ret > 0)
			return 1;
		else if (ret < 0)
			return -1;
		else
			return 0;

	}

	private static boolean isInside(int[] point, Rectangle rectangle) {
		ArrayList<int[]> points = rectangle.getTwoPoints();
		return points.get(0)[0] <= point[0] && point[0] <= points.get(1)[0] && points.get(0)[1] <= point[1] && point[1] <= points.get(1)[1];
	}

	private static double[] calculateIntersect(int[] p1, int[] p2, int[] p3, int[] p4) {
		return calculateIntersect(p1[0], p1[1], p2[0], p2[1], p3[0], p3[1], p4[0], p4[1]);
	}

	private static double[] calculateIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		double px = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
		px /= ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		double py = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
		py /= ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		return new double[] { px, py };
	}

	private static double area(ArrayList<double[]> polygon) {
		double ret = 0.0f;
		for (int i = 0; i < polygon.size(); i++) {
			ret += polygon.get(i)[0] * polygon.get((i + 1) % polygon.size())[1];
		}
		for (int i = 0; i < polygon.size(); i++) {
			ret -= polygon.get(i)[1] * polygon.get((i + 1) % polygon.size())[0];
		}
		return ret / 2;
	}

	private static class Rectangle {

		private ArrayList<int[]> allPoints;
		private ArrayList<int[]> twoPoints;

		public Rectangle(int x1, int y1, int x2, int y2) {
			super();
			allPoints = new ArrayList<>();
			allPoints.add(new int[] { x1, y1 });
			allPoints.add(new int[] { x2, y1 });
			allPoints.add(new int[] { x2, y2 });
			allPoints.add(new int[] { x1, y2 });

			twoPoints = new ArrayList<>();
			twoPoints.add(new int[] { x1, y1 });
			twoPoints.add(new int[] { x2, y2 });
		}

		public ArrayList<int[]> getAllPoints() {
			return allPoints;
		}

		public ArrayList<int[]> getTwoPoints() {
			return twoPoints;
		}

	}

}