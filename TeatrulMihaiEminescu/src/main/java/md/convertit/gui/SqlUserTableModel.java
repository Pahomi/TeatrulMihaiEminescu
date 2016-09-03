package md.convertit.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import md.convertit.dao.impl.SpectacoleDaoImpl;
import md.convertit.spectacol.domain.Spectacole;

public class SqlUserTableModel extends AbstractTableModel{
	private static final long serialVersionUID = -2627265411566825310L;

	SpectacoleDaoImpl spectDao = new SpectacoleDaoImpl();
	private List<Spectacole> spectacole = new ArrayList<>();
	// store column names
	private String[] columnNames = {"Id", "Name", "SeatsAvailable", "Premiere", "Data"};

	public SqlUserTableModel() {
		super();
		spectacole = spectDao.findAll();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return spectacole.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
	Spectacole spectacol = spectacole.get(row);
	if(column == 0){
		return spectacol.getId();
	} else if(column == 1){
		return spectacol.getName();
	} else if(column == 2){
		return spectacol.getSeatsAvailable();
	} else if(column == 3){
		return spectacol.isPremiere();
	}  else if(column == 4){
		return spectacol.getData();
	} 
	return "no data";
}
	public String getColumnName(int column) {
		return columnNames[column];
	}
		
}
