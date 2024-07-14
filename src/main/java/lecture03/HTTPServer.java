package lecture03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class HTTPServer extends AbstractTCPServer{

	protected HTTPServer(int port, int threads) {
		super(port, threads);
		// TODO Auto-generated constructor stub
	}

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
