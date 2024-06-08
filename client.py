import socket

with socket.create_connection(("localhost", 12345)) as s:  # Change the variable name to avoid conflict
    while True:  # Use 'True' for readability
        message = input("Enter your message: ")  # Change the variable name to avoid conflict with the import
        s.sendall(message.encode('utf-8'))  # Use the correct socket variable name
        result = s.recv(1024)
        print(f"Your message is '{result.decode('utf-8')}'")

# the client will not be able to connect with the server because my code can not manage more than one connection
"""
public class Server {
    public static void main(String[] args) {
        // Connection to the port of this program
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            // here will the server get some user data socket that will represent the server port 
            // 1. waited until the client connects to the server
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            while (true) {
                // 2. waited until the client sends something
                int available = is.available();
                if (available > 0) {
                    // 3. scanned the message
                    byte[] bytes = is.readNBytes(available);
                    String request = new String(bytes, StandardCharsets.UTF_8);
                    // 4. decoding
                    System.out.println("Request from client" + request);
                    // 5. somehow formatting executed (not good one)
                    String response = request + System.lineSeparator();
                    // 6. Send the response back
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                    // under similar principals some server-program that takes some requests and can some send back are made
                }
            }
            
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
"""