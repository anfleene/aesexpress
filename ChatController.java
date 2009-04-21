
class ChatController{
	String messageIn;
	String messageOut;
	String encryptedIn;
	String encryptedOut;
	SocketServer inBound;
	SocketClient outBound;
	
	ChatController(){}
	
	ChatController(String hostname, int clientPort, int serverPort){
		this.outBound = new SocketClient(hostname, clientPort);
		this.inBound = new SocketServer(serverPort);
	}
	
	public String getMsg(){
		return this.messageIn;
	}
	
	public void receiveMsg(){
		while(true){
			if(this.inBound.getMessage() != this.encryptedIn){
				this.encryptedIn = this.inBound.getMessage();
				EncryptedString eString = new EncryptedString(this.encryptedIn, true);
				try{
					eString.decrypt();
				}catch (Exception e){
					e.printStackTrace();
				}
				this.messageIn = eString.toString();
			}
		}
		
	}
	
	public void sendMsg(String msg){
		this.messageOut = msg;
		EncryptedString eString = new EncryptedString(msg);
		this.encryptedOut = eString.toString();
		this.outBound.sendMsg(eString.toString());
	}
	
	public void connect(String host, int clientPort, int serverPort){
		this.outBound = new SocketClient(host, clientPort);
		this.inBound = new SocketServer(serverPort);
	}

}
