package rplproject;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableTask extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return listTask.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 0){
			return listTask.get(rowIndex).getTitle();
		}else if(columnIndex == 1){
			return listTask.get(rowIndex).getStart();
		}else if(columnIndex == 2){
			return listTask.get(rowIndex).getEnd();
		}else if(columnIndex == 3){
			return listTask.get(rowIndex).getDesk();
		}else if(columnIndex == 4){
			return listTask.get(rowIndex).getTipe();
		}
		return null;
	}
	
	private ArrayList<Task> listTask;
	private Database db;
	private String[] namaKolom = {"Task Name", "Start", "End", "Deskripsi", "Task Type"};
	
	public TableTask(){
		db = new Database();
		listTask = db.getData();
	}
	
	public Object getIdDelete(int convertRowIndexToModel) {
		return listTask.get(convertRowIndexToModel).getId();
	}
	
	public Object getTitleTask(int convertRowIndexToModel) {
		return listTask.get(convertRowIndexToModel).getTitle();
	}
	
	public void refresh(){
		db = new Database();
		listTask = db.getData();
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		// TODO Auto-generated method stub
		listTask.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
