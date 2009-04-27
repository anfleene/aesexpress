class ChatController{
	String messageIn;
	String messageOut;
	String encryptedIn;
	String encryptedOut;
	SocketServer inBound;
	SocketClient outBound;
	boolean connected;
	
	ChatController(){ connected = false;}
	
	ChatController(String hostname, int clientPort, int serverPort){
		this.outBound = new SocketClient(hostname, clientPort);
		this.inBound = new SocketServer(serverPort);
		
	}
	
	public String receiveMsg(){
		String message = this.inBound.getMsg(); 
		if(message != this.encryptedIn){
			this.encryptedIn = message;
			EncryptedString eString = new EncryptedString(this.encryptedIn, true);
			try{
				eString.decrypt();
			}catch (Exception e){
				e.printStackTrace();
			}
			this.messageIn = eString.toString();
		}
		return this.messageIn;
	}
	
	public void sendMsg(String msg){
		this.messageOut = msg;
		EncryptedString eString = new EncryptedString(msg);
		this.encryptedOut = eString.toString();
		this.outBound.sendMsg(eString.toString());
	}
	
	public void connect(String host, int clientPort, int serverPort, boolean choice1){
		if(choice1){
			this.inBound  = new SocketServer(serverPort);
			this.outBound = new SocketClient(host, clientPort);
		}else{
			this.outBound = new SocketClient(host, clientPort);
			this.inBound  = new SocketServer(serverPort);
		}
	}

}
