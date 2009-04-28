import java.io.*;
import java.net.*;

class SocketClient{
	public boolean connected;
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	String line;
	String hostName;
	int port;
	long timeout;
   

   SocketClient(String host, int portNumber){ //Begin Constructor
	   this.hostName = host;
	   this.port = portNumber;
	   this.socket = null;
	   this.out = null;
	   this.in = null;
	   this.connected = false;
   } //End Constructor
   
   public String getMsg(){
		try{
		  this.line = this.in.readLine();
		} catch (IOException e) {
			System.out.println("Unable to read input");
		}
		return this.line;
   }
   public void sendMsg(String msg){
         this.out.println(msg);
   }
  
  public void listenSocket(){
//Create socket connection
	try{
		System.out.println("Attempting to Connect to Server");
		this.socket = new Socket(hostName, port);
	} catch (UnknownHostException e) {
		System.out.println("Connection Failed, Retrying");
	} catch (IOException e){
		System.out.println("Connection Failed, Retrying");
	}		
	try{
		out = new PrintWriter(this.socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}catch  (IOException e) {
		System.out.println("No I/0");
	}
	System.out.println("Connection Established");
  }
  
  protected void finalize(){
//	Clean up 
	     try{
	        this.in.close();
	        this.out.close();
	        this.socket.close();
	    } catch (IOException e) {
	        System.out.println("Could not close.");
	        System.exit(-1);
	    }
	  }
}
