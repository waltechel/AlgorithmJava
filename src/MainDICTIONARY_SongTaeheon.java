import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainDICTIONARY_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String[] input;
    static int[][] order;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            boolean res = makeOrder();

            if (res) {
                printOrder();
            } else {
                System.out.println("INVALID HYPOTHESIS");
            }
        }

    }

    private static void printOrder() {
        CharOrder[] charOrders = new CharOrder[26];
        for (int i = 0; i < 26; i++) {
            int sum = 0;
            for (int j = 0; j < 26; j++) {
                sum += order[i][j]; // sum이 크면 우선순위가 높은거!
            }

            charOrders[i] = new CharOrder((char) (i + 'a'), sum);
        }

        Arrays.sort(charOrders, Comparator.comparing(CharOrder::getSum).reversed());

        for (CharOrder co : charOrders) {
            System.out.print(co.c);
        }
        System.out.println();
    }

    private static boolean makeOrder() {

        boolean isOk;

        for (int i = 0; i < N - 1; i++) {
            int front = input[i].charAt(0) - 'a';
            int back = input[i+1].charAt(0) - 'a';

            int minLength = Math.min(input[i].length(), input[i + 1].length());

            int index;

            for (index = 0; index < minLength; index++) {
                front = input[i].charAt(index) - 'a';
                back = input[i+1].charAt(index) - 'a';

                if (front != back) break;
            }

            if (index >= minLength) continue; // ex) abc, abcd

//            System.out.println((char) (front+'a') + " -> " + (char) (back+'a'));
            isOk = setRelation(front, back);
            if (!isOk) return false;
        }

        return true;
    }

    private static boolean setRelation(int front, int back) {

        if (order[front][back] == -1 || order[back][front] == 1) return false;
        if (order[front][back] == 1 && order[back][front] == -1) return true;
        boolean isOk = true;

        order[front][back] = 1; // order[i][j] == 1이면 i -> j
        order[back][front] = -1; // order[i][j] == -1이면 j <- i

        for (int orderIndex = 0; orderIndex < 26; orderIndex++) {

            if (orderIndex == front || orderIndex == back) continue;

            if (order[orderIndex][front] == 1) { // orderIndex -> front -> back
                isOk = setRelation(orderIndex, back); // orderIndex -> back
            } else if (order[back][orderIndex] == 1) { // front -> back -> orderIndex
                isOk = setRelation(front, orderIndex); // front -> orderIndex
            }
            if (!isOk) return false;
        }
        return true;
    }


    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N

        input = new String[N];
        order = new int[26][26];

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            input[i] = st1.nextToken();
        }
    }

    private static class CharOrder {
        char c;
        int sum;

        public CharOrder(char c, int sum) {
            this.c = c;
            this.sum = sum;
        }

        public int getSum() {
            return sum;
        }
    }
}