import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class MainFORTRESS_SongTaeheon {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Wall rootWall;
    private static int result;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            int h = height(rootWall);
            result = Math.max(h, result);
            System.out.println(result);
        }
    }

    private static int height(Wall wall) {

        int childLength = wall.getChildWalls().size();

        if (childLength == 0) return 0;

        int[] heights = new int[childLength];

        int max1 = 0;
        int max2 = 0;
        for (int i = 0; i < childLength; i++) {
            heights[i] = height(wall.getChildWalls().get(i));

            if (max1 < heights[i]) {
                max2 = max1;
                max1 = heights[i];
            } else if (max2 < heights[i]) {
                max2 = heights[i];
            }
        }

        if (childLength == 1) {
//            result = Math.max(result, heights[0] + 1);
            return heights[0] + 1;
        }

        result = Math.max(result, max1 + max2 + 2);
        return max1 + 1;
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        StringTokenizer stRoot = new StringTokenizer(br.readLine());
        int rootX = Integer.parseInt(stRoot.nextToken());
        int rootY = Integer.parseInt(stRoot.nextToken());
        int rootR = Integer.parseInt(stRoot.nextToken());

        rootWall = new Wall(rootX, rootY, rootR, 0);

        for (int i = 1; i < N; i++) {
            StringTokenizer stWall = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stWall.nextToken());
            int y = Integer.parseInt(stWall.nextToken());
            int z = Integer.parseInt(stWall.nextToken());

            Wall wall = new Wall(x, y, z, i);
            rootWall.addWall(wall);
        }
    }

    private static class Wall {
        private int x;
        private int y;
        private int r;
        private int index;

        private List<Wall> childWalls = new ArrayList<>();

        public Wall(int x, int y, int r, int index) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.index = index;
        }

        public void addWall(Wall newWall) {
            if (!hasIncluded(newWall)) throw new RuntimeException("포함되지 않는 벽입니다.");

            Iterator<Wall> itr = childWalls.iterator();
            while (itr.hasNext()) {
                Wall child = itr.next(); // must be called before you can call i.remove()

                if (child.hasIncluded(newWall)) {
                    child.addWall(newWall);
                    return;
                }

                if (newWall.hasIncluded(child)) {
                    newWall.addWall(child);
                    itr.remove();
                }
            }

            childWalls.add(newWall);
        }

        // 중심이 포함되면, 원 자체가 포함됨. (벽은 겹치거나 만나지 않기 때문)
        public boolean hasIncluded(Wall wall) {
            return this.r > wall.getR() && distanceSquareFrom(wall) < Math.pow(this.r - wall.getR(), 2);
        }

        private double distanceSquareFrom(Wall wall) {
            return Math.pow(x - wall.x, 2) + Math.pow(y - wall.y, 2);
        }

        public List<Wall> getChildWalls() {
            return childWalls;
        }

        public int getIndex() {
            return index;
        }

        public int getR() {
            return r;
        }
    }
}
