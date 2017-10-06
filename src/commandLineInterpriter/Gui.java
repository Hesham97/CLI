package commandLineInterpriter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Gui  {

	public JFrame mainFrame;
	public JTextArea textArea;
	public JTextArea centerTextArea;
	private Terminal terminal;
	private JLabel path;
	
	public Gui() {
		terminal = new Terminal();
		mainFrame = new JFrame();
	    textArea=new JTextArea(terminal.getCurrentPath());
	    centerTextArea= new JTextArea();
	    path = new JLabel(terminal.getCurrentPath());
		mainFrame.setSize(600,350);


		centerTextArea.setForeground(Color.black);
		centerTextArea.setFont(new Font("Serif", Font.ITALIC, 14));

		
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Serif", Font.ITALIC, 14));
		
		path.setFont(new Font("Serif", Font.ITALIC, 14));

		mainFrame.add(textArea,BorderLayout.SOUTH);
		mainFrame.add(centerTextArea,BorderLayout.CENTER);

		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					centerTextArea.append(textArea.getText());
				}
			}
		});
		}
	
	

}
