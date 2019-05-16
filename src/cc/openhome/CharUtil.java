package cc.openhome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CharUtil {
	public static void dump(Reader src, Writer desc) throws IOException{

//寫法一:
		try(BufferedReader input = new BufferedReader(src);desc){
			char[] data = new char[1024];
			int length = -1;
			while((length = input.read(data)) != -1) {
				desc.write(data,0,length);
			}
		}

//寫法二:
//		try(BufferedReader input = new BufferedReader(src);
//			BufferedWriter output = new BufferedWriter(desc)){
//			char[] data = new char[1024];
//			int length = -1;
//			while((length = input.read(data)) != -1) {
//				output.write(data,0,length);
//			}
//		}
		
////寫法三:
//		try(BufferedReader input =new BufferedReader(src) ;
//			BufferedWriter output = new BufferedWriter(desc)){
//			String line;
//			while((line = input.readLine())!=null) {
//				output.write(line);
//				output.newLine();
//			}
//		}
		
//寫法四:
//		BufferedReader通常會搭配PrintWriter一起使用，因為PrintWriter有println(),print(),format()等方法。
//		PrintWriter不只可以接收Writer，也可以接收OutputStream
//		try(BufferedReader input =new BufferedReader(src) ;
//			PrintWriter output = new PrintWriter(desc)){
//				String line;
//				while((line = input.readLine())!=null) {
//					output.println(line);
//				}
//			}
	}
}
