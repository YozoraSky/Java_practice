package cc.openhome;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	public static void main(String args[]) throws Exception {
		String key = "4F6947A77C96DEAB";
		String u = "csdpprd01";
		String p = "XX3ZVwFxXX";
		System.out.println(_DecryptByDES("Nfdkbv7bj4YHULsIsz3TVWcOycsowFMh/itA0YoJdYTcD6M0MxCuWA==",key));
		System.out.println(_DecryptByDES("neGp4b95sTeNEZfIyuruGQ==",key));
	}
	public static String _EncryptByDES(String str, String key) throws Exception
	{
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec skey = new SecretKeySpec(hexToByte(key), "DES");
		String rtn = null;
		Cipher cipher;
		cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skey, zeroIv);
		byte[] encryptedData = cipher.doFinal(str.getBytes());
//		rtn = DatatypeConverter.printBase64Binary(encryptedData);
		rtn = new String(Base64.getEncoder().encode(encryptedData));
		return rtn;
	}
	
	public static String _DecryptByDES(String str, String key) throws Exception {
//		byte[] byteMi = DatatypeConverter.parseBase64Binary(str);
		byte[] byteMi = Base64.getDecoder().decode(str);
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec skey = new SecretKeySpec(hexToByte(key), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skey, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData);
	}
	
	public static byte[] _EncryptForMac(byte[] str, byte[] key) throws Exception
	{
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec skey = new SecretKeySpec(key, "DES");
		Cipher cipher;
		cipher = Cipher.getInstance("DES/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skey, zeroIv);
		byte[] encryptedData = cipher.doFinal(str);
		return encryptedData;
	}
	
	public static byte[] _DecryptForMac(byte[] str, byte[] key) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec skey = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, skey, zeroIv);
		byte[] decryptedData = cipher.doFinal(str);
		return decryptedData;
	}
	
    private static byte[] hexToByte(String key) {
    	//運算後的位元組長度:16進位數字字串長/2
    	byte[] byteOut = new byte[key.length()/2];
    	for(int i=0;i<key.length();i+=2)
    		//每2位16進位數字轉換為一個10進位整數
    		byteOut[i/2] = (byte) Integer.parseInt(key.substring(i, i+2),16);
    	return byteOut;
    }
}
