import java.io.*;
import java.net.*;

public class ChatController{
	String message;
	InBoundChat inBound;
	OutBoundChat outBound;
	
	ChatController(String hostname, int inBoundPort, int outBoundPort){
		inBound = new InBoundChat(inBoundPort);
		outBound = new OutBoundChat(hostname, outBoundPort);
	}
	
	public  String receiveMsg(){
		return "";
	}
	
	public String sendMsg(){
		return "";
	}

}
