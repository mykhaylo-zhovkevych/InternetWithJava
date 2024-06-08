package lecture01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 12345);
		// when the client receives some data from server
		InputStream is = socket.getInputStream();
		// was added to simplify the output 
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		// when the client sends some data to a server
		OutputStream os = socket.getOutputStream()) {
		
			while (true)  {
				// some message is received from user 
				System.out.println("Enter your message: ");
				System.out.print("> ");
				String line = new Scanner(System.in).nextLine();
				
				// the message is send now to the server
				os.write(line.getBytes(StandardCharsets.UTF_8));
				// received some answer from the server
				String response = reader.readLine();
				// and rendered the answer to the user 
				System.out.println("Response from server: " + response);
			}
		
		} catch (IOException e) {
			
			throw new IllegalStateException(e);
		}
	}
}
