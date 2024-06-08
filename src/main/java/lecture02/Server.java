package lecture02;

import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12346)) {
        	while(true) {
        	byte[] data = new byte[1024];
        	//the object than recives the data and stores it down in the array of byte
        	// request Packet
        	DatagramPacket packet = new DatagramPacket(data, data.length);
        	
        	socket.receive(packet);
        	
            byte[] request = packet.getData();
            String content = new String(request, 0, packet.getLength(), StandardCharsets.UTF_8);
            System.out.println("Client connected from " + packet.getSocketAddress());
            System.out.println("Request from client " + content);
            
            DatagramPacket responsePacket = new DatagramPacket(request, packet.getLength(), packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
        	}
        	
        	} catch (SocketException e) {
        		throw new RuntimeException(e);
        	} catch (IOException e) {
        		e.printStackTrace();
		}
    }
}
