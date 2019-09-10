package Test;

import java.util.UUID;

public class Test {
	public static void main(String[] args) {
		String a = "0000140002413004";
		String b = "06241241FFFFFFFF";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<a.length();i++) {
			int x = Integer.parseInt(a.substring(i, i+1),16);
			int y = Integer.parseInt(b.substring(i, i+1),16);
			sb.append(Integer.toHexString(x^y));
		}
		System.out.println(sb.toString());
	}
}
