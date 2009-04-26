import java.io.*;
import java.net.*;

class SocketServer{
   ServerSocket server = null;
   Socket client = null;
   BufferedReader in = null;
   PrintWriter out = null;
   String line;
   boolean connected;
   long timeout;
   int port;
   
   SocketServer(int portNumber){ //Begin Constructor
	   this.port = portNumber;
	   this.connected = false;
	   this.timeout = System.currentTimeMillis() + 10000;
	   listenSocket();    
   } //End Constructor

 public String getMessage(){
	 return this.line;
 }
  private void listenSocket(){
	while(!this.connected && System.currentTimeMillis() < this.timeout){
		try{
			//getMessage(); 
			server = new ServerSocket(this.port);
			this.connected = true;
		} catch (IOException e) {
			System.out.println("Connection failed, trying again");
		}	
    }
	
	if(!this.connected){
		System.out.println("Connection Failed");
	}else{

		try{
			client = server.accept();
		} catch (IOException e) {
			System.out.println("Accept failed: " + this.port);
			System.exit(-1);
		}//end try

		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Accept failed: " + this.port);
			System.exit(-1);
		}//end try
 
		while(true){
			try{
				this.line = in.readLine();
				//Send data back to client
				out.println(line);
			} catch (IOException e) {
				System.out.println("Read failed");
			}//end try
		}//end while
	}//end else
  }//end listenSocket

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
