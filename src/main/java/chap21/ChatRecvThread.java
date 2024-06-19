package chap21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ChatRecvThread extends Thread {
	Socket 			socket = null;
	InputStream 	is = null;
//	OutputStream 	os =  null;
	BufferedReader br = null;
	BufferedWriter bw = null;
	
	String inMessage = null;
	
	// 생성자: 클라이언트와 접속가능한 소켓을 초기화
	public ChatRecvThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			is = socket.getInputStream();
//			os = socket.getOutputStream();
			br = new BufferedReader(new InputStreamReader(is));
	
			while(true) {
				inMessage = br.readLine();
				
				// 소켓으로 부터 더이상 읽어올 메시지가 없으면 예외처리하여 while빠져나옴
				if (inMessage == null) {
					throw new IOException();
				}
				System.out.println(inMessage);
			}
		} catch (Exception e) {}

	}
}
