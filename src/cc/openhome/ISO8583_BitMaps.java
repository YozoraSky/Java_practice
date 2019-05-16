package cc.openhome;

public class ISO8583_BitMaps {
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		String hex = "303884012881900A";
		String binary;
		int decimal;
		for(int i=0;i<hex.length();i++) {
			decimal = Integer.parseInt(hex.substring(i, i+1), 16);
			binary = Integer.toBinaryString(decimal);
			for(int k=binary.length();k<4;k++)
				binary = "0" + binary;
			builder.append(binary);
		}
		System.out.println(builder.toString());
		char[] c = builder.toString().toCharArray();
		builder.setLength(0);
		for(int i=0;i<c.length;i++) {
			if(c[i] == '1')
				builder.append(i+1+",");
		}
		builder.deleteCharAt(builder.length()-1);
		System.out.println(builder.toString());
	}
}
