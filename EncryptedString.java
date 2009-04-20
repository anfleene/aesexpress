	import javax.crypto.*;
	import javax.crypto.spec.SecretKeySpec;
	import java.io.*;
	import java.security.NoSuchAlgorithmException;
	import org.apache.commons.codec.binary.Base64; 
	 

class EncryptedString {
	private SecretKeySpec keySpec;
	private byte[] bytes;
	private String string;
	private boolean encrypted;
	
	EncryptedString(String arg, boolean encrypt){
		this.encrypted = encrypt;
		this.bytes = arg.getBytes();
		try{
			System.out.println(new String(this.bytes, "utf-8"));
		}catch (Exception e){
			e.printStackTrace();
		}
		this.string = arg;
		if (encrypted){
			try{
				keySpec = getKeySpec();
				this.encrypt();
			}catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	EncryptedString(String arg){
		this.encrypted = false;
		this.bytes = arg.getBytes();
		this.string = arg;
		try{
			keySpec = getKeySpec();
			this.encrypt();
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	
	EncryptedString(){
		this.bytes = null;
		this.string = "";
		this.encrypted = false;
		try{
			keySpec = getKeySpec();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public SecretKeySpec getKeySpec() throws IOException, NoSuchAlgorithmException {
		byte[] bytes = new byte[16];
	    File f = new File("aes_key");
	    SecretKey key = null;
	    SecretKeySpec spec = null;
	    if (f.exists()) {
	      new FileInputStream(f).read(bytes);
	    } else {
	       KeyGenerator kgen = KeyGenerator.getInstance("AES");
	       kgen.init(128);
	       key = kgen.generateKey();
	       bytes = key.getEncoded();
	       new FileOutputStream(f).write(bytes);
	    }
	    spec = new SecretKeySpec(bytes,"AES");
	    return spec;
	  }
	  public void encrypt() throws Exception {
		  if(!this.encrypted){
	    	Cipher cipher = Cipher.getInstance("AES");
	    	cipher.init(Cipher.ENCRYPT_MODE, keySpec);
	    	byte[] base64Bytes = Base64.encodeBase64(this.bytes);
	    	byte[] encryptedBytes = cipher.doFinal(base64Bytes);
	    	 this.bytes = Base64.encodeBase64(encryptedBytes);
	    	this.string = new String(this.bytes, "utf-8");
	    	this.encrypted = true;
	    	try{
				System.out.println(new String(this.bytes, "utf-8"));
			}catch (Exception e){
				e.printStackTrace();
			}
		  }
	  }
	  public void decrypt() throws Exception {
		  if(encrypted){
			SecretKeySpec spec = getKeySpec();
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] B64bytes = Base64.decodeBase64(this.bytes);
			byte[] decrypted = cipher.doFinal(B64bytes);
			this.bytes = Base64.decodeBase64(decrypted);
			this.string = new String(this.bytes, "utf-8");
			this.encrypted = false;
		  }
	  }
	  
	  public String toString(){
		  String returnString;
		  try{
			  returnString = new String(this.bytes, "utf-8");
		  }catch(Exception e){
			  returnString = "ERROR";
		  }
		  return returnString;
	  }
	  public static void main(String[] args) throws Exception {
	    String text = "deerrrr";
	    EncryptedString eString = new EncryptedString(text);
	    String encrypted = eString.toString();
	    System.out.println("s1: EncryptedString.toString(): " + eString);
	    eString.decrypt();
	    System.out.println("s1: DecryptedString.toString(): " + eString);
	    EncryptedString eString2 = new EncryptedString(encrypted, true);
	    System.out.println("s2: EncryptedString.toString(): " + eString2);
	    eString2.decrypt();
	    System.out.println("s2: DecryptedString.toString(): " + eString2);
	  }	 
	}
