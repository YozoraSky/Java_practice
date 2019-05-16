package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import cc.openhome.DES;

public class MAC_Test {

	public static void main(String[] args) throws Exception{
		String a = "4567890123456789";
		String b = "0000000000000000";
		String key = "40C23D5DC8F21F43";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<a.length();i++) {
			int x = Integer.parseInt(a.substring(i, i+1),16);
			int y = Integer.parseInt(b.substring(i, i+1),16);
			sb.append(Integer.toHexString(x^y));
		}
		System.out.println(sb.toString());
		
		byte[] cipherText = DES._EncryptForMac(hexToByte(sb.toString()), hexToByte(key));
		System.out.println(bToHex(cipherText).toString());
		byte[] plainText = DES._DecryptForMac(cipherText, hexToByte(key));
		System.out.println(bToHex(plainText).toString());
//		byte a = (byte) 128;
//		byte b = 3;
//		System.out.println((a & 0xff) ^ b);
	}
    
    private static byte[] hexToByte(String isoMessage) {
    	//運算後的位元組長度:16進位數字字串長/2
    	byte[] byteOut = new byte[isoMessage.length()/2];
    	for(int i=0;i<isoMessage.length();i+=2)
    		//每2位16進位數字轉換為一個10進位整數
    		byteOut[i/2] = (byte) Integer.parseInt(isoMessage.substring(i, i+2),16);
    	return byteOut;
    }
    
    private static StringBuilder bToHex(byte[] bArray) {
    	StringBuilder builder = new StringBuilder();
    	for(int i=0;i<bArray.length;i++) {
    		if(Integer.toHexString(bArray[i] & 0xff).length()==1)
    			builder.append("0" + Integer.toHexString(bArray[i] & 0xff).toUpperCase());
    		else
    			builder.append(Integer.toHexString(bArray[i] & 0xff).toUpperCase());
    	}
    	return builder;
    }
}
