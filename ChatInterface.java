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
public class ChatInterface extends JFrame implements ActionListener {

	private JTextArea chatArea;
	private JTextArea textInput;
	private JButton sendButton;
	private JButton connectButton;
	private ChatController chat;
	private BorderLayout mainBox;
	private Box top;
	private Box bottom;
	private Box topBttm;
	private Box buttonBttm;
	private Color color;


	
	
	public ChatInterface()
	{
		
			chat = new ChatController();
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
			buttonBttm.add(connectButton);
			//connectButton.addActionListener(this);

		    Box overAll = Box.createVerticalBox();
		    
		    overAll.add(top);
		    overAll.add(topBttm);
		    overAll.add(buttonBttm);
		    

		    Container content = inter.getContentPane();
		    content.setLayout(new BorderLayout());
		    content.add(overAll, BorderLayout.CENTER);
		

		    
		    content.setBackground(color);
		    inter.setVisible(true);
		
	
	}
	
	public void actionPerformed(ActionEvent e)
    {
            String text = textInput.getText();
            chat.sendMsg(text);
            
            
           
    } // end of actionPerformed()
	
}
