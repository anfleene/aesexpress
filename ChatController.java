class ChatController{
	String messageIn;
	String messageOut;
	String encryptedIn;
	String encryptedOut;
	SocketServer server;
	SocketClient client;
	boolean connected;
	
	ChatController(){ connected = false;}
		
	public String receiveMsg(){
		String message = "";
		if(this.server == null){
			message = this.client.getMsg();
		}else{
			message = this.server.getMsg();
		}
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
		if(server == null){
			this.client.sendMsg(eString.toString());
		}else{
			this.server.sendMsg(eString.toString());
		}
	}
	
	public void connect(String host, boolean choice1){
		if(choice1){
			System.out.println("Connect to Server");
			this.server  = new SocketServer(4448);
			this.server.listenSocket();
		}else{
			System.out.println("Connect to Client");
			this.client = new SocketClient(host, 4448);
			this.client.listenSocket();
		}
	}

}
