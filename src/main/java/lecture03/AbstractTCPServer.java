package lecture03;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractTCPServer {
    private final int port;
    private final int threads;

    protected AbstractTCPServer(int port, int threads) {
        this.port = port;
        this.threads = threads;
    }

    public int getPort() {
        return port;
    }

    public int getThreadsNumber() {
        return threads;
    }

    public void start() {
    	// java abstraction for connection to some port
        try (ServerSocket serverSocket = new ServerSocket(port);
             ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> handleConnection(clientSocket));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't start server", e);
        }
    }

    private void handleConnection(Socket clientSocket) {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            handleConnection(inputStream, outputStream);
        } catch (SocketTimeoutException e) {
            System.err.println("Read timeout for connection from client " + clientSocket.getRemoteSocketAddress());
        } catch (IOException e) {
            throw new IllegalStateException("Can't handle connection from client " + clientSocket.getRemoteSocketAddress(), e);
        }
    }

    protected abstract void handleConnection(InputStream inputStream, OutputStream outputStream) throws IOException;
}