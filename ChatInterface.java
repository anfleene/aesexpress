import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.Box;

class ChatInterface extends JFrame implements ActionListener {

	private JTextArea chatArea;
	private JTextArea textInput;
	private JButton sendButton;
	private JButton connectButton;
	private ChatController newChat;
	private BorderLayout mainBox;
	private Box top;
	private Box bottom;
	private Box topBttm;
	private Box buttonBttm;
	private Box connectStuff;
	private Color color;
	
	private JPopupMenu pop;
	private ButtonGroup radioButtons;
	private JRadioButton con1;
	private JRadioButton con2;
	private JTextField hostName;
	private JLabel hostLabel;
	private JButton ok;
	private String ipAdd = "";
	
	private String lastMess = "";
	
	public ChatInterface()
	{
		
			newChat = new ChatController();
			
			
			
			radioButtons = new ButtonGroup();
			con1 = new JRadioButton("4444/4445");
			con2 = new JRadioButton("4445/4444");
			hostName = new JTextField();
			hostLabel = new JLabel("IP Address");
			ok = new JButton("OK");
			ok.addActionListener(this);
			
			
			
			
			
			
		 JFrame inter = new JFrame("Your Chat");
		    inter.setBounds(0, 0, 400, 400);
		    inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    
		    top = Box.createVerticalBox();
		    top.add(Box.createVerticalStrut(10));
		    top.add(Box.createHorizontalStrut(10));
		    chatArea = new JTextArea(10, 15);
		    chatArea.setEnabled(false);
		    chatArea.setWrapStyleWord(true);
		    JScrollPane scrollPane = new JScrollPane(chatArea);
			top.add(scrollPane);
			
		    top.add(Box.createHorizontalStrut(10));
			color = new Color(204,255,204,255);  

		    			
		    
			
		    topBttm = Box.createVerticalBox();
		    topBttm.add(Box.createVerticalStrut(10));
		    textInput = new JTextArea(5, 10);
		    textInput.setWrapStyleWord(true);
		    JScrollPane scrollPane1 = new JScrollPane(textInput);
		    topBttm.add(scrollPane1);
		    
		    //topBttm.add(Box.createHorizontalStrut(10));
		    
		    buttonBttm = Box.createHorizontalBox();
			sendButton = new JButton("Send");
			sendButton.addActionListener(this);
			hearChat chat = new hearChat(chatArea,textInput);
			sendButton.addActionListener(chat);
			buttonBttm.add(sendButton);
			connectButton = new JButton("Connect");
			connectButton.addActionListener(this);
			buttonBttm.add(connectButton);
			
			connectStuff = Box.createVerticalBox();
			
			
			
			con1 = new JRadioButton("Server");
			con2 = new JRadioButton("Client(must include and IP address)");
			radioButtons = new ButtonGroup();
			radioButtons.add(con1);
			radioButtons.add(con2);
			hostName = new JTextField();
			hostLabel = new JLabel("IP Address:");
			ok = new JButton("OK");
			ok.addActionListener(this);
			//connectButton.addActionListener(this);
			connectStuff.add(con1);
			connectStuff.add(con2);
			connectStuff.add(hostLabel);
			connectStuff.add(hostName);
		    Box overAll = Box.createVerticalBox();
		    
		    overAll.add(top);
		    overAll.add(topBttm);
		    overAll.add(buttonBttm);
		    overAll.add(connectStuff);
		    

		    Container content = inter.getContentPane();
		    content.setLayout(new BorderLayout());
		    content.add(overAll, BorderLayout.CENTER);
		

		    
		    content.setBackground(color);
		    inter.setVisible(true);
		    
		    while(true){
		    	this.listenChat();
		    }
	
	}
	
	public void actionPerformed(ActionEvent e)
    {		Object source = e.getSource();
    		if(source.equals(sendButton))
    		{
            String text = textInput.getText();
            newChat.sendMsg(text);
    		}
    		else if(source.equals(connectButton))
    		{
    			boolean choice1 = false;
    		
    			String host = hostName.getText();
    			ipAdd = host;
    			
    			if(con1.isSelected())
    			{
    				choice1 = true;
    				
    			}
    			else if(con2.isSelected())
    			{
    				choice1 = false;
    			}
    			
    			if(!choice1 && host.equals(""))
    			{
    				
    				chatArea.append("ERROR -> **************** CONNECTION FAILED, PLEASE RETRY***************\n");
    		        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    			}else{
    				newChat.connect(host, choice1);
    				if(host.equals(""))
    					ipAdd = "client";
    			}
    			
    				
    		}
            
           
    } // end of actionPerformed()
	public void listenChat(){
		if(newChat.connected)
    	{
    		String newMess = newChat.receiveMsg();
    		if(newMess != lastMess)
    		{
    			lastMess = newMess;
    			chatArea.append(ipAdd + " -> " + newMess + "\n");
    	        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    		}
    	}
	}
	
}
