import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainBRACKETS2_dbfldkfdbgml {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int TEST = Integer.parseInt(br.readLine());
		for (int test = 0; test < TEST; test++) {
			String line = br.readLine();
			Stack<Character> stack = new Stack<>();
			boolean flag = true;
			for(int i = 0 ; i < line.length() ; i++) {
				char c = line.charAt(i);
				if(c == '(') {
					stack.add('(');
				}else if(c == '{') {
					stack.add('{');
				}else if(c == '[') {
					stack.add('[');
				}else if(c == ']') {
					if(stack.isEmpty()) {
						flag = false;
						break;
					}else if(stack.pop() == '[') {
						
					}else {
						flag = false;
						break;
					}
				}else if(c == ')') {
					if(stack.isEmpty()) {
						flag = false;
						break;
					}else if(stack.pop() == '(') {
						
					}else {
						flag = false;
						break;
					}
				}else if(c == '}') {
					if(stack.isEmpty()) {
						flag = false;
						break;
					}else if(stack.pop() == '{') {
						
					}else {
						flag = false;
						break;
					}
				}
			}
			if(stack.isEmpty()) {
				
			}else {
				flag = false;
			}
			
			if(flag) {
				bw.write("YES\n");
			}else {
				bw.write("NO\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	
}
