package com.mordor.mordorLloguer.model;

import java.util.ArrayList;

public class MyVanTableModel extends MyVehicleTableModel<Furgoneta>{
	protected static String[] head = { "Matricula", "PrecioDia", "Marca", "Color", "Motor", "Cilindrada", "FechaAdq",
			"Estado", "Carnet", "MMA"};

	public MyVanTableModel(ArrayList<Furgoneta> data) {
		super(data,head);
		// TODO Auto-generated constructor stub
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 9:
			return data.get(row).getMMA();
		default:
			return super.getValueAt(row, col);
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 9:
			data.get(rowIndex).setMMA(Integer.valueOf((String) aValue));
			break;
		default:
			super.setValueAt(aValue, rowIndex, columnIndex);
			break;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
