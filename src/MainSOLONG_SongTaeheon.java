import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MainSOLONG_SongTaeheon {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static Word[] words;
    static String[] input;

    public static void main(String[] args) throws IOException {

        int testCount = readTestCount();

        for (int i = 0; i < testCount; i++) {
            input();
            solve();
        }
    }

    private static void solve() {

        int result = 0;

        // sort 필요
        Arrays.sort(words, Comparator.comparing(Word::getFreq, Comparator.reverseOrder()).thenComparing(Word::getWord));
        
        // insert
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i].word, i);
        }

        for (String s : input) {
            int typeCount = trie.type(s);
            result += typeCount;
        }

        result += M - 1;
        System.out.println(result);
    }

    private static int readTestCount() throws IOException {
        StringTokenizer stTestCount = new StringTokenizer(br.readLine());
        return Integer.parseInt(stTestCount.nextToken()); // C
    }

    private static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N
        M = Integer.parseInt(st.nextToken()); // M

        words = new Word[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            String word = st1.nextToken();
            int freq = Integer.parseInt(st1.nextToken());

            words[i] = new Word(word, freq, i);
        }

        input = new String[M];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            input[i] = st2.nextToken();
        }
    }

    private static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word, int id) { // id는 index
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                int childNum = c - 'A';

                if (current.children[childNum] == null) {
                    current.children[childNum] = new TrieNode();
                }

                current = current.children[childNum];

                if (current.firstId == -1) {
                    current.firstId = id;
                }
            }
        }

        int type(String word) {

            int id = findWord(word);

            if (id == -1) {
                return word.length();
            }

            TrieNode current = root;

            for (int i = 0; i < word.length(); i++) {
                int childNum = word.charAt(i) - 'A';
                if (current.firstId == id) {
                    return i + 1; // + tab key count (1)
                }

                current = current.children[childNum];
            }

            return word.length(); // 끝까지 다 와서 tab안누르는게 나은 경우
        }

        private int findWord(String word) {
            TrieNode current = root;

            for (char c: word.toCharArray()) {
                int childNum = c - 'A';
                if (current.children[childNum] == null) {
                    return -1;
                }
                current = current.children[childNum];
            }
            return current.firstId;
        }
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int firstId = -1;
    }

    private static class Word {
        String word;
        int freq;
        int id;

        public Word(String word, int freq, int id) {
            this.word = word;
            this.freq = freq;
            this.id = id;
        }

        public String getWord() {
            return word;
        }

        public int getFreq() {
            return freq;
        }
    }
}
