package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import com.jgoodies.forms.layout.FormLayout;
import com.alee.extended.date.WebDateField;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCrearEmpleado extends JInternalFrame {
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDomicilio;
	private JTextField txtCp;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private WebDateField txtDate;
	private JButton btnAdd;
	private JButton btnCancel;
	private JComboBox<String>  comboBoxCargo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaCrearEmpleado() {
		setClosable(true);
		setFrameIcon(new ImageIcon(VistaCrearEmpleado.class.getResource("/com/mordor/mordorLloguer/recursos/account_circle24dp.png")));
		setBounds(100, 100, 467, 435);
		getContentPane().setLayout(new MigLayout("", "[152px][235px]", "[19px][19px][19px][19px][19px][19px][45px][24px][19px][25px]"));
		
		JLabel lblDni = new JLabel("DNI: ");
		getContentPane().add(lblDni, "cell 0 0,growx,aligny center");
		
		txtDNI = new JTextField();
		getContentPane().add(txtDNI, "cell 1 0,growx,aligny top");
		txtDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		getContentPane().add(lblNombre, "cell 0 1,growx,aligny center");
		
		txtNombre = new JTextField();
		getContentPane().add(txtNombre, "cell 1 1,growx,aligny top");
		txtNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		getContentPane().add(lblApellidos, "cell 0 2,growx,aligny center");
		
		txtApellidos = new JTextField();
		getContentPane().add(txtApellidos, "cell 1 2,growx,aligny top");
		txtApellidos.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		getContentPane().add(lblDomicilio, "cell 0 3,growx,aligny center");
		
		txtDomicilio = new JTextField();
		getContentPane().add(txtDomicilio, "cell 1 3,growx,aligny top");
		txtDomicilio.setColumns(10);
		
		JLabel lblCp = new JLabel("CP:");
		getContentPane().add(lblCp, "cell 0 4,growx,aligny center");
		
		txtCp = new JTextField();
		getContentPane().add(txtCp, "cell 1 4,growx,aligny top");
		txtCp.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		getContentPane().add(lblEmail, "cell 0 5,growx,aligny center");
		
		txtEmail = new JTextField();
		getContentPane().add(txtEmail, "cell 1 5,growx,aligny top");
		txtEmail.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		getContentPane().add(lblFechaDeNacimiento, "cell 0 6,alignx left,aligny center");
		
		txtDate = new WebDateField();
		getContentPane().add(txtDate, "cell 1 6,growx,aligny top");
		
		JLabel lblCargo = new JLabel("Cargo:");
		getContentPane().add(lblCargo, "cell 0 7,growx,aligny center");
		
		comboBoxCargo= new JComboBox<String> ();
		getContentPane().add(comboBoxCargo, "cell 1 7,growx,aligny top");
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		getContentPane().add(lblContrasea, "cell 0 8,growx,aligny center");
		
		passwordField = new JPasswordField();
		getContentPane().add(passwordField, "cell 1 8,growx,aligny top");
		
		btnAdd = new JButton("Add");
		getContentPane().add(btnAdd, "cell 0 9,growx,aligny top");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancel, "cell 1 9,growx,aligny top");

	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellidos() {
		return txtApellidos;
	}

	public JTextField getTxtDomicilio() {
		return txtDomicilio;
	}

	public JTextField getTxtCp() {
		return txtCp;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public WebDateField getTxtDate() {
		return txtDate;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JComboBox getComboBoxCargo() {
		return comboBoxCargo;
	}
	

}
