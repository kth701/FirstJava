package chap21;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerTest {

	public static void main(String[] args) {
		// 채팅 서버 프로그램
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
//		InputStream is = null;
		OutputStream os = null;
		
//		BufferedReader br = null;
		BufferedWriter bw  = null;
		
		PrintWriter writer = null;
		
		InetAddress clientIP = null;
		String inMessage = null;//  클라이언트로 부터 문자 받기	
		String outMessage = null;// 클라이언트로 문자 보내기
		
		Scanner sc = new Scanner(System.in);
		
	
		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("서버 실행 중...");
			
			socket = serverSocket.accept();
			
			//is = socket.getInputStream();
			
			// 수신 문자 콘솔에 출력
			ChatRecvThread rThread = new ChatRecvThread(socket);
			rThread.start();
			
			os = socket.getOutputStream();
			
	
			// 송수신에 사용 할 입출력 버퍼
			//br = new BufferedReader(new InputStreamReader(is));
			bw = new BufferedWriter(new OutputStreamWriter(os));
	
		
			writer = new PrintWriter(bw, true);
			writer.println("서버: 접속을 환영합니다. 메시지를 먼저 보내세요.");
			
			
			clientIP = socket.getInetAddress();// 클라이언트 IP주소 추출
			System.out.println("접속 IP: "+clientIP);
			
			// 소켓에 정보를 보내기, 받기
			while(true) {
				// 수신 문자 콘솔에 출력
//				inMessage = br.readLine();
//				System.out.println(inMessage);
				
				outMessage = sc.nextLine();
				if (outMessage.equals("exit")) {
					break;
				}
				
				System.out.println("서버: "+outMessage);
				writer.println("서버: "+ outMessage);
			}
			
			sc.close();
			writer.close();
			socket.close();
		
			
		} catch (Exception e) {}
		

	}

}
