package com.mordor.mordorLloguer.vistas;

import javax.swing.JPanel;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class JPVehiculo extends JPanel {
	private JTable table;
	private JTextField txtModel;
	private JTextField txtMatricula;
	private JComboBox cboxMotor;
	private JComboBox cboxCarnet;

	/**
	 * Create the panel.
	 */
	public JPVehiculo() {
		
		JPanel panelSearch = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(panelSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelSearch, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelSearch.setLayout(new MigLayout("", "[][117.00][61.00][][][72.00][58.00][grow]", "[]"));
		
		JLabel lblRegistration = new JLabel("Registration");
		panelSearch.add(lblRegistration, "cell 0 0,alignx trailing");
		
		txtMatricula = new JTextField();
		panelSearch.add(txtMatricula, "cell 1 0");
		txtMatricula.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		panelSearch.add(lblModel, "flowx,cell 2 0,alignx left");
		
		txtModel = new JTextField();
		panelSearch.add(txtModel, "cell 3 0");
		txtModel.setColumns(10);
		
		JLabel lblEngine = new JLabel("Engine");
		panelSearch.add(lblEngine, "cell 4 0,alignx trailing");
		
		cboxMotor = new JComboBox();
		panelSearch.add(cboxMotor, "cell 5 0,growx,aligny top");
		
		JLabel lblLicense = new JLabel("License");
		panelSearch.add(lblLicense, "cell 6 0,alignx trailing");
		
		cboxCarnet = new JComboBox();
		panelSearch.add(cboxCarnet, "cell 7 0");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

	public JTable getTable() {
		return table;
	}

	public JTextField gettxtModel() {
		return txtModel;
	}

	public JTextField getTxtMatricula() {
		return txtMatricula;
	}

	public JComboBox getCboxMotor() {
		return cboxMotor;
	}

	public JComboBox getCboxCarnet() {
		return cboxCarnet;
	}
	
}
	
	
