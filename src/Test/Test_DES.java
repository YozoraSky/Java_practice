package Test;

import cc.openhome.DES;

public class Test_DES {

	public static void main(String[] args) throws Exception {
		String s1 = "csdpprd01";
		String s2 = "XX3ZVwFxXX";
//		System.out.println(DES._EncryptByDES(s1, "skjfoiwe"));
		System.out.println(DES._DecryptByDES("BeBz0XntSSC7PLyVwNw1Mg==", "skjfoiwe"));
//		System.out.println(DES._EncryptByDES(s2, "skjfoiwe"));
		System.out.println(DES._DecryptByDES("90AsyLco+6PEyYYjJhzzKw==", "skjfoiwe"));
	}

}
