package md.convertit.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import md.convertit.services.impl.ExcelFileService;
import md.convertit.services.impl.JsonFileService;
import md.convertit.services.impl.XmlFileService;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.services.FileService;
import md.convertit.util.FileUtil;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1549050379865161334L;

	private JButton saveButton;
	private JButton clearButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton exportJsonButton;
	private JButton exportXmlButton;
	private JButton exportExcell;
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
		numeTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				numeTextField.setBorder(new JTextField().getBorder());

			}

		});
		dateTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				dateTextField.setBorder(new JTextField().getBorder());
			}

		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					String name = numeTextField.getText();

					boolean premiere = premiereCheckBox.isSelected();

					int seatsAvailable = seatsPanel.getLocuriLibere();

					Spectacole spect = new Spectacole();
					spect.setName(name);
					spect.setSeatsAvailable(seatsAvailable);
					spect.setPremiere(premiere);

					DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
					String dateAsString = dateTextField.getText();
					Date date = null;
					try {
						date = sourceFormat.parse(dateAsString);
						spect.setData(date);

						SqlSpectacoleTableModel model = (SqlSpectacoleTableModel) table.getModel();
						model.addSpectacole(spect);
						clearFields();
					} catch (ParseException e1) {
						dateTextField.setBorder(new EtchedBorder(Color.RED, Color.GRAY));
					}
				}

			}

			private boolean validateFields() {
				boolean validated = true;
				if (numeTextField.getText().trim().isEmpty()) {
					numeTextField.setBorder(new EtchedBorder(Color.RED, Color.BLUE));
					validated = false;
				}
				if (dateTextField.getText().trim().isEmpty()) {
					dateTextField.setBorder(new EtchedBorder(Color.RED, Color.BLUE));
					validated = false;
				}
				return validated;
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearFields();

			}
		});
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SqlSpectacoleTableModel model = (SqlSpectacoleTableModel) table.getModel();
				model.removeSpectacole(table.getSelectedRow());
				JOptionPane.showMessageDialog(MyFrame.this,"Spectacolul a fost sters!!!");
				
			}
		});
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = numeTextField.getText();;

				boolean premiere = premiereCheckBox.isSelected();

				int seatsAvailable = seatsPanel.getLocuriLibere();
				
				Spectacole spect = new Spectacole();
				spect.getName();
				spect.getSeatsAvailable();
				spect.isPremiere();
				
				DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dateAsString = dateTextField.getText();
				Date date = null;
				try {
					date = sourceFormat.parse(dateAsString);
					spect.setData(date);

					SqlSpectacoleTableModel model = (SqlSpectacoleTableModel) table.getModel();
					model.addSpectacole(spect);
					clearFields();
				} catch (ParseException e1) {
					dateTextField.setBorder(new EtchedBorder(Color.RED, Color.GRAY));
				}
			}

				
				
		
		});
		exportJsonButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SqlSpectacoleTableModel tableModel =(SqlSpectacoleTableModel) table.getModel();
			
			FileService fs = new JsonFileService();
			try {
				String path = FileUtil.showSaveFileDialog();
				if (path == null) return;
				fs.saveAll(tableModel.getSpectacoleList(), path.concat(".json"));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(MyFrame.this, 
						"Error on export to JSON", "Export to JSON", 
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		
			}
		});
		exportXmlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SqlSpectacoleTableModel tableModel =(SqlSpectacoleTableModel) table.getModel();
				FileService fs = new XmlFileService();
				
				try {
					String path = FileUtil.showSaveFileDialog();
					if (path == null) return;
					fs.saveAll(tableModel.getSpectacoleList(), path.concat(".xml"));
					JOptionPane.showMessageDialog(MyFrame.this, 
							"Users was successfully exported", "Export to XML", 
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MyFrame.this, 
							"Error on export to XML", "Export to XML", 
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
				
			}
		});
		exportExcell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SqlSpectacoleTableModel tableModel =(SqlSpectacoleTableModel) table.getModel();
				FileService fs = new ExcelFileService();

				try {
					String path = FileUtil.showSaveFileDialog();
					if (path == null) return;
					fs.saveAll(tableModel.getSpectacoleList(), path.concat(".xls"));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(MyFrame.this, 
							"Error on export to EXCELL", "Export to EXCELL", 
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * clear all  complited fields
	 */
	protected void clearFields() {
		numeTextField.setText("");
		dateTextField.setText("");
		premiereCheckBox.setSelected(false);
		seatsPanel.reset();

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
		SqlSpectacoleTableModel tableModel = new SqlSpectacoleTableModel();

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
		exportJsonButton = new JButton("Export to JSON");
		exportXmlButton = new JButton("Export to XML");
		exportExcell = new JButton("Export to Excell");
		

		rightButtonPanel.add(editButton);
		rightButtonPanel.add(deleteButton);
		rightButtonPanel.add(exportJsonButton);
		rightButtonPanel.add(exportXmlButton);
		rightButtonPanel.add(exportExcell);

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
		dateTextField.setToolTipText("dd/mm/yyyy");
		dateTextField.setText("14/04/2016");
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