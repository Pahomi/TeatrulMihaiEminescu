package md.convertit.gui;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

	
	private static final long serialVersionUID = 1549050379865161334L;
	public MyFrame() throws HeadlessException {
		super();
		setSize(1024, 700);
		setTitle("Teatru Mihai Eminescu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	public void startApp() {
		initGUI();
//		addActionListeners();
		setVisible(true);
	}
	private void initGUI() {
JPanel panel = new JPanel();
		
		nameLabel = new JLabel("no name");
		

		 button = new JButton("Press me");
		 button2 = new JButton("Show dialog!");

		panel.add(button);
		panel.add(button2);
		panel.add(nameLabel);
		getContentPane().add(panel);

		
	}
	

	

}
