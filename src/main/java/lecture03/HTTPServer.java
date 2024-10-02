package lecture03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
<<<<<<< HEAD
import java.nio.charset.StandardCharsets;
=======
>>>>>>> f8d330c3d30c86703150b60fbf90097f0d745154

public class HTTPServer extends AbstractTCPServer{

	protected HTTPServer(int port, int threads) {
		super(port, threads);
		// TODO Auto-generated constructor stub
	}

<<<<<<< HEAD
	// prints the message from the client
	@Override
	protected void handleConnection(InputStream inputStream, OutputStream outputStream) throws IOException {
		
		try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(inputStreamReader)) {
				String line;
				do {
				do {
					line = reader.readLine();
					System.out.println(line);
					// returns null if the String ends
				} while(line != null && !line.isBlank());
				String htmlPage = """
				<html>
				<head>
				</head>
				<title> Nigger </title>
				<body>
				<h1>I hate and like niggers </h1>
				</body>
				</html>
				""";
				String response = new StringBuilder() 
							.append("HTTP/1.1 200 OK\r\n")
							.append("Content-Type: text/html; charset=utf-8\r\n")
							.append("Content-Length: " + htmlPage.getBytes(StandardCharsets.UTF_8).length + "\r\n")
							// this means that the headers are now ended 
							.append("\r\n")
							// body of the message
							.append(htmlPage)
							.toString();
				outputStream.write(response.getBytes(StandardCharsets.UTF_8));
				} while (line != null);
			}	
		}
	}
=======
	@Override
	protected void handleConnection(InputStream inputStream, OutputStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream));
				BufferedReader reader = new BufferedReader(inputStreamReader)) {
				String line;
				do {
					
				}while(line != null)
					reader.readLine();	
				}
	}

}
>>>>>>> f8d330c3d30c86703150b60fbf90097f0d745154
