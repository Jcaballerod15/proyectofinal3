package com.mordor.mordorLloguer.controlador;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Customer;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.vistas.VistaCargarBD;
import com.mordor.mordorLloguer.vistas.VistaCrearCliente;
import com.mordor.mordorLloguer.vistas.VistaTablaClientes;

public class ControladorViClientes implements ActionListener, TableModelListener, MouseListener {

	private VistaTablaClientes vistaTablaClientes;
	private AlmacenDatosDB model;
	private ArrayList<Customer> clientes;
	private VistaCrearCliente vistaCrearCliente;
	private String[] HEADER = { "DNI", "Nombre", "Apellidos", "Domicilio", "CP", "Email", "Nacimiento", "Carnet" };
	private MyTableModelClientes mtm;
	private static ControladorViClientes controlador;
	private byte[] imagen;
	private VistaCargarBD carga;

	public ControladorViClientes(AlmacenDatosDB model) {

		this.vistaTablaClientes = new VistaTablaClientes();
		this.model = model;
		controlador = this;
		this.imagen = null;
		inicializar();

	}

	private void inicializar() {

		vistaTablaClientes.getComboBoxCarnet().addItem("All");
		vistaTablaClientes.getComboBoxCarnet().addItem("A");
		vistaTablaClientes.getComboBoxCarnet().addItem("B");
		vistaTablaClientes.getComboBoxCarnet().addItem("C");
		vistaTablaClientes.getComboBoxCarnet().addItem("D");
		vistaTablaClientes.getComboBoxCarnet().addItem("E");
		vistaTablaClientes.getComboBoxCarnet().addItem("Z");
		
		vistaTablaClientes.getBtnBorrar().addActionListener(this);
		vistaTablaClientes.getBtnCrear().addActionListener(this);
		vistaTablaClientes.getBtnEdit().addActionListener(this);
		vistaTablaClientes.getBtnPrint().addActionListener(this);
		vistaTablaClientes.getTxtDNI().addActionListener(this);
		vistaTablaClientes.getTxtName().addActionListener(this);
		vistaTablaClientes.getTxtSurname().addActionListener(this);
		vistaTablaClientes.getComboBoxCarnet().addActionListener(this);

		vistaTablaClientes.getBtnBorrar().setActionCommand("BorrarCliente");
		vistaTablaClientes.getBtnCrear().setActionCommand("CrearCLiente");
		vistaTablaClientes.getBtnEdit().setActionCommand("EditarCliente");
		vistaTablaClientes.getBtnPrint().setActionCommand("Print");
		vistaTablaClientes.getTxtDNI().setActionCommand("filtrarDNI");
		vistaTablaClientes.getTxtName().setActionCommand("filtrarName");
		vistaTablaClientes.getTxtSurname().setActionCommand("filtrarSurname");
		vistaTablaClientes.getComboBoxCarnet().setActionCommand("filtrarCarnet");		

		ControladorPincipal.insertDestokPane(vistaTablaClientes);
		ControladorPincipal.center(vistaTablaClientes);

		crearTabla();
	}
	//Creamos la vista que contine los clientes con los datos que recibimos de la BD
	private void crearTabla() {
		SwingWorker task = new SwingWorker<List<Customer>, Void>() {
			@Override
			protected List<Customer> doInBackground() throws Exception {
				clientes = new ArrayList<Customer>();
				try{
				clientes = model.getCustomers();
				//System.out.println(clientes);
				return clientes;
				}catch(Exception es) {
					JOptionPane.showMessageDialog(vistaTablaClientes, "No se puede acceder a la base de datos",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
				return clientes;
			}

			protected void done() {
				carga.setVisible(false);
				try {
				mtm = new MyTableModelClientes(clientes, HEADER);
				vistaTablaClientes.getTableEmpleados().setModel(mtm);
				vistaTablaClientes.getTableEmpleados().setDefaultEditor(Date.class, new WebDateEditor());
				// Adding comboBox just to edit the company position in the WebTable
				JComboBox<String> comboBox = new JComboBox<String>();
				comboBox.addItem("A");
				comboBox.addItem("B");
				comboBox.addItem("C");
				comboBox.addItem("D");
				comboBox.addItem("E");
				comboBox.addItem("Z");

				TableColumn column = vistaTablaClientes.getTableEmpleados().getColumn("Carnet");
				column.setCellEditor(new DefaultCellEditor(comboBox));
				mtm.addTableModelListener(ControladorViClientes.controlador);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}

		};
		carga = new VistaCargarBD(task, "Cargando la tabla de Clientes");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("BorrarCliente")) {
			borrarCliente();
		} else if (comando.equals("CrearCLiente")) {
			newCliente();
		} else if (comando.equals("EditarCliente")) {
			editarCliente();
		} else if (comando.equals("Print")) {
			printCliente();
		} else if (comando.equals("DarAltaCliente")) {
			darAltaCliente();
		}else if (comando.equals("filtrarDNI")) {
			System.out.println("Hola");
			filtrar();
		}else if (comando.equals("filtrarName")) {
			filtrar();
		}else if (comando.equals("filtrarSurname")) {
			filtrar();
		}else if (comando.equals("filtrarCarnet")) {
			filtrar();
		}

	}
	//Añadimos a la BD un nuevo cliente
	private void darAltaCliente() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {
			protected Boolean doInBackground() {
				String dni = vistaCrearCliente.getTxtDni().getText();
				String nombre = vistaCrearCliente.getTxtName().getText();
				String apellido = vistaCrearCliente.getTxtSruname().getText();
				String cp = vistaCrearCliente.getTxtCp().getText();
				String direccion = vistaCrearCliente.getTxtAddress().getText();
				char carnet = vistaCrearCliente.getComboBoxLicencia().getSelectedItem().toString().charAt(0);
				String email = vistaCrearCliente.getTxtEmail().getText();
				byte[] imgfoto = vistaCrearCliente.getImage();

				try {
					Customer cliente = new Customer(dni, nombre, apellido, cp, email,
							new java.sql.Date(vistaCrearCliente.getTxtDate().getDate().getTime()), carnet, direccion,
							imgfoto);

					if (model.addCustomer(cliente)) {
						// System.out.println(clientes.contains(cliente));	
						if(clientes.contains(cliente)) {
							clientes.remove(cliente);
						}
							clientes.add(cliente);
							mtm.add(cliente);
							return true;
					} else {
						JOptionPane.showMessageDialog(vistaCrearCliente, "No se pudo crear el cliente", "Error",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(vistaCrearCliente, "Recuerde completar todos los campos", "Cuidado",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(vistaCrearCliente, "Recuerde completar todos los campos", "Cuidado",
							JOptionPane.INFORMATION_MESSAGE);
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get() == true) {
						JOptionPane.showMessageDialog(vistaCrearCliente, "El Cliente se insertó correctamente",
								"Creado correctamente", JOptionPane.INFORMATION_MESSAGE);
						vistaCrearCliente.setVisible(false);
						vistaCrearCliente.setImage(null);
					}
				} catch (HeadlessException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		carga = new VistaCargarBD(task, "Creando Cliente");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//Creamos los filtros para la vista de la tabla clientes
	private void filtrar() {
		List clientesFiltrados = clientes.stream().filter( c -> c.getDNI().contains(vistaTablaClientes.getTxtDNI().getText()))
				.filter( c -> c.getNombre().contains(vistaTablaClientes.getTxtName().getText()))
				.filter( c -> c.getApellidos().contains(vistaTablaClientes.getTxtSurname().getText()))
				.filter( c -> Character.toString(c.getCarnet()).contains(vistaTablaClientes.getComboBoxCarnet().getSelectedItem().toString()) || vistaTablaClientes.getComboBoxCarnet().getSelectedItem().toString().equals("All"))
				.collect(Collectors.toList());
		
		mtm = new MyTableModelClientes(clientesFiltrados, HEADER);
		vistaTablaClientes.getTableEmpleados().setModel(mtm);
		vistaTablaClientes.getTableEmpleados().setDefaultEditor(Date.class, new WebDateEditor());
		// Adding comboBox just to edit the company position in the WebTable
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("A");
		comboBox.addItem("B");
		comboBox.addItem("C");
		comboBox.addItem("D");
		comboBox.addItem("E");
		comboBox.addItem("Z");

		TableColumn column = vistaTablaClientes.getTableEmpleados().getColumn("Carnet");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		mtm.addTableModelListener(ControladorViClientes.controlador);
		
	}
	//Cargamos la imagen del directorio en nuestro equipo
	private void cargarImagen() {
		JFileChooser jfch = new JFileChooser();
		// jfc.setFileFilter(new FileNameExtensionFilter("Formulario file", "app",
		// "miapp"));
		int opcion = jfch.showOpenDialog(vistaCrearCliente);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			try {
				InputStream inte = new FileInputStream(jfch.getSelectedFile()); // Creación del flujo
				byte[] imgFoto = new byte[(int) jfch.getSelectedFile().length()]; // Creación del vector
				inte.read(imgFoto);
				this.imagen = imgFoto;

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		vistaCrearCliente.setImage(imagen);

	}
	//Borramos clientes de la BD
	private void borrarCliente() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				int[] cliente = vistaTablaClientes.getTableEmpleados().getSelectedRows();
				ArrayList<Customer> customer = new ArrayList<Customer>();
				for (int i = 0; i < cliente.length; i++) {
					customer.add(mtm.get(cliente[i]));
				}
				try {
					for (int j = 0; j < customer.size(); j++) {
						if (model.deleteCliente(customer.get(j).getDNI())) {
							mtm.remove(customer.get(j));
							clientes.remove(customer.get(j));
							return true;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(vistaTablaClientes, "No se pudo eliminar el cliente", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get() == true) {
						JOptionPane.showMessageDialog(vistaTablaClientes, "El Cliente se eliminó correctamente",
								"Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(vistaTablaClientes, "El Cliente, no existe",
								"No se puede eliminar", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		carga = new VistaCargarBD(task, "Borrando Clientes");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//Creamos la vista para crear clientes
	private void newCliente() {

		vistaCrearCliente = new VistaCrearCliente();
		ControladorPincipal.insertDestokPane(vistaCrearCliente);
		ControladorPincipal.center(vistaCrearCliente);

		vistaCrearCliente.getBtnAdd().addActionListener(this);
		vistaCrearCliente.getBtnAdd().setActionCommand("DarAltaCliente");
		vistaCrearCliente.getLblImagen().addMouseListener(this);
		vistaCrearCliente.setImage(imagen);
	}
	//Creamos la vista para editar los datos de un cliente
	private void editarCliente() {
		try {
			int cliente = vistaTablaClientes.getTableEmpleados().getSelectedRow();
			Customer c = mtm.get(cliente);
			if (c != null) {
				vistaCrearCliente = new VistaCrearCliente();
				ControladorPincipal.insertDestokPane(vistaCrearCliente);
				ControladorPincipal.center(vistaCrearCliente);

				vistaCrearCliente.getBtnAdd().addActionListener(this);
				vistaCrearCliente.getBtnAdd().setActionCommand("DarAltaCliente");
				vistaCrearCliente.getLblImagen().addMouseListener(this);
				vistaCrearCliente.setImage(imagen);

				vistaCrearCliente.getTxtDni().setText(c.getDNI());
				vistaCrearCliente.getTxtName().setText(c.getNombre());
				vistaCrearCliente.getTxtSruname().setText(c.getApellidos());
				vistaCrearCliente.getTxtDate().setDate(c.getFechaNac());
				vistaCrearCliente.getTxtAddress().setText(c.getDomicilio());
				vistaCrearCliente.getTxtCp().setText(c.getCP());
				vistaCrearCliente.getComboBoxLicencia().setSelectedItem(Character.toString(c.getCarnet()));
				vistaCrearCliente.getTxtEmail().setText(c.getEmail());
				vistaCrearCliente.setImage(c.getImgfoto());

			} else {
				newCliente();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vistaTablaClientes, "Selecciona un cliente de la tabla", "Cuidado",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}
	//Metodo que se supone que es para poder imprimir los datos de un cliente
	private void printCliente() {

	}
	//Clase para crear un modelo para la tabla de la vista clientes
	private class MyTableModelClientes extends MyTableModel<Customer> {

		public MyTableModelClientes(List<Customer> data, String[] head) {
			super(data, head);

		}

		@Override
		public boolean isCellEditable(int row, int col) {

			if (col == 0) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 6:
				return Date.class;
			default:
				return String.class;
			}
		}

		@Override
		public void setValueAt(Object aValue, int row, int col) {
			switch (col) {

			case 1:
				data.get(row).setNombre(aValue.toString());
				break;
			case 2:
				data.get(row).setApellidos(aValue.toString());
				break;
			case 3:
				data.get(row).setDomicilio(aValue.toString());
				break;

			case 4:
				data.get(row).setCP(aValue.toString());
				break;

			case 5:
				data.get(row).setEmail(aValue.toString());
				break;
			case 6:
				java.util.Date fecha = (java.util.Date) aValue;
				data.get(row).setFechaNac(new java.sql.Date(fecha.getTime()));
				break;
			case 7:
				data.get(row).setCarnet(aValue.toString().charAt(0));
				break;
			}

			fireTableCellUpdated(row, col);

		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0:
				return data.get(row).getDNI();
			case 1:
				return data.get(row).getNombre();
			case 2:
				return data.get(row).getApellidos();
			case 3:
				return data.get(row).getDomicilio();
			case 4:
				return data.get(row).getCP();
			case 5:
				return data.get(row).getEmail();
			case 6:
				return data.get(row).getFechaNac();
			case 7:
				return data.get(row).getCarnet();
			}
			return null;
		}
	}

	// @Override
	public void tableChanged(TableModelEvent e) {

		if (e.getType() == TableModelEvent.UPDATE) {
			System.out.println("Se ha actualizado al tabla " + e.getFirstRow());
			Customer cliente = mtm.get(e.getFirstRow());
			System.out.println(cliente);
			try {
				model.addCustomer(cliente);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// if(e.getSource()==vistaCrearCliente.getLblImagen())
		cargarImagen();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
