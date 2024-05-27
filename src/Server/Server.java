package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	private static final int PORT = 5001;
    private static Vector<PrintWriter> clientWriters = new Vector<>();

    private ServerFrame serverFrame;

    public Server() {
        // 서버 프레임 생성
        serverFrame = new ServerFrame(this); // Server 객체를 전달하여 ServerFrame 생성
        // 서버 프레임 표시
        serverFrame.setVisible(true);
    }

    public void startServer() { // 서버 시작 메서드 추가
        System.out.println("Server started....");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // 클라이언트로부터 이름 받기
                String nameMessage = in.readLine();
                if (nameMessage != null && nameMessage.startsWith("NAME:")) {
                    String clientName = nameMessage.substring(5);
                    broadcastMessage("해당 서버에 입장 : " + nameMessage + "님");
                } else {
                    // 약속가 다르게 접근 했다면 종료처리
                    socket.close();
                    return;
                }

                clientWriters.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received : " + message);

                    String[] parts = message.split(":", 2);
                    String command = parts[0];
                    String data = parts.length > 1 ? parts[1] : "";

                    if (command.equals("MSG")) {
                        System.out.println("연결된 전체 사용자에게 MSG 방송");
                        broadcastMessage(message);
                    } else if (command.equals("BYE")) {
                        System.out.println("client disconnected....");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    clientWriters.remove(out);
                    System.out.println("........클라이언트 연결 해제........");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(); // Server 객체 생성
        server.startServer(); // 서버 시작
    }

}


