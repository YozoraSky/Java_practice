package Test;

import cc.openhome.ServerThread;

public class SocketServerTest{

    public static void main(String[] argv) {
		ServerThread ms = new ServerThread(7080);
		ms.start();
    }
}
