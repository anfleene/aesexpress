import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class ChatInterface extends JFrame implements ActionListener {

	private JTextArea chatArea;
	private JTextArea textInput;
	private JButton sendButton;
	private ChatController chat;
	private BorderLayout mainBox;
	
	public ChatInterface()
	{
		
			chat = new ChatController();
		 JFrame inter = new JFrame("Your Chat");
		    inter.setBounds(250, 420, 250, 420);
		    inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    JPanel panel = new JPanel(new GridLayout(5,1));
		  
		    //panel.add(Box.createVerticalStrut(30));
		    panel.add(Box.createVerticalStrut(10));
		    chatArea = new JTextArea(10, 15);
		    chatArea.setEnabled(false);
			panel.add(chatArea);
			
		    //top.add(Box.createVerticalStrut(30));
			Color color = new Color(204,255,204,255);  

		    //top.add(Box.createGlue());

		    
			panel.add(Box.createVerticalStrut(10));
		    //JPanel bttm = new JPanel(new BorderLayout());
		    //bttm.add(Box.createVerticalStrut(30));
		    textInput = new JTextArea(10, 15);
		    //textInput.setMaximumSize(new Dimension(300,5));
		    panel.add(textInput);
		    //bttm.add(Box.createHorizontalStrut(30), BorderLayout.CENTER);
		    
			sendButton = new JButton("Send");
			sendButton.setMaximumSize(new Dimension(2,5));
			sendButton.addActionListener(this);
			panel.add(sendButton);
			//panel.add(Box.createVerticalStrut(10));
		    //panel.add(Box.createVerticalStrut(30));
		    
			panel.setBackground(color);
		    //bttm.add(Box.createGlue());

		    //JPanel topPanel = new JPanel(new BorderLayout());
		    //topPanel.setBorder(new TitledBorder(new EtchedBorder(), "Line Color"));
		    //topPanel.add(panel, BorderLayout.PAGE_START);
		    //topPanel.add(bttm, BorderLayout.PAGE_END);
		    //topPanel.setBackground(color);

		    //Box overAll = Box.createHorizontalBox();
		    //overAll.add(Box.createHorizontalStrut(10));
		    //overAll.add(topPanel);
		    //overAll.add(Box.createHorizontalStrut(10));
		    

		    Container content = inter.getContentPane();
		    content.setLayout(new BorderLayout());
		    content.add(panel, BorderLayout.CENTER);
		    //content.add(bttm, BorderLayout.SOUTH);

		    
		    content.setBackground(color);
		    inter.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
    {
            String text = textInput.getText();
            
            
            chat.sendMsg(text);
            //while (e.getSource() != buttonArray[i]) i++;

            // i now points to selected button.  Display its info
            //animalLabel.setText(MESSAGES[i][ANIMAL_INDEX]);
            //locationLabel.setText(MESSAGES[i][LOCATION_INDEX]);
            //this.validate();
    } // end of actionPerformed()
}
