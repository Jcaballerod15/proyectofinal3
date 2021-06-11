package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class MyVehicleTableModel<T extends Vehiculo> extends MyTableModel<T> {
	
	public MyVehicleTableModel(ArrayList<T> data,String[] head) {
		super(data,head);
		// TODO Auto-generated constructor stub
	}
	public boolean isCellEditable(int row, int col) {

		if (col == 0 || col == 8) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 6:
			return Date.class;
		default:
			return String.class;
		}
	}

	@Override
	public void setValueAt(Object aValue, int row, int col) {
		switch (col) {

		case 0:
			data.get(row).setMatricula(aValue.toString());
			break;
		case 1:
			data.get(row).setPrecioDia(Float.parseFloat((String) aValue));
			break;
		case 2:
			data.get(row).setDescripcion(aValue.toString());
			break;

		case 3:
			data.get(row).setColor(aValue.toString());
			break;
		case 4:
			data.get(row).setMotor(aValue.toString());
			System.out.println(aValue.toString());
			break;
		case 5:
			data.get(row).setCilindrada(Integer.valueOf((String) aValue));
			break;
		case 6:
			java.util.Date fecha = (java.util.Date) aValue;
			data.get(row).setFechaAdq(new java.sql.Date(fecha.getTime()));
			break;
		case 7:
			data.get(row).setEstado(aValue.toString());
			break;
		case 8:
			data.get(row).setCarnet(aValue.toString());
			break;
		}

		fireTableCellUpdated(row, col);

	}
	
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0: return data.get(row).getMatricula();
		case 1: return data.get(row).getPrecioDia();
		case 2: return data.get(row).getMarca();
		case 3: return data.get(row).getColor();
		case 4: return data.get(row).getMotor();
		case 5: return data.get(row).getCilindrada();
		case 6: return data.get(row).getFechaAdq();
		case 7: return data.get(row).getEstado();
		case 8: return data.get(row).getCarnet();
		default: return null;
		}
	}
}
