package com.mordor.mordorLloguer.model;

import java.util.ArrayList;

public class MyMinibusTableModel extends MyVehicleTableModel<Microbus>{
protected static String[] head = { "Matricula", "PrecioDia", "Marca", "Color", "Motor", "Cilindrada","FechaAdq","Estado","Carnet","NumPlazas","Medida" };
	
	
	public MyMinibusTableModel(ArrayList<Microbus> data) {
		super(data,head);
		// TODO Auto-generated constructor stub
	}
	
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 9: return data.get(row).getNumPlazas();
		case 10: return data.get(row).getMedida();
		default: return super.getValueAt(row, col);
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 9:
			data.get(rowIndex).setNumPlazas(Integer.valueOf((String) aValue));
			break;
		case 10:
			data.get(rowIndex).setMedida(Float.parseFloat((String) aValue));
			break;
		default:
			super.setValueAt(aValue, rowIndex, columnIndex);
			break;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

}
