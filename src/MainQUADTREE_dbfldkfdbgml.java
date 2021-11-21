import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 그래프 탐색을 성실하게 하였음
 * 0번 자식과 2번 자식의 자리를 바꾸고, 
 * 1번 자식과 3번 자식의 자리를 바꾸었다
 * 
 * @author leedongjun
 *
 */
public class MainQUADTREE_dbfldkfdbgml {

	private static String line;
	private static int index;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 첫 줄에 테스트 케이스의 개수 C (C≤50)가 주어집니다. 
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 0; test_case < T; test_case++) {

			line = br.readLine();
			Node root = new Node();
			index = 0;
			for(int i = 0 ; i < 4 && index < line.length(); i++) {
				char name = line.charAt(index++);
				if(name == 'x') {
					root.nexts[i] = new Node(name);
					treeMaking(root.nexts[i]);
				}else {
					root.nexts[i] = new Node(name);
				}
			}
			// 그래프(트리)를 만들고 나서 0번 자식과 2번 자식의 자리를 바꾸었다
			Node temp = root.nexts[0];
			root.nexts[0] = root.nexts[2];
			root.nexts[2] = temp;
			
			// 그래프(트리)를 만들고 나서 1번 자식과 3번 자식의 자리를 바꾸었다
			temp = root.nexts[1];
			root.nexts[1] = root.nexts[3];
			root.nexts[3] = temp;

			// 자리를 바꾼 다음에는 탐색하여 출력한다.
			// 모든 문자열의 길이는 1,000 이하이며, 
			for(int i = 0 ; i < 4 ; i++) {
				if(root.nexts[i] == null) {
					continue;
				}
				if(root.nexts[i].name == 'x') {
					System.out.print(root.nexts[i].name);
					dfs(root.nexts[i]);
				}else {
					System.out.print(root.nexts[i].name);
				}
			}
			System.out.println();

		}

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void dfs(Node now) {
		for(int i = 0 ; i < 4 ; i++) {
			if(now.nexts[i] == null) {
				return;
			}
			if(now.nexts[i].name == 'x') {
				System.out.print(now.nexts[i].name);
				dfs(now.nexts[i]);
			}else {
				System.out.print(now.nexts[i].name);
			}
		}
		
	}

	/**
	 * now 번째 Node를 찾는다. index 번째 글자를 찾는다.
	 * @param now
	 * @param index
	 */
	private static void treeMaking(Node now) {
		if (index >= line.length()) {
			return;
		}
		for(int i = 0 ; i < 4 ; i++) {
			char name = line.charAt(index++);
			if(name == 'x'){
				Node next = new Node(name);
				now.nexts[i] = next;
				treeMaking(next);
			}else {
				Node next = new Node(name);
				now.nexts[i] = next;
			}
		}
		
		Node temp = now.nexts[0];
		now.nexts[0] = now.nexts[2];
		now.nexts[2] = temp;
		
		temp = now.nexts[1];
		now.nexts[1] = now.nexts[3];
		now.nexts[3] = temp;
		

	}

	private static class Node {
		char name;
		Node[] nexts;

		public Node(char name, MainQUADTREE_dbfldkfdbgml.Node[] nexts) {
			super();
			this.name = name;
			this.nexts = nexts;
		}

		public Node() {
			this.nexts = new Node[4];
		}

		public Node(char name) {
			this.name = name;
			this.nexts = new Node[4];
		}

	}

	

}
