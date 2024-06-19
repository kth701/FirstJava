package chap21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {
	
//	private ServerSocket serverSocket = null;
	// 각각의 클라이언트에 메시지를 전달하는 스레드 저장되는 List
	private static List<ChatServerThread> threads  = new ArrayList<ChatServerThread>();
	private Socket socket = null;
	
	InputStream is = null;
	OutputStream os = null;
	
	BufferedReader br = null;
	BufferedWriter bw  = null;
	PrintWriter writer = null;
	
	String message = null;	
	String nickName = null;
	
	// 생성자
	public ChatServerThread(Socket socket, String nickName) {
		this.nickName = nickName;
		this.socket = socket;
		
		threads.add(this);
	}
	
	@Override
	public void run() {
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			bw = new BufferedWriter(new OutputStreamWriter(os));
			writer = new PrintWriter(bw, true);
			writer.print("서버에 접속되었습니다.");
			
			while(true) {
				br = new BufferedReader(new InputStreamReader(is));
				message = br.readLine();
				if (message == null) {
					throw new IOException();
				}
				
				// 현재 클라이언트 접속자의 메시지를 인자로 전달
				sendMessageAll(message);
			}
		} catch (Exception e) {	// TODO: handle exception
		}
	}
	
	public void sendMessageAll(String message) throws Exception {
		
		ChatServerThread thread = null;
		for (int i=0; i< threads.size(); i++) {
			thread = threads.get(i);
			if (thread.isAlive()) { // 해당 스레드의 run()메서드가 종료됐는지 확인
				thread.sendMessage(message);
			}
		}
		
		// 서버 콘솔로 출력
		System.out.println(message);
		
	}
	
	public void sendMessage(String message) throws Exception {
		writer = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())), true);
		writer.print(message);
	}

}
