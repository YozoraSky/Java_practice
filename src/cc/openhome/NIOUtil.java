package cc.openhome;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class NIOUtil {
	public static void dump(ReadableByteChannel src, WritableByteChannel dest) throws IOException{
		ByteBuffer buff = ByteBuffer.allocate(1024);
		try(src;dest){
			while(src.read(buff) != -1) {
				buff.flip();
				dest.write(buff);
				buff.clear();
			}
		}
	}
}
