package Test;

import java.util.Base64;

public class Base64_Test {
	public static void main(String[] args) throws Exception{
		byte[] e = Base64.getEncoder().encode("CSDP_IVR_TEST#1".getBytes());
		System.out.println(new String(e));
		byte[] d = Base64.getDecoder().decode("Q1NEUF9JVlJfVEVTVCMx");
		System.out.println(new String(d));
	}
}
