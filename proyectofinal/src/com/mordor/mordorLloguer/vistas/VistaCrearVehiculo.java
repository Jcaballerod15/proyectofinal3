package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;

public class VistaCrearVehiculo extends JInternalFrame {
	private JTextField txtMatricula;
	private JTextField txtPreciodia;
	private JButton btnAdd;
	private JTextField txtMarca;
	private JTextField txtColor;
	private JComboBox<String> comboBoxMotor;
	private JTextField txtCilindrada;
	private WebDateField txtFecha;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<String>  comboBoxCarnet;
	private JTextPane textPaneDescrip;
	private JLabel labelTextoAtributoExtra;
	private JTextField txtDatosExtra1;
	private JLabel lblTextoAtributoExtra2;
	private JTextField txtDatosExtra2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaCrearVehiculo() {
		setClosable(true);
		setFrameIcon(new ImageIcon(VistaCrearVehiculo.class.getResource("/com/mordor/mordorLloguer/recursos/car25dp.png")));
		setBounds(100, 100, 499, 362);
		
		JPanel panelDatosVehiculo = new JPanel();
		
		JPanel panelBoton = new JPanel();
		
		JPanel panelExpecificacion = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelDatosVehiculo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
						.addComponent(panelExpecificacion, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
						.addComponent(panelBoton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelDatosVehiculo, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelExpecificacion, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelBoton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GridBagLayout gbl_panelExpecificacion = new GridBagLayout();
		gbl_panelExpecificacion.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelExpecificacion.rowHeights = new int[]{36, 0};
		gbl_panelExpecificacion.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelExpecificacion.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelExpecificacion.setLayout(gbl_panelExpecificacion);
		
		labelTextoAtributoExtra = new JLabel("hola");
		GridBagConstraints gbc_labelTextoAtributoExtra = new GridBagConstraints();
		gbc_labelTextoAtributoExtra.insets = new Insets(0, 0, 0, 5);
		gbc_labelTextoAtributoExtra.gridx = 0;
		gbc_labelTextoAtributoExtra.gridy = 0;
		panelExpecificacion.add(labelTextoAtributoExtra, gbc_labelTextoAtributoExtra);
		
		txtDatosExtra1 = new JTextField();
		GridBagConstraints gbc_txtDatosExtra1 = new GridBagConstraints();
		gbc_txtDatosExtra1.insets = new Insets(0, 0, 0, 5);
		gbc_txtDatosExtra1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDatosExtra1.gridx = 2;
		gbc_txtDatosExtra1.gridy = 0;
		panelExpecificacion.add(txtDatosExtra1, gbc_txtDatosExtra1);
		txtDatosExtra1.setColumns(10);
		
		lblTextoAtributoExtra2 = new JLabel("hola");
		GridBagConstraints gbc_lblTextoAtributoExtra2 = new GridBagConstraints();
		gbc_lblTextoAtributoExtra2.insets = new Insets(0, 0, 0, 5);
		gbc_lblTextoAtributoExtra2.anchor = GridBagConstraints.EAST;
		gbc_lblTextoAtributoExtra2.gridx = 4;
		gbc_lblTextoAtributoExtra2.gridy = 0;
		panelExpecificacion.add(lblTextoAtributoExtra2, gbc_lblTextoAtributoExtra2);
		
		txtDatosExtra2 = new JTextField();
		GridBagConstraints gbc_txtDatosExtra2 = new GridBagConstraints();
		gbc_txtDatosExtra2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDatosExtra2.gridx = 5;
		gbc_txtDatosExtra2.gridy = 0;
		panelExpecificacion.add(txtDatosExtra2, gbc_txtDatosExtra2);
		txtDatosExtra2.setColumns(10);
		panelDatosVehiculo.setLayout(new MigLayout("", "[72px][104px][123px][5px][101px]", "[90px][24px][24px][19px][24px][5px][19px]"));
		
		JLabel lblMatricula = new JLabel("Matricula");
		panelDatosVehiculo.add(lblMatricula, "cell 0 0,alignx right,aligny center");
		
		txtMatricula = new JTextField();
		panelDatosVehiculo.add(txtMatricula, "cell 1 0,growx,aligny center");
		txtMatricula.setColumns(10);
		
		JLabel lblFechaAdquirido = new JLabel("Fecha adquirido");
		panelDatosVehiculo.add(lblFechaAdquirido, "cell 2 0,alignx right,aligny center");
		
		txtFecha = new WebDateField();
		panelDatosVehiculo.add(txtFecha, "cell 4 0,growx,aligny center");
		//txtFecha.setColumns(10);
		
		JLabel lblPrecioDia = new JLabel("Precio Dia");
		panelDatosVehiculo.add(lblPrecioDia, "cell 0 1,alignx right,aligny center");
		
		txtPreciodia = new JTextField();
		panelDatosVehiculo.add(txtPreciodia, "cell 1 1,growx,aligny center");
		txtPreciodia.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		panelDatosVehiculo.add(lblEstado, "cell 2 1,alignx right,aligny center");
		
		comboBoxEstado = new JComboBox<String>();
		panelDatosVehiculo.add(comboBoxEstado, "cell 4 1,growx,aligny center");
		comboBoxEstado.addItem("Preparado");
		comboBoxEstado.addItem("Taller");
		
		JLabel lblMarca = new JLabel("Marca");
		panelDatosVehiculo.add(lblMarca, "cell 0 2,alignx right,aligny center");
		
		txtMarca = new JTextField();
		panelDatosVehiculo.add(txtMarca, "cell 1 2,growx,aligny center");
		txtMarca.setColumns(10);
		
		JLabel lblCarnet = new JLabel("Carnet");
		panelDatosVehiculo.add(lblCarnet, "cell 2 2,alignx right,aligny center");
		
		comboBoxCarnet = new JComboBox<String>();
		panelDatosVehiculo.add(comboBoxCarnet, "cell 4 2,growx,aligny center");
		comboBoxCarnet.addItem("A");
		comboBoxCarnet.addItem("B");
		comboBoxCarnet.addItem("C");
		comboBoxCarnet.addItem("D");
		comboBoxCarnet.addItem("E");
		comboBoxCarnet.addItem("Z");
		
		
		JLabel lblColor = new JLabel("Color");
		panelDatosVehiculo.add(lblColor, "cell 0 3,alignx right,aligny center");
		
		txtColor = new JTextField();
		panelDatosVehiculo.add(txtColor, "cell 1 3,growx,aligny center");
		txtColor.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		panelDatosVehiculo.add(lblDescripcin, "cell 2 3,alignx left,aligny center");
		
		JLabel lblMotor = new JLabel("Motor");
		panelDatosVehiculo.add(lblMotor, "cell 0 4,alignx right,aligny center");
		
		comboBoxMotor = new JComboBox<String>();
		panelDatosVehiculo.add(comboBoxMotor, "cell 1 4,growx,aligny center");
		comboBoxMotor.addItem("Electrico");
		comboBoxMotor.addItem("Diesel");
		comboBoxMotor.addItem("hibrido enchufable");
		comboBoxMotor.addItem("diesel");
		
		textPaneDescrip = new JTextPane();
		panelDatosVehiculo.add(textPaneDescrip, "cell 2 4 3 3,grow");
		
		JLabel lblCilindrada = new JLabel("Cilindrada");
		panelDatosVehiculo.add(lblCilindrada, "cell 0 6,alignx right,aligny center");
		
		txtCilindrada = new JTextField();
		panelDatosVehiculo.add(txtCilindrada, "cell 1 6,growx,aligny center");
		txtCilindrada.setColumns(10);
		
		btnAdd = new JButton("Add");
		panelBoton.add(btnAdd);
		getContentPane().setLayout(groupLayout);
		
		
	}

	public JTextField getTxtMatricula() {
		return txtMatricula;
	}

	public JTextField getTxtPreciodia() {
		return txtPreciodia;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public JComboBox getComboBoxMotor() {
		return comboBoxMotor;
	}

	public JTextField getTxtCilindrada() {
		return txtCilindrada;
	}

	public WebDateField getTxtFecha() {
		return txtFecha;
	}

	public JComboBox getComboBoxEstado() {
		return comboBoxEstado;
	}

	public JComboBox getComboBoxCarnet() {
		return comboBoxCarnet;
	}

	public JTextPane getTextPaneDescrip() {
		return textPaneDescrip;
	}

	public JLabel getLabelTextoAtributoExtra() {
		return labelTextoAtributoExtra;
	}

	public JTextField getTxtDatosExtra1() {
		return txtDatosExtra1;
	}

	public JLabel getLblTextoAtributoExtra2() {
		return lblTextoAtributoExtra2;
	}

	public JTextField getTxtDatosExtra2() {
		return txtDatosExtra2;
	}
}
