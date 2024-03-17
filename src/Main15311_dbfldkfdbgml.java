import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * https://stonejjun.tistory.com/116
 * 이진법으로 풀면 해결되고, 앞에 1을 갖다놓고 적절히 처리하고 뒤에 1000으로 적절히 해결하면 되는 문제
 * @author developer
 *
 */
public class Main15311_dbfldkfdbgml {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		bw.write("2000\n");
		for(int i = 0 ; i < 1000 ; i++) {
			bw.write("1 ");
		}
		for(int i = 0 ; i < 1000 ; i++) {
			bw.write("1000 ");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}

}