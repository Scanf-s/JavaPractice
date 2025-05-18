import java.io.*;
import java.net.*;
import java.util.concurrent.*;


/**
 * 멀티 쓰레드 서버 클래스
 * Port : 22897
 * 여러개의 Thread를 사용해서 Thead pool을 만들고, 딱 3개의 Thread만 사용하는 방식으로 구현했습니다. (4개 연결 시 하나는 대기해야함)
 */
public class MultiThreadedServer {
    public static void main(String[] args) {
        final int port = 22897;
        final int maxThreads = 3;

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxThreads); // Thread pool 생성

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(port + "에서 서버 실행 중...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 클라이언트 연결 Accept (TCP HANDSHAKE)

                if (threadPool.getActiveCount() >= maxThreads) {
                    System.out.println("모든 스레드가 사용 중입니다. 클라이언트는 대기 중입니다...");
                    continue;
                }

                System.out.println("현재 연결된 클라이언트 수: " + (Thread.activeCount() - 1)); // -1은 서버 소켓을 제외하기 위함
                System.out.println("클라이언트 연결됨 : " + clientSocket.getInetAddress());

                threadPool.submit(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }
}
