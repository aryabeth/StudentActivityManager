package rplproject;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableLog extends AbstractTableModel{
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return namaKolom.length;
	}

	@Override
	public String getColumnName(int index){
		return namaKolom[index];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listLog.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 0){
			return listLog.get(rowIndex).getTasktitle();
		}else if(columnIndex == 1){
			return listLog.get(rowIndex).getDate();
		}else if(columnIndex == 2){
			return listLog.get(rowIndex).getTime();
		}else if(columnIndex == 3){
			return listLog.get(rowIndex).getAction();
		}
		return null;
	}

	private ArrayList<Log> listLog;
	private Database db;
	private String[] namaKolom = {"Task Title", "Date" , "Time", "Action"};
	
	public TableLog(){
		db = new Database();
		listLog = db.getDatalog();
	}


	public void refreshl(){
		db = new Database();
		listLog = db.getDatalog();
		fireTableDataChanged();
	}
	
	
}