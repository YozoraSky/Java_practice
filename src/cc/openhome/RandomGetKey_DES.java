package cc.openhome;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class RandomGetKey_DES {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
	      KeyGenerator desGen = KeyGenerator.getInstance("DES");
	      SecretKey desKey = desGen.generateKey();
	      SecretKeyFactory desFactory = SecretKeyFactory.getInstance("DES");
	      DESKeySpec desSpec = (DESKeySpec) desFactory.getKeySpec(desKey, javax.crypto.spec.DESKeySpec.class);
	      byte[] rawDesKey = desSpec.getKey();
	      System.out.println(bToHex(rawDesKey).toString());
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
