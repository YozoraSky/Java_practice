package cc.openhome;

import java.io.IOException;
import java.net.Socket;
import java.util.TimerTask;

public class MonitorPort extends TimerTask{
	
	@Override
	public void run() {
		Socket socket = null;
		String ip = "127.0.0.1";
		int port = 9080;
		try {
			socket = new Socket(ip,port);
			System.out.println("Server is running on port " + port);
			socket.close();
		}
		catch(IOException e) {
			System.out.println("No server on port " + port);
		}
		
		if(socket!=null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
