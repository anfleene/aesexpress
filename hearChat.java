import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

class hearChat implements ActionListener {
	

	    JTextArea chatText;
	    JTextArea sentText;
	    
	    public hearChat(JTextArea chat, JTextArea sending) {
	        chatText = chat;
	        sentText = sending;
	    }

	    public void actionPerformed(ActionEvent e) {
	    	
	    	String text = sentText.getText();
	        chatText.append("me -> " + text + "\n");
	        chatText.setCaretPosition(chatText.getDocument().getLength());
	    }
	
	

}
