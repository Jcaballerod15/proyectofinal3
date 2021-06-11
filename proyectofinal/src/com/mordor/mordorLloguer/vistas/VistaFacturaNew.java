package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.alee.extended.date.WebDateField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaFacturaNew extends JInternalFrame {
	private JTextField txtDNI;
	private JLabel lblDatosDeLa;
	private JTextField txtMatricula;
	private WebDateField txtFechainicio;
	private WebDateField txtFechafin;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaFacturaNew() {
		setClosable(true);
		setFrameIcon(new ImageIcon(VistaFacturaNew.class.getResource("/com/mordor/mordorLloguer/recursos/invoice.png")));
		setBounds(100, 100, 450, 490);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lblDatosDeLa = new JLabel("Datos de la factura");
		lblDatosDeLa.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblDatosDeLa);
		panel_1.setLayout(new MigLayout("", "[][][][-7.00][grow][][][]", "[][][][][][][][][][][][]"));
		
		JLabel lblDni = new JLabel("DNI ");
		panel_1.add(lblDni, "cell 4 0,alignx center");
		
		txtDNI = new JTextField();
		panel_1.add(txtDNI, "cell 4 1,growx");
		txtDNI.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		panel_1.add(lblMatricula, "cell 4 3,alignx center");
		
		txtMatricula = new JTextField();
		panel_1.add(txtMatricula, "cell 4 4,growx");
		txtMatricula.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		panel_1.add(lblFechaInicio, "cell 4 6,alignx center");
		
		txtFechainicio = new WebDateField();
		panel_1.add(txtFechainicio, "cell 4 7,growx");
		//txtFechainicio.setColumns(10);
		
		JLabel lblFechaFinal = new JLabel("Fecha final");
		panel_1.add(lblFechaFinal, "cell 4 9,alignx center");
		
		txtFechafin = new WebDateField();
		panel_1.add(txtFechafin, "cell 4 10,growx");
		
		btnAdd = new JButton("Add");
		panel_1.add(btnAdd, "cell 4 11,alignx center");
		//txtFechafin.setColumns(10);
		getContentPane().setLayout(groupLayout);

	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public JLabel getLblDatosDeLa() {
		return lblDatosDeLa;
	}

	public JTextField getTxtMatricula() {
		return txtMatricula;
	}

	public WebDateField getTxtFechainicio() {
		return txtFechainicio;
	}

	public WebDateField getTxtFechafin() {
		return txtFechafin;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
	
}
