class ChatController{
	String messageIn;
	String messageOut;
	String encryptedIn;
	String encryptedOut;
	SocketServer inBound;
	SocketClient outBound;
	boolean connected;
	
	ChatController(){ connected = false;}
		
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
			System.out.println("Connect to Server");
			this.inBound  = new SocketServer(serverPort);
			System.out.println("Connect to Client");
			this.outBound = new SocketClient(host, clientPort);
		}else{
			System.out.println("Connect to Client");
			this.outBound = new SocketClient(host, clientPort);
			System.out.println("Connect to Server");
			this.inBound  = new SocketServer(serverPort);
		}
	}

}
