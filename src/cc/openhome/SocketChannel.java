package cc.openhome;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketChannel{
	private Socket socket;
	private String status;
	public SocketChannel(String ip, int port) {
		try {
			socket = new Socket(ip,port);
			setStatus("success");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setStatus("fail");
		}
	}
	
	public String sendAndReceive() {
		String str;
		String result = "";
		try {
			if(socket!=null) {
				BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
				output.write("iii llllove u~".getBytes());
				output.flush();
				for(int i=0;i<10000;i++) {
					
				}
//				socket.shutdownOutput();
				BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
				byte[] dataByte = new byte[1024];
				int length = -1;
//				while((length = input.read(dataByte))!=-1) {
//					result += new String(dataByte,0,length,"big5");
//				}
				length = input.read(dataByte);
				result += new String(dataByte,0,length);
				System.out.println(result);
				input.close();
				socket.close();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
