package md.convertit.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	private JTextField dateTextField;
	private JCheckBox premiereCheckBox;
	private SeatsPanel seatsPanel;
	private JPanel rightPanel;
	private JTable table;

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
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nume = numeTextField.getText();

			}
		});

	}

	private void iniContainers() {

		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBackground(Color.GREEN);
		leftPanel.setMinimumSize(new Dimension(400, 700));

		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.RED);

		JScrollPane formScrollPane = createFormPanel();

		JPanel formButtonsPanel = createFormButtonsPanel();

		JScrollPane tableScrollPane = createTableScrollPane();

		JPanel tableButtonsPanel = createButtonsPanel();

		// ***********************************************
		setContentPane(mainSplitPane);

		mainSplitPane.setLeftComponent(leftPanel);

		mainSplitPane.setRightComponent(rightPanel);

		leftPanel.add(formScrollPane, BorderLayout.CENTER);

		leftPanel.add(formButtonsPanel, BorderLayout.SOUTH);

		rightPanel.add(tableScrollPane, BorderLayout.CENTER);

		rightPanel.add(tableButtonsPanel, BorderLayout.SOUTH);

	}

	private JScrollPane createTableScrollPane() {

		// Create SqlUserTableModel
		SqlUserTableModel tableModel = new SqlUserTableModel();

		// Init table
		table = new JTable(tableModel);

		// set selection mode to single
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		// JScrollPane init
		JScrollPane jScrollPane = new JScrollPane(table);

		return jScrollPane;
	}

	private JPanel createButtonsPanel() {
		JPanel rightButtonPanel = new JPanel();
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		exportButton = new JButton("Export");

		rightButtonPanel.add(editButton);
		rightButtonPanel.add(deleteButton);
		rightButtonPanel.add(exportButton);

		return rightButtonPanel;
	}

	private JPanel createFormButtonsPanel() {
		JPanel leftButtonPanel = new JPanel();

		// init buton
		saveButton = new JButton("Save");
		clearButton = new JButton("Clear");

		// adaug buton pe panel
		leftButtonPanel.add(saveButton);
		leftButtonPanel.add(clearButton);

		return leftButtonPanel;
	}

	private JScrollPane createFormPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// nume
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		numeTextField = new JTextField(30);
		panel1.setBorder(new TitledBorder(new EtchedBorder(), "Nume"));

		// data
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dateTextField = new JTextField(30);
		panel2.setBorder(new TitledBorder(new EtchedBorder(), "Data"));
		// premiere
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.setBorder(new TitledBorder(new EtchedBorder(), "Premiere"));
		premiereCheckBox = new JCheckBox("Premiere");

		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.setBorder(new TitledBorder(new EtchedBorder()));

		// seats
		seatsPanel = new SeatsPanel(8, 6);

		panel1.add(numeTextField);
		panel2.add(dateTextField);
		panel3.add(premiereCheckBox);

		panel.setBorder(new TitledBorder(new EtchedBorder(), "Adauga spectacol"));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(seatsPanel);

		JScrollPane scrollPane = new JScrollPane(panel);
		return scrollPane;
	}

	public void run() {
		setVisible(true);

	}
}