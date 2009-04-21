import java.io.*;
import java.net.*;

class SocketClient{
   Socket socket = null;
   PrintWriter out = null;
   BufferedReader in = null;
   String hostName;
   int port;

   SocketClient(String host, int portNumber){ //Begin Constructor
	   this.hostName = host;
	   this.port = portNumber;
	   listenSocket();
   } //End Constructor
   
   public void sendMsg(String msg){
          out.println(msg);
   }
  
  public void listenSocket(){
//Create socket connection
     try{
       socket = new Socket(hostName, port);
       out = new PrintWriter(socket.getOutputStream(), true);
       in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     } catch (UnknownHostException e) {
       System.out.println("Unknown host: " + hostName);
       System.exit(1);
     } catch  (IOException e) {
       System.out.println("No I/O");
       System.exit(1);
     }
  }
}
