package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

public abstract class MyTableModel<T> extends AbstractTableModel {

	private String[] HEADER;
	protected List<T> data;

	public MyTableModel(List<T> data, String[] header) {
		this.data = data;
		this.HEADER = header;
	}

	@Override
	public int getColumnCount() {
		return HEADER.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int column) {
		return HEADER[column];
	}

	@Override
	public abstract void setValueAt(Object aValue, int row, int col);

	@Override
	public abstract Object getValueAt(int row, int col);

	public void add(T cadena) {
		if (data.contains(cadena) == false) {
			data.add(cadena);
			//System.out.println(data);
			fireTableRowsInserted(data.size() - 1, data.size() - 1);
		} else {
			this.remove(cadena);
		}
	}

	public ArrayList<T> get(int[] rows) {
		ArrayList<T> objetos = new ArrayList<T>();
		for (int row : rows)
			objetos.add(get(row));

		return objetos;
	}

	public T get(int row) {
		return data.get(row);
	}

	public void setComparator(Comparator<T> compararator) {
		Collections.sort(data, compararator);
		fireTableDataChanged();
	}

	public void remove(T employee) {
		int pos = data.indexOf(employee);
		data.remove(employee);
		this.fireTableRowsDeleted(pos, pos);
	}

}
