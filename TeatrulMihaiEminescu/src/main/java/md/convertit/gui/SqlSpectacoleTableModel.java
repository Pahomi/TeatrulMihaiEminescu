package md.convertit.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import md.convertit.dao.impl.SpectacoleDaoImpl;
import md.convertit.spectacol.domain.Spectacole;

public class SqlSpectacoleTableModel extends AbstractTableModel{
	private static final long serialVersionUID = -2627265411566825310L;

	SpectacoleDaoImpl spectDao = new SpectacoleDaoImpl();
	private List<Spectacole> spectacoleList = new ArrayList<>();
	// store column names
	private String[] columnNames = {"Id", "Name", "SeatsAvailable", "Premiere", "Data"};

	public SqlSpectacoleTableModel() {
		super();
		spectacoleList = spectDao.findAll();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return spectacoleList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
	Spectacole spectacol = spectacoleList.get(row);
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
	public void addSpectacole(Spectacole spectacol){
		spectDao.save(spectacol);
		spectacoleList = spectDao.findAll();
		fireTableDataChanged();
	}	
	public void removeSpectacole(int row){
		spectDao.delete((long) row);
		spectacoleList = spectDao.findAll();
		fireTableDataChanged();
	}

	public SpectacoleDaoImpl getSpectDao() {
		return spectDao;
	}

	public void setSpectDao(SpectacoleDaoImpl spectDao) {
		this.spectDao = spectDao;
	}

	public List<Spectacole> getSpectacoleList() {
		return spectacoleList;
	}

	public void setSpectacoleList(List<Spectacole> spectacoleList) {
		this.spectacoleList = spectacoleList;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
