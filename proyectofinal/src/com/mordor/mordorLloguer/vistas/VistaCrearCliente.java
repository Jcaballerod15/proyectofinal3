package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

public class VistaCrearCliente extends JInternalFrame{
	private JLabel lblImagen;
	private JTextField txtDni;
	private JTextField txtName;
	private JTextField txtSruname;
	private WebDateField txtDate;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JTextField txtCp;
	private JComboBox <String> comboBoxLicencia;
	private JButton btnAdd;
	private JButton btnCancel;
	private byte[] image;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public VistaCrearCliente() {
		setClosable(true);
		setFrameIcon(new ImageIcon(VistaCrearCliente.class.getResource("/com/mordor/mordorLloguer/recursos/person25dp.png")));
		setBounds(100, 100, 450, 446);
		
		JPanel panelImagen = new JPanel();
		
		JPanel panelDatos = new JPanel();
		
		JPanel panelDatos2 = new JPanel();
		
		btnAdd = new JButton("Add");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelDatos2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panelImagen, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(panelDatos, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addComponent(btnAdd)
							.addGap(18)
							.addComponent(btnCancel)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelDatos, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelImagen, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelDatos2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnCancel))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		panelDatos2.setLayout(new MigLayout("", "[][119.00,left][][][][grow]", "[][]"));
		
		JLabel lblAddress = new JLabel("Address");
		panelDatos2.add(lblAddress, "cell 0 0,alignx trailing");
		
		txtAddress = new JTextField();
		panelDatos2.add(txtAddress, "cell 1 0,growx");
		txtAddress.setColumns(10);
		
		JLabel lblCp = new JLabel("CP");
		panelDatos2.add(lblCp, "cell 4 0,alignx trailing");
		
		txtCp = new JTextField();
		panelDatos2.add(txtCp, "cell 5 0,growx");
		txtCp.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panelDatos2.add(lblEmail, "cell 0 1,alignx trailing");
		
		txtEmail = new JTextField();
		panelDatos2.add(txtEmail, "cell 1 1,growx");
		txtEmail.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving license");
		panelDatos2.add(lblDrivingLicense, "cell 4 1,alignx trailing");
		
		comboBoxLicencia = new JComboBox<String>();
		panelDatos2.add(comboBoxLicencia, "cell 5 1,growx");
		comboBoxLicencia.addItem("A");
		comboBoxLicencia.addItem("B");
		comboBoxLicencia.addItem("C");
		comboBoxLicencia.addItem("D");
		comboBoxLicencia.addItem("E");
		comboBoxLicencia.addItem("Z");
		
		JLabel lblDni = new JLabel("DNI");
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		
		txtSruname = new JTextField();
		txtSruname.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		
		panelDatos.setLayout(new MigLayout("", "[166px,grow]", "[15px][19px][15px][19px][15px][19px][15px][]"));
		panelDatos.add(txtDni, "cell 0 1,growx,aligny top");
		panelDatos.add(lblDni, "cell 0 0,alignx left,aligny top");
		panelDatos.add(lblName, "cell 0 2,alignx left,aligny top");
		panelDatos.add(txtName, "cell 0 3,growx,aligny top");
		panelDatos.add(lblSurname, "cell 0 4,alignx left,aligny top");
		panelDatos.add(txtSruname, "cell 0 5,growx,aligny top");
		panelDatos.add(lblBirthday, "cell 0 6,alignx left,aligny top");
		
		txtDate = new WebDateField();
		panelDatos.add(txtDate, "cell 0 7,growx");
		//txtDate.setColumns(10);
		panelImagen.setLayout(new BorderLayout(0, 0));
		
		lblImagen = new JLabel("");
		panelImagen.add(lblImagen, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);

	}

	public JLabel getLblImagen() {
		return lblImagen;
	}

	public void setLblImagen(JLabel lblImagen) {
		this.lblImagen = lblImagen;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtSruname() {
		return txtSruname;
	}

	public void setTxtSruname(JTextField txtSruname) {
		this.txtSruname = txtSruname;
	}

	public WebDateField getTxtDate() {
		return txtDate;
	}

	public void setTxtDate(WebDateField txtDate) {
		this.txtDate = txtDate;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(JTextField txtAddress) {
		this.txtAddress = txtAddress;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTextField getTxtCp() {
		return txtCp;
	}

	public void setTxtCp(JTextField txtCp) {
		this.txtCp = txtCp;
	}

	public JComboBox getComboBoxLicencia() {
		return comboBoxLicencia;
	}

	public void setComboBoxLicencia(JComboBox comboBoxLicencia) {
		this.comboBoxLicencia = comboBoxLicencia;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
	public void setImage(byte[] image) {

		this.image = image;

		if (image != null) {

			try {

				BufferedImage ima = null;

				InputStream in = new ByteArrayInputStream(image);

				ima = ImageIO.read(in);

				ImageIcon icono = new ImageIcon(ima);

				Image imageToResize = icono.getImage();

				Image nuevaResized = imageToResize.getScaledInstance(410, 150, java.awt.Image.SCALE_SMOOTH);

				lblImagen.setIcon(new ImageIcon(nuevaResized));

			} catch (IOException e) {

				e.printStackTrace();

			}

		} else {

			lblImagen.setIcon(
					new ImageIcon(VistaCrearCliente.class.getResource("/com/mordor/mordorLloguer/recursos/descarga.png")));

		}

	}

	public byte[] getImage() {
		return image;
	}
}
