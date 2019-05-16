package cc.openhome;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	public static void dump(InputStream src, OutputStream desc) throws IOException{
		try(BufferedInputStream input = new BufferedInputStream(src);
			BufferedOutputStream output = new BufferedOutputStream(desc)){
			int length;
			byte[] data = new byte[1024];
			while((length = src.read(data)) != -1) {
				desc.write(data,0,length);
			}
		}
	}
}
