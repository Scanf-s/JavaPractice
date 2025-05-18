import java.io.*;
import java.net.*;

/**
 * 클라이언트 요청을 처리하는 쓰레드 생성 및 요청 핸들러 클래스
 * 클라이언트와의 연결을 관리하고, 메시지 송수신을 처리한다.
 */
public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader rcv = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter send = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            String inputLine;
            while ((inputLine = rcv.readLine()) != null) {
                System.out.println("클라이언트로부터 수신받은 메세지: " + inputLine);
                if ("quit()".equalsIgnoreCase(inputLine.trim())) {
                    System.out.println("클라이언트 연결 종료 요청.");
                    break;
                }
                send.println("서버의 응답: " + inputLine + " (ECHO)");
            }
        } catch (IOException e) {
            System.err.println("클라이언트 처리 중 오류 발생: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("클라이언트 연결 종료됨.");
            } catch (IOException e) {
                System.err.println("소켓 종료 오류: " + e.getMessage());
            }
        }
    }
}
