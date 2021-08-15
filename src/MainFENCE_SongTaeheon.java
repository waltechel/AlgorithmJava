import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainFENCE_SongTaeheon {
    private static final int MAX_HEIGHT = 10001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] heights;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {

        int maxArea = Arrays.stream(heights).max().orElse(0);
        int area = findMaxArea(0, N - 1);

        int result = Math.max(maxArea, area);
        System.out.println(result);

    }

    private static int findMaxArea(int leftBound, int rightBound) {
        int maxArea = 0;
        int mid = (leftBound + rightBound) / 2;
        int minHeight = MAX_HEIGHT;

        if (leftBound >= rightBound) return 0;

        int left = mid;
        int right = mid + 1;

        while(true) {
            minHeight = minOf(minHeight, heights[left], heights[right]);

            int tempArea = minHeight * (right - left + 1);
            if (tempArea > maxArea) {
                maxArea = tempArea;
            }

            if (left - 1 < leftBound && right + 1 > rightBound) break;

            if (left - 1 >= leftBound && (right + 1 > rightBound || heights[left-1] > heights[right + 1] )) {
                left -= 1;
            } else if (right + 1 <= rightBound && (left - 1 < leftBound || heights[left-1] <= heights[right + 1])) {
                right += 1;
            }
        }

        int leftArea = findMaxArea(leftBound, mid);
        int rightArea = findMaxArea(mid + 1, rightBound);

        maxArea = maxOf(maxArea, leftArea, rightArea);

        return maxArea;
    }

    private static int minOf(int value1, int value2, int value3) {
        int result = Math.min(value1, value2);
        result = Math.min(result, value3);
        return result;
    }

    private static int maxOf(int value1, int value2, int value3) {
        int result = Math.max(value1, value2);
        result = Math.max(result, value3);
        return result;
    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer stN = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stN.nextToken()); // N

        StringTokenizer stHeights = new StringTokenizer(br.readLine());
        heights = new int[N];

        int index = 0;
        while(stHeights.hasMoreTokens()) {
            heights[index] = Integer.parseInt(stHeights.nextToken());
            index += 1;
        }

    }
}
