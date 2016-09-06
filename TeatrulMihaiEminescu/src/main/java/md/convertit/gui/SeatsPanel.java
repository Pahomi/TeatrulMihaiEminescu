package md.convertit.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SeatsPanel extends JPanel {

	private List<JButton> locuri;
	private JPanel seatsAvailable;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3460810561385264688L;

	public SeatsPanel(int rows, int columns) {

		super();
		setBorder(new TitledBorder(new EtchedBorder(), "Seats"));
		setLayout(new BorderLayout());

		addSeats(rows, columns);
		addLegends();
	}

	private void addLegends() {
		JPanel panel = new JPanel();

		JPanel panelOcupat = new JPanel();
		panelOcupat.setBackground(Color.RED);
		panelOcupat.setMinimumSize(new Dimension(25, 25));
		JPanel panelLiber = new JPanel();
		panelLiber.setBackground(Color.GREEN);
		panel.add(panelLiber);
		panel.add(new JLabel("Liber       "));
		panel.add(panelOcupat);
		panel.add(new JLabel("Ocupat"));

		add(panel, BorderLayout.SOUTH);

	}

	private void addSeats(int rows, int columns) {
		locuri = new ArrayList<>();
		GridLayout layout = new GridLayout(rows, columns);
		layout.setHgap(4);
		layout.setVgap(4);
		seatsAvailable = new JPanel(layout);
		for (int i = 1; i <= rows*columns; i++) {
			JButton button = new JButton(String.valueOf(i));
			button.setBackground(Color.GREEN);
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					if (btn.getBackground() == Color.GREEN) {
						btn.setBackground(Color.RED);
					}
					else {
						btn.setBackground(Color.GREEN);
					}
				}
			});
			
			locuri.add(button);
			seatsAvailable.add(button);
			add(seatsAvailable, BorderLayout.CENTER);	
		}
	}
	public int getLocuriLibere () {
		int libere = 0;
		for (JButton jButton : locuri) {
		if (jButton.getBackground() == Color.GREEN) {
			libere++;
		}
		}
		return libere;
		
	}

	public void reset() {
		for (JButton jButton : locuri) {
			jButton.setBackground(Color.GREEN);
		}
		
	}

}
