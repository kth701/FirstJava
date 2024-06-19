package chap21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientTest {
	
	public static void main(String[] args) {
		
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		
		BufferedReader br = null;
		BufferedWriter bw  = null;
		PrintWriter writer = null;
		
		String inMessage = null;//  서버로 부터 문자 받기	
		String outMessage = null;// 서버로 문자 보내기
		
		
		try {
			//socket = new Socket("localhost", 8888);
			//socket = new Socket("127.0.0.1", 8888);
			
			socket = new Socket("192.168.0.12", 8888);
			is =  socket.getInputStream();
			os = socket.getOutputStream();
			
			Scanner sc = new Scanner(System.in);
			
			// 송수신에 사용 할 입출력 버퍼
			br = new BufferedReader(new InputStreamReader(is));
			bw = new BufferedWriter(new OutputStreamWriter(os));
			writer = new PrintWriter(bw, true);
			
			while(true) {
				// 서버로 부터 전송받은 문자 확인
				inMessage = br.readLine();
				System.out.println(inMessage);
				
				outMessage = sc.nextLine();
				if (outMessage.equals("exit")) {
					break;
				}
				
				writer.print("클라이언트:"+outMessage);
			}
			
			sc.close();writer.close();socket.close();
			
			
		} catch (Exception e) {}
	}

}
