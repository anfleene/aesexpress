	import javax.crypto.*;
	import javax.crypto.spec.SecretKeySpec;
	import java.io.*;
	import java.security.NoSuchAlgorithmException;
	import org.apache.commons.codec.binary.Base64; 
	 

class EncryptedString {
	private SecretKeySpec keySpec;
	private byte[] bytes;
	EncryptedString(String arg){
		try{
			keySpec = getKeySpec();
			this.encrypt(arg);
		}catch (Exception e) {
	        e.printStackTrace();
	        return;
	    }
	}
	EncryptedString(){
		this.bytes = null;
		try{
			keySpec = getKeySpec();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public SecretKeySpec getKeySpec() throws IOException, NoSuchAlgorithmException {
		byte[] bytes = new byte[16];
	    File f = new File("sample_aes_key");
	    SecretKey key = null;
	    SecretKeySpec spec = null;
	    if (f.exists()) {
	      new FileInputStream(f).read(bytes);
	    } else {
	       KeyGenerator kgen = KeyGenerator.getInstance("AES");
	       kgen.init(256);
	       key = kgen.generateKey();
	       bytes = key.getEncoded();
	       new FileOutputStream(f).write(bytes);
	    }
	    spec = new SecretKeySpec(bytes,"AES");
	    return spec;
	  }
	  public void encrypt(String arg) throws Exception {
	    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
	    	cipher.init(Cipher.ENCRYPT_MODE, keySpec);
	    	byte[] argBytes = arg.getBytes();
	    	byte[] base64Bytes = Base64.encodeBase64(argBytes);
	    	this.bytes = cipher.doFinal(base64Bytes);
	  }
	  public String decrypt() throws Exception {
			SecretKeySpec spec = getKeySpec();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] B64bytes  = cipher.doFinal(this.bytes);
			byte[] decrypted = Base64.decodeBase64(B64bytes);
			return new String(decrypted, "ASCII");
	  }
	  
	  public String toString(){
		  String returnString;
		  try{
			  returnString = new String(this.bytes, "ASCII");
		  }catch(Exception e){
			  returnString = "ERROR";
		  }
		  return returnString;
	  }
	  public static void main(String[] args) throws Exception {
	    String text = "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE";
	    EncryptedString eString = new EncryptedString(text);
	    String decrypted = eString.decrypt();
	    System.out.println(decrypted);
	    System.out.println(eString);
	  }
	}
