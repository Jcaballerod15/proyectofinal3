package com.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VistaTablaVehiculos extends JInternalFrame {

	private JPanel contentPane;
	//private MyCarTableModel mctm;
	private JTabbedPane tabbedPane;
	private JButton btnAdd;
	private JButton btnDelete;
	private JPVehiculo panelCar;
	private JPVehiculo panelVan;
	private JPVehiculo panelTruck;
	private JPVehiculo panelMicrobus;

	/**
	 * Create the frame.
	 */
	public VistaTablaVehiculos() {
		setFrameIcon(new ImageIcon(VistaTablaVehiculos.class.getResource("/com/mordor/mordorLloguer/recursos/car25dp.png")));
		setClosable(true);
		setBounds(100, 100, 809, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		btnAdd = new JButton("Add");
		panel_1.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		
		panelCar = new JPVehiculo();
		panelVan = new JPVehiculo();
		panelTruck = new JPVehiculo();
		panelMicrobus = new JPVehiculo();
		
		//mctm = new MyCarTableModel(new ArrayList<Coche>());
		//panelCar.getTable().setModel(mctm);
		
		tabbedPane.addTab("Car", null, panelCar, null);
		tabbedPane.addTab("Van", null, panelVan, null);
		tabbedPane.addTab("Truck", null, panelTruck, null);		
		tabbedPane.addTab("Microbus", null, panelMicrobus, null);
		contentPane.setLayout(gl_contentPane);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	public JTable getCarTable() {
		return panelCar.getTable();
	}
	public JTable getVanTable() {
		return panelVan.getTable();
	}
	public JTable getTruckTable() {
		return panelTruck.getTable();
	}
	public JTable getMicrobusTable() {
		return panelMicrobus.getTable();
	}

	public JPVehiculo getPanelCar() {
		return panelCar;
	}

	public JPVehiculo getPanelVan() {
		return panelVan;
	}

	public JPVehiculo getPanelTruck() {
		return panelTruck;
	}

	public JPVehiculo getPanelMicrobus() {
		return panelMicrobus;
	}
	
}
