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
	   this.listenSocket();
   } //End Constructor
  public void listenSocket(){
	this.timeout = System.currentTimeMillis() + 10000;
	while(!this.connected && System.currentTimeMillis() < this.timeout){
		try{ 
			server = new ServerSocket(this.port);
			this.connected = true;
		} catch (Exception e) {
			System.out.println("Connection failed, trying again");
		}
    }
	
	if(!this.connected){
		System.out.println("Connection Failed");
	}else{
		System.out.println("Connected on port: " + this.port);
		try{
			client = server.accept();
		} catch (IOException e) {
			System.out.println("Accept failed: " + this.port);
			System.exit(-1);
		}//end try
		System.out.println("Client connected");
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Accept failed: " + this.port);
			System.exit(-1);
		}//end try
		System.out.println("in and out connected");
	}//end else
  }//end listenSocket
  
  public String getMsg(){
	  try{
		  this.line = in.readLine();
		  //Send data back to client
		  this.out.println(line);
	  } catch (IOException e) {
		  System.out.println("Read failed");
	  }//end try
	  return this.line;
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
