package commandLineInterpriter;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Gui  {

	public JFrame mainFrame;
	public JTextArea textArea;
	private Terminal terminal;
	private JLabel path;
	
	public Gui() {
		terminal = new Terminal();
		mainFrame = new JFrame();
	    textArea=new JTextArea();
	    path = new JLabel(terminal.getCurrentPath());
		mainFrame.setSize(600,350);
		
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Serif", Font.ITALIC, 14));
		
		path.setFont(new Font("Serif", Font.ITALIC, 14));
		mainFrame.add(textArea);
		mainFrame.add(path);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea.setCaretPosition(textArea.getParent().getWidth());
	}

}
