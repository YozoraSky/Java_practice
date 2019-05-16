package cc.openhome;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Novel{
	public static void main(String[] args) throws IOException {
    	String str;
    	
    	@SuppressWarnings("resource")
		BufferedReader bre = new BufferedReader(new FileReader("C:/enjoy/novel.txt"));
    	String hotbox;
    	while ((str = bre.readLine())!= null)
    	{
    		final int lineLength = 95;
    		int count = str.length() / lineLength;
    		count++;
    		for(int i=1;i<=count;i++) {
    			if(i*lineLength>str.length())
    				out.println(str.substring((i-1)*lineLength, str.length()));
    			else
    				out.println(str.substring((i-1)*lineLength, i*lineLength));
    		}
    	}
//    	URL url = new URL("https://ck101.com/forum.php?mod=viewthread&tid=4658573&extra=page%3D1");
//    	InputStream src = url.openStream();
//    	OutputStream desc = new FileOutputStream("C:/enjoy/test.txt");
//    	IO.dump(src, desc);
    }
}