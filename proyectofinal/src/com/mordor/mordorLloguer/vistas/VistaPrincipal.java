package com.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnEmployee;
	private JButton btnLogout;
	private JButton btnLogin;
	private JDesktopPane desktopPane;
	private JMenuItem mntmPreferencias;
	private JMenuItem mntmExit;
	private JButton btnCustomer;
	private JButton btnVehiculos;
	private JButton btnFactura;
	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/rent.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1063, 758);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmPreferencias = new JMenuItem("Preferencias");
		mnEdit.add(mntmPreferencias);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.putClientProperty("styleId", "attached-north");
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/login_25dp.png")));
		toolBar.add(btnLogin);
		
		btnLogout = new JButton("");
		btnLogout.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/logout_25dp.png")));
		toolBar.add(btnLogout);
		
		toolBar.addSeparator(new Dimension(10,10));
		
		btnEmployee = new JButton("");
		btnEmployee.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/employee_25dp.png")));
		toolBar.add(btnEmployee);
		
		btnCustomer = new JButton("");
		btnCustomer.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/groups24dp.png")));
		toolBar.add(btnCustomer);
		
		btnVehiculos = new JButton("");
		btnVehiculos.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/car25dp.png")));
		toolBar.add(btnVehiculos);
		
		toolBar.addSeparator(new Dimension(10,10));
		
		btnFactura = new JButton("");
		btnFactura.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/recursos/invoice.png")));
		toolBar.add(btnFactura);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	public JButton getBtnEmployee() {
		return btnEmployee;
	}

	public void setBtnEmployee(JButton btnEmployee) {
		this.btnEmployee = btnEmployee;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public void setBtnLogout(JButton btnLogout) {
		this.btnLogout = btnLogout;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuItem getMntmPreferencias() {
		return mntmPreferencias;
	}

	public JMenuItem getMntmExit() {
		return mntmExit;
	}

	public JButton getBtnCustomer() {
		return btnCustomer;
	}

	public JButton getBtnVehiculos() {
		return btnVehiculos;
	}

	public JButton getBtnFactura() {
		return btnFactura;
	}
	
}
