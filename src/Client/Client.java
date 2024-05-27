package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private static final String SERVER_IP = "127.0.0.1"; // 서버 IP 주소
	private static final int SERVER_PORT = 7000; // 서버 포트 번호

	public static void main(String[] args) {
		try {
			// 서버에 연결
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 연결 확인 메시지 출력
			System.out.println("Connected to the chat server.");

			// 사용자 이름 입력
			System.out.print("Enter your name: ");
			String name = keyboardReader.readLine();
			out.println("NAME:" + name);

			// 서버로부터 메시지 수신 및 출력
			Thread readThread = new Thread(() -> {
				try {
					String message;
					while ((message = in.readLine()) != null) {
						System.out.println(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			readThread.start();

			// 사용자 입력 메시지 서버로 전송
			String userInput;
			while ((userInput = keyboardReader.readLine()) != null) {
				out.println("MSG:" + userInput);
			}

			// 연결 종료
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
