package lecture01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        // Connect to the port of this program
        try (ServerSocket serverSocket = new ServerSocket(12345);
        		
        	// allows multi-connection
        	ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            // the loop allows after one connection is established serve to it after that it will return to this loop (the loop is infinite) 
            while (true) {
            	Socket accept = serverSocket.accept();
            	// Wait until the client connects to the server
            	executorService.submit(() -> handelConnection(accept));
                
            }
        } catch (IOException e) {
            throw new IllegalStateException("Server error: " + e.getMessage(), e);
        }
    }
    
    
    private static void handelConnection(Socket clientSocket) {
    try (Socket socket = clientSocket;
            InputStream is = socket.getInputStream();
    		// the BufferedReader read out the data in his buffer and cause of it mark can be done
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    		
            OutputStream os = socket.getOutputStream()) {
            
           // Print the port number of the connected client
           System.out.println("Connected to client on port: " + socket.getPort());
           
           // this will work only until the client is connected
           // not isConnected will return true if at least one connection was established
           // not isClosed will stop work any when i write it manually socket.stop() 
           
      /* A reason why committed out it need knowledge of the TCP and UDP and some code that make rules */
           
//           //!!! in the default socket InputStream get roll back is not possible
//           
//           // on the Stream the flow-position will try to memorize if we read more than one byte the setting will be reseted
//           reader.mark(1);
//           // than one byte will be read if we cound not read it it means connection is closed 
//           while (reader.read() != -1) {
//        	   // but if not -1 than i will Stream move back to the previous position
//        	   reader.reset();
//               // Wait until the client sends something
//               int available = is.available();
//               if (available > 0) {
//                   handleRequest(reader, available + 1, os);
//                   reader.mark(1);
//               }
//           }
           System.out.println("Client connected from " + socket.getRemoteSocketAddress());
           char[] chars = new char[1024];
           int read;
           // will help not to close the connection after the first message
           do {
        	   
           read = reader.read(chars);
           //reading the data from the reader to the array of the chars 
           if (read != -1) {
        	   // making new copy of array but  the length will be the amount of the signs that was taken from inputStream
        	   char[] content = Arrays.copyOf(chars, read);
        	   handleRequest(content, os);
           }
       } while(read >=0);
           
           
       } catch (IOException e) {
           System.err.println("Client connection error: " + e.getMessage());
           throw new IllegalStateException(e);
       }
   }

   private static void handleRequest(//Reader reader, int available, 
		   char[] chars, OutputStream os) throws IOException {
       // Read the message sent by the client
//       char[] chars = new char[available];
//	   reader.read(chars);
       String request = new String(chars);

       // Print the client's request
       System.out.println("Request from client: " + request);

       // Create a response by appending a line separator
       String response = request + System.lineSeparator();

       // Send the response back to the client
       os.write(response.getBytes(StandardCharsets.UTF_8));

       // Typically, server programs will process requests and send back responses under similar principles
   }
}

