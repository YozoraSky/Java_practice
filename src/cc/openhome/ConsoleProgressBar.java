package cc.openhome;

public class ConsoleProgressBar {
	private static String start = "0% [                                                                                                    ]";
	private static int max = 155292;
	private static int min = 0;
	public static void main(String[] args) throws InterruptedException {
//		int maxGrid = start.subSequence(start.indexOf("[")+1, start.indexOf("]")).length();
//		System.out.print(start);
//		for(int j=1;j<=maxGrid;j++) {
//			for(int i=0;i<start.length()+2;i++) {
//				System.out.print("\b");
//			}
//			System.out.print((int)((float)j/maxGrid*100) + "%");
//			System.out.print(" [");
//			
//			for(int i=1;i<=j;i++) {
//				System.out.print("=");
//			}
//			if(j!=maxGrid)
//				System.out.print(">");
//			for(int i=maxGrid-j;i>0;i--) {
//				System.out.print(" ");
//			}
//			System.out.print("]");
//			Thread.sleep(1000);
//		}
		int count = 0;
		while(count<max) {
			count+=1000;
			printConsoleBar(count);
		}
	}
	
	public static void printConsoleBar(int currentLine) {
		int maxGrid = start.subSequence(start.indexOf("[")+1, start.indexOf("]")).length();
//		System.out.print(start);
		for(int i=0;i<start.length()+2;i++) {
			System.out.print("\b");
		}
		int precent = currentLine * 100/max;
		System.out.print(precent + "%");
		System.out.print(" [");
		min = precent;
		for(int i=0;i<min;i++) {
			System.out.print("=");
		}
		if(min<maxGrid && min>0)
			System.out.print(">");
		for(int i=maxGrid-min;i>0;i--) {
			System.out.print(" ");
		}
		System.out.print("]");
	}
}
