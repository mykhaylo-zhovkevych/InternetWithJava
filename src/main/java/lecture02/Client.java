package lecture02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) { 
    	try (DatagramSocket socket = new DatagramSocket()) {
    		InetAddress address = InetAddress.getByName("localhost");
    		int port = 12346;
    		while (true)  {
				
				System.out.println("Enter your message: ");
				System.out.print("> ");
				String line = new Scanner(System.in).nextLine();
				if (line.length() > 1024) {
					throw new IllegalArgumentException("too long");
				}
				byte[] request= line.getBytes(StandardCharsets.UTF_8);
				DatagramPacket requestpacket = new DatagramPacket(request, request.length, address, port); 
				socket.send(requestpacket);
				// Response from the server
				byte[] data = new byte[1024];
				DatagramPacket responsePacket = new DatagramPacket(data, data.length);
				socket.receive(responsePacket);
				String response = new String(responsePacket.getData(), 0, responsePacket.getLength(), StandardCharsets.UTF_8);
				System.out.println("Response from server: " + response);
			}
    		
    	} catch (IOException e) {
    		throw new IllegalStateException(e);
    	}
    }
}
