import java.io.*;
import java.net.*;

class SocketServer{
	public boolean connected;
	ServerSocket server = null;
	Socket client = null;
	BufferedReader in = null;
	PrintWriter out = null;
	String line;
	long timeout;
	int port;
   
   SocketServer(int portNumber){ //Begin Constructor
	   this.port = portNumber;
	   this.connected = false;
	   this.server = null;
	   this.client = null;
	   this.in = null;
	   this.out = null;
	   try{
		   System.out.println("Opening a socket");
		   this.server = new ServerSocket(this.port);
	   } catch (Exception e) {
				System.out.println("Connection failed could not listen to port");
	   }
  } //End Constructor
  public void listenSocket(){
	try{
		System.out.println("Attempting to accept the client");
		this.client = server.accept();
	} catch (IOException e){
		System.out.println("Accept failed: " + port);
	}
	System.out.println("Client Accepted");
	try{
		this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.out = new PrintWriter(this.client.getOutputStream(), true);
		System.out.println("created input and output");
	}catch (IOException e){
		System.out.println("Faild to read input and output");
	}
	System.out.println("Connection Established");
  }//end listenSocket
  
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

  protected void finalize(){
//Clean up 
     try{
        this.in.close();
        this.out.close();
        this.server.close();
    } catch (IOException e) {
        System.out.println("Could not close.");
        System.exit(-1);
    }
  }

}
