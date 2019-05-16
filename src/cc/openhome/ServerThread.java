package cc.openhome;

import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerThread extends Thread{
	private static Socket socketChannel;
	private ServerSocket serverSocket;
	public ServerThread(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//建立伺服器端的Socket，並且設定Port
	}
	
	@Override
	public void run() {
		while(true) {
		System.out.println("等待連線......");
		try {
			String result = "";
			socketChannel = serverSocket.accept();
			System.out.println("連線成功！");
			BufferedOutputStream output = new BufferedOutputStream(socketChannel.getOutputStream());
			BufferedInputStream input = new BufferedInputStream(socketChannel.getInputStream());
			byte[] dataByte = new byte[1024];
			int length = -1;
//			while((length = input.read(dataByte))!=-1) {
//				result += new String(dataByte,0,length,"big5");
//			}
			length = input.read(dataByte);
			result += new String(dataByte,0,length);
			System.out.println("Client: " + result);
			String out = "hello";
			output.write(out.getBytes());
			output.flush();//清空緩衝區並送出資料
			socketChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//等待伺服器端的連線，若未連線則程式一直停在這裡
	}
	}
}
