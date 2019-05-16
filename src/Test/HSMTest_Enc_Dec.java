package Test;

import static java.lang.System.out;

import cc.openhome.Decrypt;
import cc.openhome.Encrypt;

public class HSMTest_Enc_Dec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String plaintext = "床前明月光，疑似地上霜。舉頭望明月，低頭吃便當。";
		String[] ciphertext = new String[1];
		String[] plaintextA = new String[1];
		Encrypt.Enc(0, "des2", "des2", "1234", plaintext, ciphertext);
		out.println(ciphertext[0]);
		Decrypt.Dec(0, "des2", "des2", "1234", plaintextA, ciphertext[0]);
		out.println(plaintextA[0]);
	}

}
