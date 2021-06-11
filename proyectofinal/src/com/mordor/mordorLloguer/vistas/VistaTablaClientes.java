package com.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VistaTablaClientes extends JInternalFrame {

	private WebTable tableEmpleados;
	private JButton btnCrear;
	private JButton btnBorrar;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAddRow_1;
	private JMenuItem mntmDeleteRow_1;
	private JTextField txtDNI;
	private JTextField txtName;
	private JTextField txtSurname;
	private JComboBox comboBoxCarnet;
	private JButton btnEdit;
	private JButton btnPrint;

	

	/**
	 * Create the frame.
	 */
	public VistaTablaClientes() {
		setClosable(true);
		setFrameIcon(new ImageIcon(VistaTablaClientes.class.getResource("/com/mordor/mordorLloguer/recursos/groups24dp.png")));
		setBounds(100, 100, 710, 354);

		JPanel panelTabla = new JPanel();

		JPanel panelCrear = new JPanel();

		JPanel panelBorra = new JPanel();
		
		JPanel panelOrdenar = new JPanel();
		
				btnCrear = new JButton("Add");
		
				btnBorrar = new JButton("Delete");
		
		JPanel panelEdit = new JPanel();
		
		JPanel panelPrint = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
								.addComponent(panelOrdenar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(panelEdit, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(87)
									.addComponent(panelBorra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
									.addComponent(panelPrint, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addGap(26))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panelCrear, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(502, Short.MAX_VALUE))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelOrdenar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelTabla, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelCrear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(panelBorra, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelPrint, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelEdit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnCrear)
									.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
							.addGap(22))))
		);
		panelPrint.setLayout(new BorderLayout(0, 0));
		
		btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(VistaTablaClientes.class.getResource("/com/mordor/mordorLloguer/recursos/print25dp.png")));
		panelPrint.add(btnPrint, BorderLayout.CENTER);
		panelEdit.setLayout(new BorderLayout(0, 0));
		
		btnEdit = new JButton("Edit");
		panelEdit.add(btnEdit, BorderLayout.CENTER);
		
		JLabel lblDNI = new JLabel("DNI");
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		
		JLabel lblLicense = new JLabel("license");
		
		comboBoxCarnet = new JComboBox();
		GroupLayout gl_panelOrdenar = new GroupLayout(panelOrdenar);
		gl_panelOrdenar.setHorizontalGroup(
			gl_panelOrdenar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrdenar.createSequentialGroup()
					.addComponent(lblDNI)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSurname)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLicense)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBoxCarnet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_panelOrdenar.setVerticalGroup(
			gl_panelOrdenar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrdenar.createSequentialGroup()
					.addGroup(gl_panelOrdenar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOrdenar.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDNI)
							.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblName)
							.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSurname)
							.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblLicense))
						.addComponent(comboBoxCarnet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelOrdenar.setLayout(gl_panelOrdenar);
		panelBorra.setLayout(new BorderLayout(0, 0));
		panelCrear.setLayout(new BorderLayout(0, 0));
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

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtSurname() {
		return txtSurname;
	}

	public JComboBox getComboBoxCarnet() {
		return comboBoxCarnet;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnPrint() {
		return btnPrint;
	}
	
}
