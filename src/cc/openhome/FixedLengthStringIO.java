package cc.openhome;
import java.io.*;

public class FixedLengthStringIO {
  /** Read fixed number of characters from a DataInput stream */
  public static String readFixedLengthString(DataInput in) throws IOException {
    return in.readLine();
  }

  /** Write fixed number of characters to a DataOutput stream */
  public static void writeFixedLengthString(String s, int size, DataOutput out) throws IOException {
	  char[] chars = new char[size];

	  // Fill in string with characters
	  s.getChars(0, Math.min(s.length(), size), chars, 0);

	  // Create and write a new string padded with blank characters
	  out.write(new String(chars).getBytes());
	  out.write(" ".getBytes());
  }
  
  public static void writeFixedLengthString(int i, DataOutput out) throws IOException {
	  out.write(String.valueOf(i).getBytes());
	  out.write(" ".getBytes());
  }
}