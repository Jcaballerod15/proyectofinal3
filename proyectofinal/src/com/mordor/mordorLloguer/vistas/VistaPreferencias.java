package com.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VistaPreferencias extends JInternalFrame {
	private JTextField txtURL;
	private JTextField textDriver;
	private JButton btnGuardar;
	private JTextField txtUser;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public VistaPreferencias() {
		setTitle("Preferencias");
		setFrameIcon(new ImageIcon(VistaPreferencias.class.getResource("/com/mordor/mordorLloguer/recursos/settings_25dp.png")));
		setBounds(100, 100, 450, 300);
		
		JPanel panelDriver = new JPanel();
		
		JPanel panelURL = new JPanel();
		
		JPanel panelUser = new JPanel();
		
		JPanel panelPassowrd = new JPanel();
		
		JPanel panelBoton = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelUser, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addComponent(panelURL, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
								.addComponent(panelDriver, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addComponent(panelPassowrd, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
							.addGap(8))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelBoton, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
							.addGap(10))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelDriver, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelURL, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelPassowrd, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBoton, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
					.addGap(11))
		);
		panelBoton.setLayout(new BorderLayout(0, 0));
		
		btnGuardar = new JButton("Guardar");
		panelBoton.add(btnGuardar, BorderLayout.NORTH);
		GridBagLayout gbl_panelPassowrd = new GridBagLayout();
		gbl_panelPassowrd.columnWidths = new int[]{80, 0, 0};
		gbl_panelPassowrd.rowHeights = new int[]{43, 0};
		gbl_panelPassowrd.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelPassowrd.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelPassowrd.setLayout(gbl_panelPassowrd);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 0;
		panelPassowrd.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		panelPassowrd.add(passwordField, gbc_passwordField);
		GridBagLayout gbl_panelUser = new GridBagLayout();
		gbl_panelUser.columnWidths = new int[]{78, 0, 0};
		gbl_panelUser.rowHeights = new int[]{50, 0};
		gbl_panelUser.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelUser.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelUser.setLayout(gbl_panelUser);
		
		JLabel lblUser = new JLabel("User");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 0, 5);
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 0;
		panelUser.add(lblUser, gbc_lblUser);
		
		txtUser = new JTextField();
		GridBagConstraints gbc_txtUser = new GridBagConstraints();
		gbc_txtUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUser.gridx = 1;
		gbc_txtUser.gridy = 0;
		panelUser.add(txtUser, gbc_txtUser);
		txtUser.setColumns(10);
		GridBagLayout gbl_panelDriver = new GridBagLayout();
		gbl_panelDriver.columnWidths = new int[]{76, 0, 0};
		gbl_panelDriver.rowHeights = new int[]{43, 0};
		gbl_panelDriver.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelDriver.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDriver.setLayout(gbl_panelDriver);
		
		JLabel lblDriver = new JLabel("Driver");
		GridBagConstraints gbc_lblDriver = new GridBagConstraints();
		gbc_lblDriver.insets = new Insets(0, 0, 0, 5);
		gbc_lblDriver.anchor = GridBagConstraints.EAST;
		gbc_lblDriver.gridx = 0;
		gbc_lblDriver.gridy = 0;
		panelDriver.add(lblDriver, gbc_lblDriver);
		
		textDriver = new JTextField();
		GridBagConstraints gbc_textDriver = new GridBagConstraints();
		gbc_textDriver.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDriver.gridx = 1;
		gbc_textDriver.gridy = 0;
		panelDriver.add(textDriver, gbc_textDriver);
		textDriver.setColumns(10);
		GridBagLayout gbl_panelURL = new GridBagLayout();
		gbl_panelURL.columnWidths = new int[]{77, 0, 0};
		gbl_panelURL.rowHeights = new int[]{44, 0};
		gbl_panelURL.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelURL.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelURL.setLayout(gbl_panelURL);
		
		JLabel lblUrl = new JLabel("URL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.insets = new Insets(0, 0, 0, 5);
		gbc_lblUrl.anchor = GridBagConstraints.EAST;
		gbc_lblUrl.gridx = 0;
		gbc_lblUrl.gridy = 0;
		panelURL.add(lblUrl, gbc_lblUrl);
		
		txtURL = new JTextField();
		GridBagConstraints gbc_txtURL = new GridBagConstraints();
		gbc_txtURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtURL.gridx = 1;
		gbc_txtURL.gridy = 0;
		panelURL.add(txtURL, gbc_txtURL);
		txtURL.setColumns(10);
		getContentPane().setLayout(groupLayout);

	}

	public JTextField getTxtURL() {
		return txtURL;
	}

	public JTextField getTextDriver() {
		return textDriver;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
}
