package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class VistaTablaEmpleados extends JInternalFrame {
	private WebTable tableEmpleados;
	private JButton btnCrear;
	private JButton btnBorrar;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAddRow_1;
	private JMenuItem mntmDeleteRow_1;
	private JComboBox<String> cboxOrdenDatos;
	private JComboBox<String> cboxAscenDescen;

	

	/**
	 * Create the frame.
	 */
	public VistaTablaEmpleados() {
		setClosable(true);
		setFrameIcon(new ImageIcon(
				VistaTablaEmpleados.class.getResource("/com/mordor/mordorLloguer/recursos/employee_25dp.png")));
		setBounds(100, 100, 496, 354);

		JPanel panelTabla = new JPanel();

		JPanel panelCrear = new JPanel();

		JPanel panelBorra = new JPanel();
		
		JPanel panelOrdenar = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelOrdenar, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelCrear, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panelBorra, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelOrdenar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabla, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCrear, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBorra, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		cboxOrdenDatos = new JComboBox<String>();
		
		cboxAscenDescen = new JComboBox<String>();
		GroupLayout gl_panelOrdenar = new GroupLayout(panelOrdenar);
		gl_panelOrdenar.setHorizontalGroup(
			gl_panelOrdenar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrdenar.createSequentialGroup()
					.addComponent(cboxOrdenDatos, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cboxAscenDescen, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(236, Short.MAX_VALUE))
		);
		gl_panelOrdenar.setVerticalGroup(
			gl_panelOrdenar.createParallelGroup(Alignment.LEADING)
				.addComponent(cboxAscenDescen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
				.addComponent(cboxOrdenDatos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
		);
		panelOrdenar.setLayout(gl_panelOrdenar);
		panelBorra.setLayout(new BorderLayout(0, 0));

		btnBorrar = new JButton("Delete");
		panelBorra.add(btnBorrar, BorderLayout.CENTER);
		panelCrear.setLayout(new BorderLayout(0, 0));

		btnCrear = new JButton("Add");
		panelCrear.add(btnCrear, BorderLayout.CENTER);
		panelTabla.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		tableEmpleados = new WebTable();
		scrollPane.setViewportView(tableEmpleados);
		getContentPane().setLayout(groupLayout);
		
		popupMenu = new JPopupMenu();

		mntmAddRow_1 = new JMenuItem("Add row");
		popupMenu.add(mntmAddRow_1);

		mntmDeleteRow_1 = new JMenuItem("Delete row");
		popupMenu.add(mntmDeleteRow_1);

	}

	public WebTable getTableEmpleados() {
		return tableEmpleados;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public JMenuItem getMntmAddRow_1() {
		return mntmAddRow_1;
	}

	public JMenuItem getMntmDeleteRow_1() {
		return mntmDeleteRow_1;
	}

	public JComboBox getCboxOrdenDatos() {
		return cboxOrdenDatos;
	}

	public JComboBox getCboxAscenDescen() {
		return cboxAscenDescen;
	}
	
}
