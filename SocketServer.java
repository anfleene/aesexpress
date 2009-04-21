import java.io.*;
import java.net.*;

class SocketServer{
   ServerSocket server = null;
   Socket client = null;
   BufferedReader in = null;
   PrintWriter out = null;
   String line;
   int port;
   
   SocketServer(int portNumber){ //Begin Constructor
	   this.port = portNumber;
	   listenSocket();    
   } //End Constructor

 public String getMessage(){
	 return this.line;
 }
  private void listenSocket(){
	
    try{
    	//getMessage(); 
    	server = new ServerSocket(this.port); 
    } catch (IOException e) {
      System.out.println("Could not listen on port " + this.port);
      System.exit(-1);
    }

    try{
      client = server.accept();
    } catch (IOException e) {
      System.out.println("Accept failed: " + this.port);
      System.exit(-1);
    }

    try{
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("Accept failed: " + this.port);
      System.exit(-1);
    }
 
    while(true){
      try{
        this.line = in.readLine();
//Send data back to client
        out.println(line);
      } catch (IOException e) {
        System.out.println("Read failed");
        System.exit(-1);
      }
    }
  }

  protected void finalize(){
//Clean up 
     try{
        in.close();
        out.close();
        server.close();
    } catch (IOException e) {
        System.out.println("Could not close.");
        System.exit(-1);
    }
  }

}
