package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Alquiler;
import com.mordor.mordorLloguer.model.Customer;
import com.mordor.mordorLloguer.model.Factura;
import com.mordor.mordorLloguer.model.MyFacturaTableModel;
import com.mordor.mordorLloguer.model.Vehiculo;
import com.mordor.mordorLloguer.vistas.JIFInvoice;
import com.mordor.mordorLloguer.vistas.VistaCargarBD;
import com.mordor.mordorLloguer.vistas.VistaFacturaNew;

public class ControladorViFacturas implements ActionListener, TableModelListener {
	private AlmacenDatosDB model;
	private JIFInvoice vistaFactura;
	private static ControladorViFacturas controlador;
	private ArrayList<Factura> facturas;
	private ArrayList<Alquiler> alquileres;
	private ArrayList<Customer> clientes;
	private ArrayList<Vehiculo> vehiculos;
	private VistaCargarBD carga;
	private int facturaActual;
	private String[] header = { "Matricula", "Modelo", "Precio", "F.Inicio", "F.Fin" };
	private MyFacturaTableModel mftm;
	private VistaFacturaNew vistaFacturanew;
	private VistaFacturaNew vistaAlquilernew;

	public ControladorViFacturas(AlmacenDatosDB model2) {
		super();
		this.model = model2;
		facturaActual = 0;
		try {
			this.vistaFactura = new JIFInvoice();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controlador = this;
		inicializar();
	}

	private void inicializar() {

		ControladorPincipal.insertDestokPane(vistaFactura);
		ControladorPincipal.center(vistaFactura);
		//Creamos los ActionListener
		vistaFactura.getBtnPreviousInvoice().addActionListener(this);
		vistaFactura.getBtnNextInvoice().addActionListener(this);
		vistaFactura.getBtnNewInvoce().addActionListener(this);
		vistaFactura.getBtnRemoveInvoice().addActionListener(this);
		vistaFactura.getBtnAddDetail().addActionListener(this);
		vistaFactura.getBtnRemoveDetail().addActionListener(this);
		vistaFactura.getBtnCheck().addActionListener(this);
		//Creamos los ActionCommand
		vistaFactura.getBtnPreviousInvoice().setActionCommand("FacturaAnterior");
		vistaFactura.getBtnNextInvoice().setActionCommand("FacturaSiguiente");
		vistaFactura.getBtnNewInvoce().setActionCommand("NewFactura");
		vistaFactura.getBtnRemoveInvoice().setActionCommand("RemoveFactura");
		vistaFactura.getBtnAddDetail().setActionCommand("NewAlquiler");
		vistaFactura.getBtnRemoveDetail().setActionCommand("RemoveAlquiler");
		vistaFactura.getBtnCheck().setActionCommand("Check");

		crearDatosVista();

	}
	//En este meto recibimos los datos de facturas,alquiler,clientes y vehilculos
	public void crearDatosVista() {
		SwingWorker task = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				vehiculos = new ArrayList<Vehiculo>();
				try {
					facturas = model.getFactura();
					alquileres = model.getAlquiler();
					clientes = model.getCustomers();

					vehiculos.addAll(model.getCoche());
					vehiculos.addAll(model.getFurgonetas());
					vehiculos.addAll(model.getCamion());
					vehiculos.addAll(model.getMicrobus());

				} catch (Exception es) {
					JOptionPane.showMessageDialog(vistaFactura, "No se puede acceder a la base de datos", "Cuidado",
							JOptionPane.INFORMATION_MESSAGE);
					//es.printStackTrace();
				}
				return null;
			}

			protected void done() {
				carga.setVisible(false);
				recorrerfacturas();
			}
		};
		carga = new VistaCargarBD(task, "Cargando las tablas de Vehiculos");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	// En este metodo plasmamos los datos de facturas ,alquler,clientes y vehiculos en la vista de las facturas
	public void recorrerfacturas() {

		Factura fac = facturas.get(facturaActual);
		Customer cus = null;
		ArrayList<Alquiler> alqui = new ArrayList<Alquiler>();

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getIdCliente() == fac.getClienteid()) {
				cus = clientes.get(i);
			}
		}
		for (int j = 0; j < alquileres.size(); j++) {
			if (alquileres.get(j).getIdFactura() == fac.getIdFactura()) {
				alqui.add(alquileres.get(j));
			}
		}
		vistaFactura.getTxtFieldNombre().setText(cus.getNombre());
		vistaFactura.getTxtFieldApellidos().setText(cus.getApellidos());
		vistaFactura.getTxtFieldDNI().setText(cus.getDNI());
		vistaFactura.getTxtFieldNumeroFactura().setText(Integer.toString(fac.getIdFactura()));
		vistaFactura.getWebDateFieldFechaFactura().setDate(fac.getFecha());

		mftm = new MyFacturaTableModel(alqui, vehiculos, header);
		mftm.addTableModelListener(this);

		vistaFactura.getTableDetalles().setModel(mftm);
		vistaFactura.getTableDetalles().setDefaultEditor(Date.class, new WebDateEditor());
		vistaFactura.getTxtFieldSuma().setText(Float.toString(fac.getImportebase()));
		vistaFactura.getTxtFieldImpuestos().setText(Float.toString(fac.getImporteiva()));
		vistaFactura.getTxtFieldTotal().setText(Float.toString(fac.getImportebase() + fac.getImporteiva()));
		// vistaFactura.getTableDetalles().addTableModelListener(ControladorViFacturas.controlador);
	}
	// en este metodo recogemos los datos a la hora de hacer un cambio en la tabla
	@Override
	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE) {
			//System.out.println("hola");
			Alquiler al = mftm.get(e.getFirstRow());
			//System.out.println(al.getMatricula());
			try {
				model.updateAlquiler(al.getIdAlquiler(), al.getFechainicio(), al.getFechafin());
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(vistaFactura, e1.getMessage(), "Cuidado",
						JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("FacturaAnterior")) {
			facturaAnterior();
		} else if (comando.equals("FacturaSiguiente")) {
			facturaSiguiente();
		} else if (comando.equals("NewFactura")) {
			newFactura();
		} else if (comando.equals("crearFactura")) {
			crearFactura();
		} else if (comando.equals("NewAlquiler")) {
			newAlquiler();
		} else if (comando.equals("RemoveFactura")) {
			removeFactura();
		} else if (comando.equals("crearAlquiler")) {
			crearAlquiler();
		} else if (comando.equals("RemoveAlquiler")) {
			removeAlquiler();
		}else if (comando.equals("Check")) {
			check();
		}
		

	}
	//Metodo para comprobar si un alquiler ya se ha pasado de la fechafin 
	private void check() {
		SwingWorker task = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				try {
					model.facturaCobrada(Integer.parseInt(vistaFactura.getTxtFieldNumeroFactura().getText()));
				}catch(Exception r) {
					JOptionPane.showMessageDialog(vistaFactura, r.getMessage(), "Cuidado",
							JOptionPane.INFORMATION_MESSAGE);
				}
				return null;
			}
			protected void done() {
				carga.setVisible(false);
			}
		};
		carga = new VistaCargarBD(task, "comprobando fecha");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//Metodo para eleiminar un factura completamente
	private void removeFactura() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				try {
					for (int i = 0; i < mftm.getRowCount(); i++) {
						Alquiler al = mftm.get(i);
						model.deleteAlquiler(al.getIdAlquiler());
						// System.out.println(al.getIdAlquiler());
					}
						facturas = model.getFactura();
						alquileres = model.getAlquiler();
					return true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(vistaFactura, "No se pudo eliminar", "Cuidado",
							JOptionPane.ERROR_MESSAGE);
					
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get()) {
						JOptionPane.showMessageDialog(vistaFactura, "Se eliminó correctamente", "Correcto",
								JOptionPane.INFORMATION_MESSAGE);
						facturaActual = 0;
						recorrerfacturas();
					}
				} catch (InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog(vistaFactura, "No se pudo eliminar", "Cuidado",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				// recorrerfacturas();
			}
		};
		carga = new VistaCargarBD(task, "Eliminando factura");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();

	}
	//metodo para eliminar alquileres seleccionando una fila de la tabla
	private void removeAlquiler() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {
			
			Factura f = null;
			@Override
			protected Boolean doInBackground() throws Exception {
				Alquiler al = mftm.get(vistaFactura.getTableDetalles().getSelectedRow());
				// System.out.println(al.getIdAlquiler());
				facturas = model.getFactura();
				alquileres = model.getAlquiler();
				//final de alquileres solucionar
				for(int i =0;i<facturas.size();i++) {
					if(facturas.get(i).getIdFactura() == al.getIdFactura()) {
						f = facturas.get(i);
					}
				}
				return model.deleteAlquiler(al.getIdAlquiler());
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get()) {
						JOptionPane.showMessageDialog(vistaFactura, "Se eliminó correctamente", "Correcto",
								JOptionPane.INFORMATION_MESSAGE);
						if(f==null) {
							facturaActual = 0;
						}
						recorrerfacturas();
					}
				} catch (InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog(vistaFactura, "No se pudo eliminar", "Cuidado",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				// recorrerfacturas();
			}
		};
		carga = new VistaCargarBD(task, "Eliminando alquiler");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	// Creamos la vista para añadir alquiler
	private void newAlquiler() {
		vistaAlquilernew = new VistaFacturaNew();
		vistaAlquilernew.getLblDatosDeLa().setText("Datos del alquiler");
		vistaAlquilernew.getTxtDNI().setText(vistaFactura.getTxtFieldDNI().getText());
		vistaAlquilernew.getTxtDNI().setEditable(false);
		ControladorPincipal.insertDestokPane(vistaAlquilernew);
		ControladorPincipal.center(vistaAlquilernew);
		vistaAlquilernew.getBtnAdd().addActionListener(this);
		vistaAlquilernew.getBtnAdd().setActionCommand("crearAlquiler");

	}
	//Creamos la vista para añadir facturas
	private void newFactura() {

		vistaFacturanew = new VistaFacturaNew();
		ControladorPincipal.insertDestokPane(vistaFacturanew);
		ControladorPincipal.center(vistaFacturanew);
		vistaFacturanew.getBtnAdd().addActionListener(this);
		vistaFacturanew.getBtnAdd().setActionCommand("crearFactura");
	}
	//Metodo para crear la factura
	private void crearFactura() {

		SwingWorker task = new SwingWorker<ArrayList<Integer>, Void>() {
			@Override
			protected ArrayList<Integer> doInBackground() throws Exception {
				ArrayList<Integer> identi = new ArrayList<Integer>();
				try {
				String dni = vistaFacturanew.getTxtDNI().getText();
				String matricula = vistaFacturanew.getTxtMatricula().getText();
				Date fechainicio = new java.sql.Date(vistaFacturanew.getTxtFechainicio().getDate().getTime());
				Date fechafin = new java.sql.Date(vistaFacturanew.getTxtFechafin().getDate().getTime());
				//ArrayList<Integer> identi = new ArrayList<Integer>();
				identi = model.insertarFactura(dni, matricula, fechainicio, fechafin);
				facturas = model.getFactura();
				alquileres = model.getAlquiler();

				//return identi;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(vistaFactura, e.getMessage(), "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
				return identi;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get().size() > 0) {
						JOptionPane.showMessageDialog(vistaFactura, "Se creó correctamente", "Correcto",
								JOptionPane.INFORMATION_MESSAGE);
						vistaFacturanew.setVisible(false);
						for (int i = 0; i < facturas.size(); i++) {
							if (facturas.get(i).getIdFactura() == get().get(0)) {
								facturaActual = i;
								recorrerfacturas();
							}
						}
					}
				} catch (InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog(vistaFactura, "No se puede crear la factura", "Cuidado",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				// recorrerfacturas();
			}
		};
		carga = new VistaCargarBD(task, "Creando factura");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();

	}
	//Metodo para crear Alquiler
	private void crearAlquiler() {

		SwingWorker task = new SwingWorker<ArrayList<Integer>, Void>() {
			@Override
			protected ArrayList<Integer> doInBackground() throws Exception {
				ArrayList<Integer> identi = new ArrayList<Integer>();
				try {
				String dni = vistaAlquilernew.getTxtDNI().getText();
				String matricula = vistaAlquilernew.getTxtMatricula().getText();
				Date fechainicio = new java.sql.Date(vistaAlquilernew.getTxtFechainicio().getDate().getTime());
				Date fechafin = new java.sql.Date(vistaAlquilernew.getTxtFechafin().getDate().getTime());

				//ArrayList<Integer> identi = new ArrayList<Integer>();
				identi = model.insertarAlquiler(Integer.parseInt(vistaFactura.getTxtFieldNumeroFactura().getText()),
						dni, matricula, fechainicio, fechafin);
				facturas = model.getFactura();
				alquileres = model.getAlquiler();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(vistaFactura, e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				return identi;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get().size() > 0) {
						JOptionPane.showMessageDialog(vistaFactura, "Se creó correctamente", "Correcto",
								JOptionPane.INFORMATION_MESSAGE);
						vistaAlquilernew.setVisible(false);
						for (int i = 0; i < facturas.size(); i++) {
							if (facturas.get(i).getIdFactura() == get().get(0)) {
								facturaActual = i;
								recorrerfacturas();
							}
						}
					}
				} catch (InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog(vistaFactura, "No se puede crear el Alquiler", "Cuidado",
							JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
				// recorrerfacturas();
			}
		};
		carga = new VistaCargarBD(task, "Creando alquiler");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();

	}
	//Metodo para pasar a la factura siguiente
	private void facturaSiguiente() {
		if (facturaActual == facturas.size() - 1) {
			recorrerfacturas();
		} else {
			facturaActual = facturaActual + 1;
			recorrerfacturas();
		}
		// System.out.println(facturaActual);
	}
	//Metodo para pasar a la factura anterior
	private void facturaAnterior() {
		if (facturaActual == 0) {
			recorrerfacturas();
		} else {
			facturaActual = facturaActual - 1;
			recorrerfacturas();
		}
		// System.out.println(facturaActual);

	}

}
