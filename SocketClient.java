import java.io.*;
import java.net.*;

class SocketClient{
	public boolean connected;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
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
	   this.listenSocket();
   } //End Constructor
   
   public void sendMsg(String msg){
          out.println(msg);
   }
  
  public void listenSocket(){
//Create socket connection
	 this.timeout = System.currentTimeMillis() + 30000;
	 System.out.println("Timeout: " + this.timeout + " Current Time: " + System.currentTimeMillis());
	 while(!this.connected && System.currentTimeMillis() < this.timeout){
		 try{
			 System.out.println("Attempting to Connect to Server");
			 this.socket = new Socket(hostName, port);
			 this.socket.setSoTimeout(20000); 
			 this.connected = true;
		 } catch (UnknownHostException e) {
			 System.out.println("Connection Failed, Retrying");
		 } catch (IOException e){
			 System.out.println("Connection Failed, Retrying");
		 }
  	}
	if (!this.connected){
		System.out.println("Connection Timed out");
	}else{
		System.out.println("Connected on to host:" + this.hostName + " port: " + this.port);
		try{
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		}catch  (IOException e) {
			System.out.println("No I/0");
		}
		System.out.println("Client in and out connected");
	}
  }
}
