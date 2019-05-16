package cc.openhome;
import java.util.Arrays;
import java.util.Base64;

import safenet.jcprov.CKR_Exception;
import safenet.jcprov.CK_ATTRIBUTE;
import safenet.jcprov.CK_BBOOL;
import safenet.jcprov.CK_C_INITIALIZE_ARGS;
import safenet.jcprov.CK_MECHANISM;
import safenet.jcprov.CK_OBJECT_HANDLE;
import safenet.jcprov.CK_SESSION_HANDLE;
import safenet.jcprov.Cryptoki;
import safenet.jcprov.CryptokiEx;
import safenet.jcprov.LongRef;
import safenet.jcprov.constants.CKA;
import safenet.jcprov.constants.CKF;
import safenet.jcprov.constants.CKK;
import safenet.jcprov.constants.CKM;
import safenet.jcprov.constants.CKO;
import safenet.jcprov.constants.CKU;
import safenet.jcprov.constants.CK_KEY_TYPE;
import safenet.jcprov.constants.CK_OBJECT_CLASS;

public class Encrypt {
	/*
	 * @keytype : one of (des, des2, des3, rsa)
	 * @keyname : name (label) of the key to delete
 	 * @slotId : slot containing the token to delete the key from - default (0)
 	 * @password : user password of the slot. if you don't need to login. Please input 0 to replace it.
 	 * @plaintext :
 	 * @ciphertext : empty string array. len = 1
	 */
    public static boolean Enc(long slotId, String keyType, String keyName, String password, 
    				   String plaintext, String[] ciphertext) {
    	CK_SESSION_HANDLE session = new CK_SESSION_HANDLE();
    	boolean bPrivate = false;
    	byte[] bCiphertext = null;
    	byte[] bPlaintext;
    	try{
            CryptokiEx.C_Initialize(new CK_C_INITIALIZE_ARGS(CKF.OS_LOCKING_OK));
            CryptokiEx.C_OpenSession(slotId, CKF.RW_SESSION, null, null, session);
            
    		if(plaintext.getBytes().length %8 != 0) {
    			bPlaintext = Arrays.copyOf(plaintext.getBytes(), (plaintext.getBytes().length / 8 + 1)*8);
    		}
    		else
    			bPlaintext = plaintext.getBytes();
            
            if (!password.equals("0")){
                CryptokiEx.C_Login(session, CKU.USER, password.getBytes(), password.length());
                bPrivate = true;
            }

            if (keyType.equalsIgnoreCase("des")){
                CK_OBJECT_HANDLE hKey = null;
                hKey = findKey(session, CKO.SECRET_KEY, CKK.DES, keyName, bPrivate);

                if (!hKey.isValidHandle()){
                	System.out.println("des key (" + keyName + ") not found");
                    return false;
                }
                bCiphertext = symetricEncDec(session, hKey, new CK_MECHANISM(CKM.DES_ECB), bPlaintext);
//              jdk8/10
                ciphertext[0] = Base64.getEncoder().encodeToString(bCiphertext);
//              jdk6/7  
//              ciphertext[0] = DatatypeConverter.printBase64Binary(bCiiphertext);
            }
            
            else if (keyType.equalsIgnoreCase("des2")){
                CK_OBJECT_HANDLE hKey = null;
                hKey = findKey(session, CKO.SECRET_KEY, CKK.DES2, keyName, bPrivate);

                if (!hKey.isValidHandle()){
                	System.out.println("des2 key (" + keyName + ") not found");
                    return false;
                }
                bCiphertext = symetricEncDec(session, hKey, new CK_MECHANISM(CKM.DES3_ECB), bPlaintext);
//              jdk8/10
                ciphertext[0] = Base64.getEncoder().encodeToString(bCiphertext);
//              jdk6/7  
//              ciphertext[0] = DatatypeConverter.printBase64Binary(bCiiphertext);
            }
            
            else if (keyType.equalsIgnoreCase("des3")){
                CK_OBJECT_HANDLE hKey = null;
                hKey = findKey(session, CKO.SECRET_KEY, CKK.DES3, keyName, bPrivate);

                if (!hKey.isValidHandle())
                {
                	System.out.println("des3 key (" + keyName + ") not found");
                    return false;
                }
                bCiphertext = symetricEncDec(session, hKey, new CK_MECHANISM(CKM.DES3_ECB), bPlaintext);
//              jdk 8/10 
                ciphertext[0] = Base64.getEncoder().encodeToString(bCiphertext);
//              jdk6/7  
//              ciphertext[0] = DatatypeConverter.printBase64Binary(bCiiphertext);
            }
            
            else if (keyType.equalsIgnoreCase("rsa")){
                CK_OBJECT_HANDLE hPublicKey = null;
                CK_OBJECT_HANDLE hPrivateKey = null;

                hPublicKey = findKey(session, CKO.PUBLIC_KEY, CKK.RSA, keyName, false);

                if (!hPublicKey.isValidHandle()){
                    System.out.println("rsa public key (" + keyName + ") not found");
                    return false;
                }

                hPrivateKey = findKey(session, CKO.PRIVATE_KEY, CKK.RSA, keyName, bPrivate);

                if (!hPrivateKey.isValidHandle()){
                	System.out.println("rsa private key (" + keyName + ") not found");
                    return false;
                }
                bCiphertext = asymetricEncDec(session, hPublicKey, hPrivateKey, new CK_MECHANISM(CKM.RSA_PKCS), bPlaintext);
//              jdk8/10
                ciphertext[0] = Base64.getEncoder().encodeToString(bCiphertext);
//              jdk6/7  
//              ciphertext[0] = DatatypeConverter.printBase64Binary(bCiiphertext);
            }
            else
            {
                System.out.println("keyType is error!");
                return false;
            }
        }
        catch (CKR_Exception ex){
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally{
            Cryptoki.C_Logout(session);
            Cryptoki.C_CloseSession(session);
            Cryptoki.C_Finalize(null);
        }
		return true;
    }
    private static CK_OBJECT_HANDLE findKey(CK_SESSION_HANDLE session,
            CK_OBJECT_CLASS keyClass,
            CK_KEY_TYPE keyType,
            String keyName,
            boolean bPrivate)
    {
    	/* array of one object handles */
    	CK_OBJECT_HANDLE[] hObjects = {new CK_OBJECT_HANDLE()};

    	/* to receive the number of objects located */
    	LongRef objectCount = new LongRef();

    	/* setup the template of the object to search for */
    	CK_ATTRIBUTE[] template =
    	{
    			new CK_ATTRIBUTE(CKA.CLASS,     keyClass),
    			new CK_ATTRIBUTE(CKA.KEY_TYPE,  keyType),
    			new CK_ATTRIBUTE(CKA.TOKEN,     CK_BBOOL.TRUE),
    			new CK_ATTRIBUTE(CKA.LABEL,     keyName.getBytes()),
    			new CK_ATTRIBUTE(CKA.PRIVATE,   new CK_BBOOL(bPrivate))
    	};

    	CryptokiEx.C_FindObjectsInit(session, template, template.length);

    	CryptokiEx.C_FindObjects(session, hObjects, hObjects.length, objectCount);

    	CryptokiEx.C_FindObjectsFinal(session);

    	if (objectCount.value == 1){
    		/* return the handle of the located object */
    		return hObjects[0];
    	}
    	else{
    		/* return an object handle which is invalid */
    		return new CK_OBJECT_HANDLE();
    	}
    }
    
    private static byte[] symetricEncDec(CK_SESSION_HANDLE session,
            CK_OBJECT_HANDLE hKey,
            CK_MECHANISM mechanism,
            byte[] bPlaintext)
    {
    	byte[] bCiphertext = null;
    	LongRef lRefEnc = new LongRef();

    	/* get ready to encrypt */
    	CryptokiEx.C_EncryptInit(session, mechanism, hKey);

    	/* get the size of the cipher text (may be larger than actually
    	 * required)
    	 */
    	CryptokiEx.C_Encrypt(session, bPlaintext, bPlaintext.length, null, lRefEnc);

    	/* allocate space */
    	bCiphertext = new byte[(int)lRefEnc.value];

    	/* encrypt */
    	CryptokiEx.C_Encrypt(session, bPlaintext, bPlaintext.length, bCiphertext, lRefEnc);
    	
    	/*Reduce excess length*/
    	bCiphertext = Arrays.copyOf(bCiphertext, bCiphertext.length-8);
    	
    	return bCiphertext;
    }
    
    private static byte[] asymetricEncDec(CK_SESSION_HANDLE session,
            CK_OBJECT_HANDLE hPublicKey,
            CK_OBJECT_HANDLE hPrivateKey,
            CK_MECHANISM mechanism,
            byte[] bPlaintext)
    {
    	byte[] bCiphertext = null;
    	LongRef lRefEnc = new LongRef();

    	/* get ready to encrypt */
    	CryptokiEx.C_EncryptInit(session, mechanism, hPublicKey);

    	/* get the size of the cipher text */
    	CryptokiEx.C_Encrypt(session, bPlaintext, bPlaintext.length, null, lRefEnc);

    	/* allocate space */
    	bCiphertext = new byte[(int)lRefEnc.value];

    	/* encrypt */
    	CryptokiEx.C_Encrypt(session, bPlaintext, bPlaintext.length, bCiphertext, lRefEnc);
    	
    	/*Reduce excess length*/
    	bCiphertext = Arrays.copyOf(bCiphertext, bCiphertext.length-8);
    	
    	return bCiphertext;
    }
}
