import java.io.*;
import java.net.*;

class SocketClient{
   Socket socket = null;
   PrintWriter out = null;
   BufferedReader in = null;
   String hostName;
   boolean connected;
   int port;
   long timeout;
   

   SocketClient(String host, int portNumber){ //Begin Constructor
	   this.hostName = host;
	   this.port = portNumber;
	   this.timeout = System.currentTimeMillis() + 30000;
	   listenSocket();
   } //End Constructor
   
   public void sendMsg(String msg){
          out.println(msg);
   }
  
  public void listenSocket(){
//Create socket connection
	 while(!this.connected || System.currentTimeMillis() < this.timeout){
		 try{
			 socket = new Socket(hostName, port);
		 } catch (UnknownHostException e) {
			 System.out.println("Connection Failed, Retrying");
		 } catch (IOException e){
			 System.out.println("Connection Failed, Retrying");
		 }
  	}
	if (!this.connected){
		System.out.println("Connection Timed out");
	}else{
		try{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch  (IOException e) {
			System.out.println("No I/0");
		}
	}
  }
}
