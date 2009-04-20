import java.io.*;
import java.net.*;

class OutBoundChat {
	
	Socket outBoundSocket;
	String hostName;
	int outBoundPort;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	
	OutBoundChat(String host, int sending){
		hostName = host;
		outBoundPort = sending;
		try{
			//1. creating a socket to connect to the server
			outBoundSocket = new Socket(hostName, outBoundPort);
			//2. get Input and Output streams
			out = new ObjectOutputStream(outBoundSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(outBoundSocket.getInputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	boolean isConnected(){
		boolean connected = false;
		sendMessage("ping");
		String message = "";
		try{
			message = (String)in.readObject();
		}catch(IOException ioException){
			System.out.println("Connection Failed");
		}catch(Exception e){
			e.printStackTrace();
		}
		if(message.equals("ping")){
			connected = true;
		}
		return connected;
	}
	
	public void closeConnection(){
		try{
			in.close();
			out.close();
			outBoundSocket.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	protected void finalize() throws Throwable {
		this.closeConnection();
	}
}
