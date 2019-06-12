package cc.openhome;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	public static void main(String args[]) throws Exception {
		String UUID = java.util.UUID.randomUUID().toString();
		String key = "4F6947A77C96DEAB";
		String u = "csdpprd01";
		String p = "XX3ZVwFxXX";
		String s = "INSERT INTO Mock_IVRDetailLog1 ([RecordId] ,[CallId] ,[ConnectionId] ,[CustomerId] ,[ANI] ,[DNIS] ,[StartTime] ,[SystemId] ,[EndFunctionId] ,[FunctionId] ,[Machine] ,[LogMessage] ,[ProcessDate]) VALUES (NEWID() ,'B2565000-DC29-2D4B-FCD9-129FC5920838' ,'00a702ddecec7b7c' ,'' ,'6601' ,'6423907' ,'2019-05-29 09:56:43.183' ,'BANK' ,'Mainflow_B','Mainflow_B' ,'172.24.34.65(scsdpcbta01)' ,'==執行Mainflow_B，手機進線(MediaType=voice)' ,getdate())";
		String ss = _EncryptByDES(s,key);
		System.out.println(ss);
		System.out.println(_DecryptByDES("2h3dzVgcKubou5ktTHj5KYUcs07/EODoQx4gdsvNyNZavg4r6qmgdTgh9exfpb5P5sDEx95+K4bKvBjLSpL6clwHoiyCJrK7c4s8T/CKtd6bH5kg27+xUH8sHKDunFakDy4nK2RBlLuDgvF0YRJzccZOeazPxWjujz//8JyIDDnp4znYoBHnw6oyWDZBTuSTLiSFgnmFMysgPAFanWqQFHMIYYP5PGRHz7eTUyeY/tLh/rLXQMmJQUF19PaAtX/+0XgG2ba19TiDDVfEQ2ZPFj/HBHrHDUfKjvXbDFDrvAVAd3fXpxw/39uUkPzI//2vUOMCRcFlt+kc3Rjo1i1H4rdoKfYWbiBXNlMxFVydTnBAx3X9uwrrhSgb9tna/k/0keU0jSk3jwMvKhUbRnIVfFsxNDqnv98NHUFijoWvDRl6KoPqz1aKvJziphHE9gmP91cXBXdIU1T2SsH8MH/dcGLFGciqP01f09cJxxdp01lS/mliSjAut+A0dEtlfmg8kpbGwlPcJMQ4ix9KuJ6Z8P6Ln33Whrw+u3dprjeBOzY1ugBlvQaenvJ8nsXh8V6j6ZHvBbY0aZPY9Gxl0VfQo7QoZ1+9QWuVhEqMEK02YKLLCEGQ6n5uMwfbvExkx4sRbcINB5WdimY=",key));
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
