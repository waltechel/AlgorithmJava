import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainTRAVERSAL_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] preArr;
    static int[] inArr;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }

    }

    private static void solve() {
        Node root = makeTree(0, N-1, 0, N-1);
        printPostOrder(root);
    }

    private static void printPostOrder(Node root) {

        if (root == null) return;

        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.print(root.data + " ");
    }

    private static Node makeTree(int preI, int preJ, int inI, int inJ) {
        int rootData = preArr[preI];
        // 중위에서 rootData의 위치를 찾는다.
        int inRootPos;
        for (inRootPos = inI; inRootPos <= inJ; inRootPos++) {
            if (inArr[inRootPos] == rootData) break;
        }
        int leftCount = inRootPos - inI;
        int rightCount = inJ - inRootPos;

        Node left = null;
        Node right = null;

        if (leftCount > 0) {
            left = makeTree(preI + 1, preI + leftCount - 1, inI, inRootPos - 1);
        }

        if (rightCount > 0) {
            right = makeTree(preI + 1 + leftCount, preJ, inRootPos + 1, inJ);
        }

        return new Node(rootData, left, right);
    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        preArr = new int[N];
        inArr = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int index = 0;
        while (st1.hasMoreTokens()) {
            preArr[index] = Integer.parseInt(st1.nextToken());
            index++;
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        index = 0;
        while (st2.hasMoreTokens()) {
            inArr[index] = Integer.parseInt(st2.nextToken());
            index++;
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
