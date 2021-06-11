package com.mordor.mordorLloguer.controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.conf.MyConfig;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.MyOracleDataBase;
import com.mordor.mordorLloguer.vistas.VistaCargarBD;
import com.mordor.mordorLloguer.vistas.VistaLogin;
import com.mordor.mordorLloguer.vistas.VistaPreferencias;
import com.mordor.mordorLloguer.vistas.VistaPrincipal;
import com.mordor.mordorLloguer.vistas.VistaTablaEmpleados;

public class ControladorPincipal implements ActionListener {

	private VistaPrincipal vista;
	private VistaLogin vistaLogin;
	private AlmacenDatosDB model;
	private VistaTablaEmpleados tablaEmpleados;
	private static JDesktopPane desktopPane;
	private SwingWorker<Boolean, Void> task;
	private VistaPreferencias vistaPreferencias;

	public ControladorPincipal(VistaPrincipal vista, AlmacenDatosDB model) {
		super();
		this.vista = vista;
		this.model = model;
		this.desktopPane = vista.getDesktopPane();

		inicializar();
	}
	
	private void inicializar() {

		vista.getBtnLogout().setEnabled(false);
		vista.getBtnEmployee().setEnabled(false);
		vista.getBtnCustomer().setEnabled(false);
		vista.getBtnVehiculos().setEnabled(false);
		vista.getBtnFactura().setEnabled(false);

		// Añadimos los ActionListener
		vista.getBtnLogout().addActionListener(this);
		vista.getBtnLogin().addActionListener(this);
		vista.getBtnEmployee().addActionListener(this);
		vista.getMntmPreferencias().addActionListener(this);
		vista.getBtnCustomer().addActionListener(this);
		vista.getBtnVehiculos().addActionListener(this);
		vista.getBtnFactura().addActionListener(this);

		// Añadimos los ActionCommand
		vista.getBtnLogout().setActionCommand("Logout");
		vista.getBtnLogin().setActionCommand("Login");
		vista.getBtnEmployee().setActionCommand("Employee");
		vista.getMntmPreferencias().setActionCommand("Preferencias");
		vista.getBtnCustomer().setActionCommand("Customer");
		vista.getBtnVehiculos().setActionCommand("Vehiculos");
		vista.getBtnFactura().setActionCommand("Factura");

	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("Login")) {
			iniciarLogin();
		} else if (comando.equals("Logout")) {
			logout();
		} else if (comando.equals("Employee")) {
			tablaEmployee();
		} else if (comando.equals("IniciarSesion")) {
			iniciarSesion();
		} else if (comando.equals("Preferencias")) {
			preferencias();
		} else if (comando.equals("GuardarPreferencias")) {
			guardarPreferencias();
		}else if (comando.equals("Customer")) {
			tablaCustomer();
		}else if (comando.equals("Vehiculos")) {
			tablaVehiculos();
		}else if (comando.equals("Factura")) {
			facturas();
		} 

	}
	//Creamos la vista facturas
	private void facturas() {
		
	ControladorViFacturas facturas = new ControladorViFacturas(model);
		
	}
	//Cerramos sesion
	private void logout() {
		int option = JOptionPane.showConfirmDialog(null, "Desea cerrar sesión", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if(option == JOptionPane.OK_OPTION) {
			vista.getBtnLogout().setEnabled(false);
			vista.getBtnEmployee().setEnabled(false);
			vista.getBtnCustomer().setEnabled(false);
			vista.getBtnLogin().setEnabled(true);
			vista.getBtnVehiculos().setEnabled(false);
			vista.getBtnFactura().setEnabled(false);
		}
	}
	//Creamos la vista de la tabla vehiculos
	private void tablaVehiculos() {
		ControladorViVehiculos control = new ControladorViVehiculos(model);
		
	}
	//Creamos la vista de la tabla Clientes
	private void tablaCustomer() {
		ControladorViClientes control = new ControladorViClientes(model);
		
	}
	//Creamos la vista de la tabla Empleados
	private void tablaEmployee() {

		ControladorViEmpleados control = new ControladorViEmpleados(model);
		//center(tablaEmpleados);
		
	}
	//Metodo para guardar los datos de las preferencias en el fichero
	private void guardarPreferencias() {
		MyConfig.getInstancia().setDriver(vistaPreferencias.getTextDriver().getText());
		MyConfig.getInstancia().setURL(vistaPreferencias.getTxtURL().getText());
		MyConfig.getInstancia().setUsername(vistaPreferencias.getTxtUser().getText());
		char[] pas = vistaPreferencias.getPasswordField().getPassword();
		String passd = String.valueOf(pas);
		MyConfig.getInstancia().setPassword(passd);
		
	}
	//Creamos la vista de preferencias
	private void preferencias() {
		vistaPreferencias = new VistaPreferencias();
		vistaPreferencias.getBtnGuardar().addActionListener(this);
		vistaPreferencias.getBtnGuardar().setActionCommand("GuardarPreferencias");
		insertDestokPane(vistaPreferencias);
		center(vistaPreferencias);
		vistaPreferencias.getTextDriver().setText(MyConfig.getInstancia().getDriver());
		vistaPreferencias.getTxtURL().setText(MyConfig.getInstancia().getURL());
		vistaPreferencias.getTxtUser().setText(MyConfig.getInstancia().getUsername());
		vistaPreferencias.getPasswordField().setText(MyConfig.getInstancia().getPassword());
	}
	//En este metodo comporbamos que no el usuario y la contraseña estan en la base de datos
	private void iniciarSesion() {
		task = new SwingWorker<Boolean, Void>() {
			
			@Override
			protected Boolean doInBackground() throws Exception {
				boolean respuesta=false;
				try {
					vistaLogin.getProgressBar().setVisible(true);
					
					char[] pas = vistaLogin.getPasswordFieldUser().getPassword();
					System.out.println();
					String passd = String.valueOf(pas);
					for (int i = 0; i < 100 && !isCancelled(); i++) {
						vistaLogin.getProgressBar().setValue(i + 1);
					}
					respuesta = model.athenticate(vistaLogin.getTxtUsuario().getText(), passd);
					return respuesta;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vista, "No existe el usuario o contraseña", "Cuidado",
							JOptionPane.INFORMATION_MESSAGE);
				}
				return respuesta;
			}

			@Override
			protected void done() {
				vistaLogin.getProgressBar().setVisible(false);
				try {
					boolean validado = get();
					if (validado == true) {
						vista.getBtnLogout().setEnabled(true);
						vista.getBtnEmployee().setEnabled(true);
						vista.getBtnCustomer().setEnabled(true);
						vista.getBtnLogin().setEnabled(false);
						vista.getBtnVehiculos().setEnabled(true);
						vista.getBtnFactura().setEnabled(true);
						vistaLogin.setVisible(false);
						JOptionPane.showMessageDialog(vista, "Bienvenido", "Sesion Iniciada",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		task.execute();
	}

	// Insertemos cualquier vista en el desktopPane
	static void insertDestokPane(JInternalFrame jif) {
		
		
		if (isOpen(jif) == true) {
			jif.setVisible(true);
		} else {
			desktopPane.add(jif);
			jif.setVisible(true);
		}

	}

	// Comprobamos que en el desktopPane este ya la vista pero sin estar visible
	static boolean isOpen(JInternalFrame jif) {
		boolean elec = false;

		JInternalFrame[] frames = desktopPane.getAllFrames();
		for (JInternalFrame frame : frames) {
			if (frame == jif) {
				elec = true;
			}
		}

		return elec;
	}
	//Creamos la vista de iniciar sesión
	private void iniciarLogin() {

		vistaLogin = new VistaLogin();

		vistaLogin.getBtnLogin().addActionListener(this);
		vistaLogin.getBtnLogin().setActionCommand("IniciarSesion");
		insertDestokPane(vistaLogin);
		center(vistaLogin);

	}
	//Centramos los internalFrame en el desktopPane
	static void center(JInternalFrame jif) {
		Dimension dskSize = desktopPane.getSize();
		Dimension ifSize = jif.getSize();
		jif.setLocation((dskSize.width - ifSize.width) / 2, (dskSize.height - ifSize.height) / 2);

	}
}
