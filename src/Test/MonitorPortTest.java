package Test;

import java.util.Timer;

import cc.openhome.MonitorPort;

public class MonitorPortTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new MonitorPort(), 3000, 5000);
	}
}
