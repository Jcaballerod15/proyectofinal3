package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Date;

public class MyFacturaTableModel extends MyTableModel<Alquiler>{
	
	private ArrayList<Vehiculo> data2;
	
	public MyFacturaTableModel(ArrayList<Alquiler> data,ArrayList<Vehiculo> data2,String[] head ) {
		super(data,head);
		this.data2 = data2;
		// TODO Auto-generated constructor stub
	}
	public boolean isCellEditable(int row, int col) {

		if (col == 0 || col == 1 || col == 2) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 3:
			return Date.class;
		case 4:
			return Date.class;
		default:
			return String.class;
		}
	}

	@Override
	public void setValueAt(Object aValue, int row, int col) {
		switch (col) {

		case 0:
			
			data2.get(row).setMatricula(aValue.toString());
			break;
		case 1:
			data2.get(row).setMarca(aValue.toString());
			break;
		case 2:
			data.get(row).setPrecio(Float.parseFloat((String) aValue));
			break;

		case 3:
			java.util.Date fecha = (java.util.Date) aValue;
			data.get(row).setFechainicio(new java.sql.Date(fecha.getTime()));
			break;
		case 4:
			java.util.Date fecha2 = (java.util.Date) aValue;
			data.get(row).setFechafin(new java.sql.Date(fecha2.getTime()));
		//	System.out.println(aValue.toString());
			break;
		
		}

		fireTableCellUpdated(row, col);

	}
	
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0:
			for(int i =0 ; i<data2.size();i++) {
				if(data2.get(i).getMatricula().equals(data.get(row).getMatricula())) {
					return data2.get(i).getMatricula();
				}
			}
			return "";
		case 1: for(int i =0 ; i<data2.size();i++) {
			if(data2.get(i).getMatricula().equals(data.get(row).getMatricula())) {
				return data2.get(i).getMarca();
			}
		}
		return "";
		case 2: return data.get(row).getPrecio();
		case 3: return data.get(row).getFechainicio();
		case 4: return data.get(row).getFechafin();
		default: return null;
		}
	}
}
