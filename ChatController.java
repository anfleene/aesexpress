import java.io.*;


public class ChatController{
	String messageIn;
	InBoundChat inBound;
	OutBoundChat outBound;
	
	ChatController(String hostname, int inBoundPort, int outBoundPort){
		this.inBound = new InBoundChat(inBoundPort);
		this.outBound = new OutBoundChat(hostname, outBoundPort);
	}
	
	public String getMsg(){
		return this.messageIn;
	}
	
	public void receiveMsg(){
		String input = "";
		try{
			input = inBound.in.readUTF();
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		EncryptedString eString = new EncryptedString(input, true);
		try{
			eString.decrypt();
		}catch (Exception e){
			e.printStackTrace();
		}
		this.messageIn = eString.toString();
	}
	
	public void sendMsg(String msg){
		EncryptedString eString = new EncryptedString(msg);
		try{
		outBound.out.writeUTF(eString.toString());
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public static void main(String[] args){

	}
	
	protected void finalize() throws Throwable {
		this.inBound.closeConnection();
		this.outBound.closeConnection();
	}

}
