package md.convertit.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1549050379865161334L;

	private JButton saveButton;
	private JButton clearButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton exportButton;
	private JTextField numeTextField;
	private JTextField numeTextField1;
	private JRadioButton premiereYes;
	private JRadioButton premiereNo;
	private JSpinner seatsAvailable;

	public MyFrame() throws HeadlessException {
		super();
		setSize(1024, 700);
		setTitle("Teatru Mihai Eminescu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		iniContainers();

		addActionListeners();

	}

	private void addActionListeners() {
		// TODO Auto-generated method stub

	}

	private void iniContainers() {

		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBackground(Color.GREEN);
		leftPanel.setMinimumSize(new Dimension(400, 700));

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.RED);

		JScrollPane formScrollPane = createFormPanel();

		// ***********************************************
		setContentPane(mainSplitPane);

		mainSplitPane.setLeftComponent(leftPanel);

		mainSplitPane.setRightComponent(rightPanel);

		leftPanel.add(formScrollPane, BorderLayout.CENTER);

	}

	private JScrollPane createFormPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		premiereYes = new JRadioButton("Yes");
		premiereYes.setSelected(true);
        premiereNo = new JRadioButton("No");
        
        ButtonGroup premiereButtonGroup = new ButtonGroup();
        premiereButtonGroup.add(premiereYes);
        premiereButtonGroup.add(premiereNo);
        
        panel.add(new JLabel("Premiere"));
        panel.add(premiereNo);
        panel.add(premiereYes);
        
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		numeTextField = new JTextField(30);
		panel1.setBorder(new TitledBorder(new EtchedBorder(), "Nume"));
		
		seatsAvailable= new JSpinner();
        //facem model pt spiner
     SpinnerModel spinnerModel = new SpinnerNumberModel(18, 18, 109, 1);
     
     seatsAvailable.setModel(spinnerModel);
     seatsAvailable.setMinimumSize(new Dimension(Integer.MAX_VALUE, 
    		 seatsAvailable.getPreferredSize().height));
		

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		numeTextField1 = new JTextField(30);
		panel2.setBorder(new TitledBorder(new EtchedBorder(), "Nume2"));

		// JPanel panel3 = new JPanel(new GridLayout());
		// int height = numeTextField.getPreferredSize().height;
		// int width = Integer.MAX_VALUE;
		// numeTextField.setMaximumSize(new Dimension(width,height));
		// numeTextField.setMinimumSize(new Dimension(Integer.MAX_VALUE,
		// numeTextField.getPreferredSize().height));

		panel1.add(numeTextField);
		panel2.add(numeTextField1);

		panel.setBorder(new TitledBorder(new EtchedBorder(), "Adauga spectacol"));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(Box.createRigidArea(new Dimension(0, 500)));

		JScrollPane scrollPane = new JScrollPane(panel);
		return scrollPane;
	}

	public void run() {
		setVisible(true);

	}
}