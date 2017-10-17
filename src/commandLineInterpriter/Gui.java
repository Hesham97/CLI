package commandLineInterpriter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui {

	public static JFrame mainFrame;
	public static JTextArea textArea;
	public static JTextArea centerTextArea;
	private Terminal terminal;
	JScrollPane scrollPane ;
	private static JLabel path;
	Validator validator;

	public Gui() {
		terminal = new Terminal();
		mainFrame = new JFrame();
		textArea = new JTextArea(Terminal.getUserName() + " ~ :" + Terminal.getCurrentPath() + "$ ");
		centerTextArea = new JTextArea();
		path = new JLabel(Terminal.getCurrentPath());
		mainFrame.setSize(600, 350);
		validator = new Validator();

		 scrollPane = new JScrollPane(centerTextArea,scrollPane.VERTICAL_SCROLLBAR_ALWAYS,scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		
		centerTextArea.setForeground(Color.black);
		centerTextArea.setFont(new Font("Serif", Font.ITALIC, 14));
		centerTextArea.setLineWrap(true);
		centerTextArea.setWrapStyleWord(true);
		// centerTextArea.setEditable(false);

		textArea.setBackground(Color.black);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Serif", Font.ITALIC, 14));
		textArea.setSize(600, 350);

		path.setFont(new Font("Serif", Font.ITALIC, 14));
		path.setSize(600, 350);
		
		mainFrame.add(scrollPane,BorderLayout.CENTER);
		mainFrame.add(textArea, BorderLayout.SOUTH);
		//mainFrame.add(centerTextArea, BorderLayout.CENTER);
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						validator.Validate(textArea.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					centerTextArea.append(textArea.getText()+'\n');
					textArea.setText(Terminal.getUserName() + " ~ :" + Terminal.getCurrentPath() + "$ ");
				}
			}
		});

		Gui.centerTextArea.addKeyListener(new KeyListener() {

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

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (Validator.args[0].equals("more")) {
						try {
							Terminal.more(Validator.args[1], Terminal.moreCounter);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}

		});

	}

}
