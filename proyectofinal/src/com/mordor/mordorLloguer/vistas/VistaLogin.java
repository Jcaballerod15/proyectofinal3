package com.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.ImageIcon;

public class VistaLogin extends JInternalFrame {

	private JPanel contentPane;
	private JPasswordField passwordFieldUser;
	private JTextField txtUsuario;
	private JButton btnLogin;
	private JProgressBar progressBar;


	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setFrameIcon(new ImageIcon(VistaLogin.class.getResource("/com/mordor/mordorLloguer/recursos/login_25dp.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel lblMemberLogin = new JLabel("    Member Login");
		lblMemberLogin.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(lblMemberLogin);
		
		JPanel panelMedio = new JPanel();
		contentPane.add(panelMedio);
		
		JPanel panelUser = new JPanel();
		
		JPanel panelLogin = new JPanel();
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_panelMedio = new GroupLayout(panelMedio);
		gl_panelMedio.setHorizontalGroup(
			gl_panelMedio.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelMedio.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMedio.createParallelGroup(Alignment.LEADING)
						.addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addComponent(panelUser, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addComponent(lblUser)
						.addComponent(lblPassword))
					.addContainerGap())
		);
		gl_panelMedio.setVerticalGroup(
			gl_panelMedio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMedio.createSequentialGroup()
					.addComponent(lblUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		passwordFieldUser = new JPasswordField();
		panelLogin.add(passwordFieldUser, BorderLayout.CENTER);
		panelUser.setLayout(new BorderLayout(0, 0));
		
		txtUsuario = new JTextField();
		panelUser.add(txtUsuario, BorderLayout.CENTER);
		txtUsuario.setColumns(10);
		panelMedio.setLayout(gl_panelMedio);
		
		JPanel panelIferior = new JPanel();
		contentPane.add(panelIferior);
		
		JPanel panelBotonLogin = new JPanel();
		
		JPanel panelProgresBar = new JPanel();
		GroupLayout gl_panelIferior = new GroupLayout(panelIferior);
		gl_panelIferior.setHorizontalGroup(
			gl_panelIferior.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelIferior.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelIferior.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelProgresBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addComponent(panelBotonLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelIferior.setVerticalGroup(
			gl_panelIferior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIferior.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelProgresBar, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelBotonLogin, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelProgresBar.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		panelProgresBar.add(progressBar, BorderLayout.CENTER);
		panelBotonLogin.setLayout(new BorderLayout(0, 0));
		
		btnLogin = new JButton("Login");
		panelBotonLogin.add(btnLogin, BorderLayout.CENTER);
		panelIferior.setLayout(gl_panelIferior);
	}


	public JPasswordField getPasswordFieldUser() {
		return passwordFieldUser;
	}


	public JTextField getTxtUsuario() {
		return txtUsuario;
	}


	public JButton getBtnLogin() {
		return btnLogin;
	}


	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	
}
