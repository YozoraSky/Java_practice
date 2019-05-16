package Test;

import cc.openhome.SocketChannel;

public class SocketClientTest {
	public static void main(String[] args) {
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				SocketChannel socket1 = new SocketChannel("127.0.0.1",7080);
				socket1.sendAndReceive();
				System.out.println("thread1 end");
			}
		};
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				SocketChannel socket2 = new SocketChannel("127.0.0.1",7080);
				socket2.sendAndReceive();
				System.out.println("thread2 end");
			}
		};
		Thread thread3 = new Thread() {
			@Override
			public void run() {
				SocketChannel socket3 = new SocketChannel("127.0.0.1",7080);
				socket3.sendAndReceive();
				System.out.println("thread3 end");
			}
		};
		thread1.start();
		thread2.start();
		thread3.start();
		SocketChannel socket4 = new SocketChannel("127.0.0.1",7080);
		socket4.sendAndReceive();
		System.out.println("thread4 end");
	}
}
