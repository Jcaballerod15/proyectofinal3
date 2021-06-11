package com.mordor.mordorLloguer.controlador;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.mordor.mordorLloguer.vistas.VistaCargarBD;
import com.mordor.mordorLloguer.vistas.VistaCrearEmpleado;
import com.mordor.mordorLloguer.vistas.VistaTablaEmpleados;
import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyOracleDataBase;
import com.mordor.mordorLloguer.model.MyTableModel;

public class ControladorViEmpleados implements TableModelListener, ActionListener {
	private VistaTablaEmpleados vistaTablaEmpleados;
	private AlmacenDatosDB model;
	private MyTableModelEmpleados mtm;
	private VistaCrearEmpleado vistaCrearEmpleado;
	private static ControladorViEmpleados controlador;
	private ArrayList<Empleado> empleados;
	private String[] HEADER = { "DNI", "Nombre", "Apellidos", "CP", "Email", "Nacimiento", "Cargo","Domicilio" };
	private VistaCargarBD carga;

	public ControladorViEmpleados(AlmacenDatosDB model) {

		this.vistaTablaEmpleados = new VistaTablaEmpleados();
		this.model = model;
		controlador = this;
		inicializar();

	}

	private void inicializar() {
		vistaTablaEmpleados.getCboxOrdenDatos().addItem("DNI");
		vistaTablaEmpleados.getCboxOrdenDatos().addItem("Nombre");
		vistaTablaEmpleados.getCboxOrdenDatos().addItem("Apellido");
		vistaTablaEmpleados.getCboxOrdenDatos().addItem("Cargo");
		
		vistaTablaEmpleados.getCboxAscenDescen().addItem("Ascendente");
		vistaTablaEmpleados.getCboxAscenDescen().addItem("Descendente");
		
		vistaTablaEmpleados.getBtnBorrar().addActionListener(this);
		vistaTablaEmpleados.getBtnCrear().addActionListener(this);
		vistaTablaEmpleados.getMntmAddRow_1().addActionListener(this);
		vistaTablaEmpleados.getMntmDeleteRow_1().addActionListener(this);
		vistaTablaEmpleados.getCboxOrdenDatos().addActionListener(this);
		vistaTablaEmpleados.getCboxAscenDescen().addActionListener(this);

		vistaTablaEmpleados.getBtnBorrar().setActionCommand("BorrarEmpleado");
		vistaTablaEmpleados.getBtnCrear().setActionCommand("CrearEmpleado");
		vistaTablaEmpleados.getMntmAddRow_1().setActionCommand("CrearEmpleadoPopUp");
		vistaTablaEmpleados.getMntmDeleteRow_1().setActionCommand("BorrarEmpleadoPopUp");
		vistaTablaEmpleados.getCboxOrdenDatos().setActionCommand("OrdenarDatos");
		vistaTablaEmpleados.getCboxAscenDescen().setActionCommand("OrdenarAscenDescen");

		ControladorPincipal.insertDestokPane(vistaTablaEmpleados);
		ControladorPincipal.center(vistaTablaEmpleados);
		creartabla();

		vistaTablaEmpleados.getTableEmpleados().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {

					int row = vistaTablaEmpleados.getTableEmpleados().rowAtPoint(e.getPoint());

					if (row < 0 || row >= vistaTablaEmpleados.getTableEmpleados().getRowCount())
						vistaTablaEmpleados.getTableEmpleados().clearSelection();
					else if (vistaTablaEmpleados.getTableEmpleados().getSelectedRowCount() <= 1) {
						vistaTablaEmpleados.getTableEmpleados().setSelectedRow(row);
						vistaTablaEmpleados.getPopupMenu().show(vistaTablaEmpleados.getTableEmpleados(), e.getX(),
								e.getY());
					} else if (vistaTablaEmpleados.getTableEmpleados().getSelectedRowCount() > 1) {
						vistaTablaEmpleados.getPopupMenu().show(vistaTablaEmpleados.getTableEmpleados(), e.getX(),
								e.getY());
					}

				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("BorrarEmpleado")) {
			borrarEmpleado();
		} else if (comando.equals("CrearEmpleado")) {
			crearEmpleado();
		} else if (comando.equals("CrearNewEmpleado")) {
			newEmpleado();
		} else if (comando.equals("CancelarCreacionEmpleado")) {
			cancelarNewEmpleado();
		} else if (comando.equals("BorrarEmpleadoPopUp")) {
			borrarEmpleado();
		} else if (comando.equals("CrearEmpleadoPopUp")) {
			crearEmpleado();
		}else if (comando.equals("OrdenarDatos")) {
			OrdenarPorDatos();
		}else if (comando.equals("OrdenarAscenDescen")) {
			OrdenarPorAscenDescen();
		}

	}
	//Metodo para ordenar los datos de la tabla de forma ascendente o descendente
	private void OrdenarPorAscenDescen() {
		String option = (String)vistaTablaEmpleados.getCboxOrdenDatos().getSelectedItem();
		String option2 = (String)vistaTablaEmpleados.getCboxAscenDescen().getSelectedItem();
		if(option2.compareTo("Ascendente")==0) {
			if(option.compareTo("DNI")==0) {
				empleados.sort(Empleado.COMPARE_BY_DNI);
			}else if(option.compareTo("Nombre")==0) {
				empleados.sort(Empleado.COMPARE_BY_NOMBRE);
			}else if(option.compareTo("Apellido")==0) {
				empleados.sort(Empleado.COMPARE_BY_APELLIDO);
			}else if(option.compareTo("Cargo")==0) {
				empleados.sort(Empleado.COMPARE_BY_CARGO);
			}
		}else if(option2.compareTo("Descendente")==0){
			if(option.compareTo("DNI")==0) {
				empleados.sort(Empleado.COMPARE_BY_DNI_DESCEN);
			}else if(option.compareTo("Nombre")==0) {
				empleados.sort(Empleado.COMPARE_BY_NOMBRE_DESCEN);
			}else if(option.compareTo("Apellido")==0) {
				empleados.sort(Empleado.COMPARE_BY_APELLIDO_DESCEN);
			}else if(option.compareTo("Cargo")==0) {
				empleados.sort(Empleado.COMPARE_BY_CARGO_DESCEN);
			}
		}
		mtm = new MyTableModelEmpleados(empleados, HEADER);
		vistaTablaEmpleados.getTableEmpleados().setModel(mtm);
		vistaTablaEmpleados.getTableEmpleados().setDefaultEditor(Date.class, new WebDateEditor());
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("mecanico");
		comboBox.addItem("administrativo");
		comboBox.addItem("comercial");
		comboBox.addItem("gerente");

		TableColumn column = vistaTablaEmpleados.getTableEmpleados().getColumn("Cargo");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		mtm.addTableModelListener(ControladorViEmpleados.controlador);		
	}
	//Metodo para aplicar filtros a la tabla de empleados
	private void OrdenarPorDatos() {
		String option = (String)vistaTablaEmpleados.getCboxOrdenDatos().getSelectedItem();
		String option2 = (String)vistaTablaEmpleados.getCboxAscenDescen().getSelectedItem();
		if(option2.compareTo("Ascendente")==0) {
			if(option.compareTo("DNI")==0) {
				empleados.sort(Empleado.COMPARE_BY_DNI);
			}else if(option.compareTo("Nombre")==0) {
				empleados.sort(Empleado.COMPARE_BY_NOMBRE);
			}else if(option.compareTo("Apellido")==0) {
				empleados.sort(Empleado.COMPARE_BY_APELLIDO);
			}else if(option.compareTo("Cargo")==0) {
				empleados.sort(Empleado.COMPARE_BY_CARGO);
			}
		}else if(option2.compareTo("Descendente")==0){
			if(option.compareTo("DNI")==0) {
				empleados.sort(Empleado.COMPARE_BY_DNI_DESCEN);
			}else if(option.compareTo("Nombre")==0) {
				empleados.sort(Empleado.COMPARE_BY_NOMBRE_DESCEN);
			}else if(option.compareTo("Apellido")==0) {
				empleados.sort(Empleado.COMPARE_BY_APELLIDO_DESCEN);
			}else if(option.compareTo("Cargo")==0) {
				empleados.sort(Empleado.COMPARE_BY_CARGO_DESCEN);
			}
		}
		mtm = new MyTableModelEmpleados(empleados, HEADER);
		vistaTablaEmpleados.getTableEmpleados().setModel(mtm);
		vistaTablaEmpleados.getTableEmpleados().setDefaultEditor(Date.class, new WebDateEditor());
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("mecanico");
		comboBox.addItem("administrativo");
		comboBox.addItem("comercial");
		comboBox.addItem("gerente");

		TableColumn column = vistaTablaEmpleados.getTableEmpleados().getColumn("Cargo");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		mtm.addTableModelListener(ControladorViEmpleados.controlador);
		
	}
	//Metodo para cancelar la vista de crear empleado
	private void cancelarNewEmpleado() {
		vistaCrearEmpleado.setVisible(false);
	}
	//Insetamos un nuevo empleado en la BD
	private void newEmpleado() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				char[] pas = vistaCrearEmpleado.getPasswordField().getPassword();
				String passd = String.valueOf(pas);
				Empleado employee = new Empleado(vistaCrearEmpleado.getTxtDNI().getText(),
						vistaCrearEmpleado.getTxtNombre().getText(), vistaCrearEmpleado.getTxtApellidos().getText(),
						vistaCrearEmpleado.getTxtCp().getText(), vistaCrearEmpleado.getTxtEmail().getText(),
						new java.sql.Date(vistaCrearEmpleado.getTxtDate().getDate().getTime()),
						(String) vistaCrearEmpleado.getComboBoxCargo().getSelectedItem(),
						vistaCrearEmpleado.getTxtDomicilio().getText(), passd);
				try {
					if(model.getEmpleadoPorDNI(employee.getDNI())==null){
						if (model.crearEmpleado(employee) == true) {
							mtm.add(employee);
							empleados.add(employee);
							return true;
						}
					}else {
						JOptionPane.showMessageDialog(vistaTablaEmpleados, "El usuario que desea crear ya existe",
								"Cuidado", JOptionPane.INFORMATION_MESSAGE);
						return false;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vistaTablaEmpleados, "No se puede acceder a la base de datos",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get() == true) {
						JOptionPane.showMessageDialog(vistaTablaEmpleados, "El Usuario se insertó correctamente",
								"Creado correctamente", JOptionPane.INFORMATION_MESSAGE);
						vistaCrearEmpleado.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(vistaTablaEmpleados, "El Usuario no se pudo crear",
								"No se pudo crear", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (HeadlessException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		carga = new VistaCargarBD(task,"Creando Empleado");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//Creamos la vista para crear empleados
	private void crearEmpleado() {

		vistaCrearEmpleado = new VistaCrearEmpleado();
		vistaCrearEmpleado.getComboBoxCargo().addItem("mecanico");
		vistaCrearEmpleado.getComboBoxCargo().addItem("administrativo");
		vistaCrearEmpleado.getComboBoxCargo().addItem("comercial");
		vistaCrearEmpleado.getComboBoxCargo().addItem("gerente");
		ControladorPincipal.insertDestokPane(vistaCrearEmpleado);
		ControladorPincipal.center(vistaCrearEmpleado);

		vistaCrearEmpleado.getBtnAdd().addActionListener(this);
		vistaCrearEmpleado.getBtnCancel().addActionListener(this);

		vistaCrearEmpleado.getBtnAdd().setActionCommand("CrearNewEmpleado");
		vistaCrearEmpleado.getBtnCancel().setActionCommand("CancelarCreacionEmpleado");
	}
	//Borramos empleados de la BD
	private void borrarEmpleado() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				int[] empleadoss = vistaTablaEmpleados.getTableEmpleados().getSelectedRows();
				ArrayList<Empleado> employees = new ArrayList<Empleado>();
				for (int i = 0; i < empleadoss.length; i++) {
					employees.add(mtm.get(empleadoss[i]));
				}
				try {
					for (int j = 0; j < employees.size(); j++) {
						if (model.deleteEmpleado(employees.get(j).getDNI()) == true) {
							mtm.remove(employees.get(j));
							empleados.remove(employees.get(j));
							return true;
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vistaTablaEmpleados, "No se puede acceder a la base de datos",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get() == true) {

						JOptionPane.showMessageDialog(vistaTablaEmpleados, "El Usuario se eliminó correctamente",
								"Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(vistaTablaEmpleados, "El Usuario, no existe",
								"No se puede eliminar", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		carga = new VistaCargarBD(task,"Borrando Empleados");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//Rcibimos los datos de la BD sobre empleados y creamos la tabla de la vista de empleados
	private void creartabla() {

		SwingWorker task = new SwingWorker<List<Empleado>, Void>() {
			@Override
			protected List<Empleado> doInBackground() throws Exception {
				empleados = new ArrayList<Empleado>();
				try {
					Thread.sleep(2000);
					empleados = model.getEmpleados();
					return empleados;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vistaTablaEmpleados, "No se puede acceder a la base de datos",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
				return empleados;

			}

			@Override
			protected void done() {
				carga.setVisible(false);
				try {
					ArrayList<Empleado> empleados = (ArrayList<Empleado>) get();
					mtm = new MyTableModelEmpleados(empleados, HEADER);
					vistaTablaEmpleados.getTableEmpleados().setModel(mtm);
					// mtm.addTableModelListener(this);
					vistaTablaEmpleados.getTableEmpleados().setDefaultEditor(Date.class, new WebDateEditor());
					// Adding comboBox just to edit the company position in the WebTable
					JComboBox<String> comboBox = new JComboBox<String>();
					comboBox.addItem("mecanico");
					comboBox.addItem("administrativo");
					comboBox.addItem("comercial");
					comboBox.addItem("gerente");

					TableColumn column = vistaTablaEmpleados.getTableEmpleados().getColumn("Cargo");
					column.setCellEditor(new DefaultCellEditor(comboBox));
					mtm.addTableModelListener(ControladorViEmpleados.controlador);

				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		carga = new VistaCargarBD(task,"Cargando la tabla de Empleados");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();

	}
	//Clase para crear un modelo para la tabla de empleados
	private class MyTableModelEmpleados extends MyTableModel<Empleado> {

		public MyTableModelEmpleados(List<Empleado> data, String[] head) {
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
			case 5:
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
				data.get(row).setCP(aValue.toString());
				break;
			case 4:
				data.get(row).setEmail(aValue.toString());
				break;
			case 5:
				java.util.Date fecha = (java.util.Date) aValue;
				data.get(row).setFechaNac(new java.sql.Date(fecha.getTime()));
				break;
			case 6:
				data.get(row).setCargo(aValue.toString());
				break;
			case 7:
				data.get(row).setDomicilio(aValue.toString());
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
				return data.get(row).getCP();
			case 4:
				return data.get(row).getEmail();
			case 5:
				return data.get(row).getFechaNac();
			case 6:
				return data.get(row).getCargo();
			case 7:
				return data.get(row).getDomicilio();
			}
			return null;
		}
	}

	// @Override
	public void tableChanged(TableModelEvent e) {

		if (e.getType() == TableModelEvent.UPDATE) {
			System.out.println("Se ha actualizado al tabla " + e.getFirstRow());
			Empleado employee = mtm.get(e.getFirstRow());
			System.out.println(employee);
			model.updateEmpleado(employee);
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public VistaTablaEmpleados getTablaEmpleados() {
		return vistaTablaEmpleados;
	}

	public void setTablaEmpleados(VistaTablaEmpleados tablaEmpleados) {
		this.vistaTablaEmpleados = tablaEmpleados;
	}

}
