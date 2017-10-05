package commandLineInterpriter;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JFrame;

public class Gui  {

	public JFrame mainFrame;
	public TextArea textArea;
	Terminal terminal;
	
	public Gui() {
		terminal = new Terminal();
		mainFrame = new JFrame();
		mainFrame.setSize(600,350);
	    textArea=new TextArea();
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Serif", Font.ITALIC, 19));
		mainFrame.add(textArea);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea.setText(terminal.getCurrentPath());
		textArea.setCaretPosition(textArea.getParent().getWidth());
	}

}
