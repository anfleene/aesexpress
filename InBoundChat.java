import java.io.*;
import java.net.*;

class InBoundChat{

	Socket inBoundSocket;
	int inBoundPort;
	ObjectOutputStream out;
 	ObjectInputStream in;
	InBoundChat(int reciving){
		inBoundPort = reciving;
		try{
			//1. creating a socket to connect to the server
			inBoundSocket = new ServerSocket(inBoundPort, 10).accept();
			//2. get Input and Output streams
			out = new ObjectOutputStream(inBoundSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(inBoundSocket.getInputStream());
			//3: Communicating with the server
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try{
			in.close();
			out.close();
			inBoundSocket.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	boolean isConnected(){
		boolean connected = false;
		try{
			out.writeObject("ping");
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		if(!recieveMessage().equals("ping")){
			connected = true;
		}
		return connected;
	}
	String recieveMessage(){
		String message = "";
			try{
				message = (String)in.readObject();
			}catch(ClassNotFoundException classNot){
				System.err.println("data received in unknown format");
			}catch(Exception e){
				e.printStackTrace();
			}
		return message;
	}
}
