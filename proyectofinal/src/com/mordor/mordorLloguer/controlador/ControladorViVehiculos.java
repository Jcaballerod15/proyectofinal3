package com.mordor.mordorLloguer.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Camion;
import com.mordor.mordorLloguer.model.Coche;
import com.mordor.mordorLloguer.model.Customer;
import com.mordor.mordorLloguer.model.Furgoneta;
import com.mordor.mordorLloguer.model.Microbus;
import com.mordor.mordorLloguer.model.MyCarTableModel;
import com.mordor.mordorLloguer.model.MyMinibusTableModel;
import com.mordor.mordorLloguer.model.MyTrukTableModel;
import com.mordor.mordorLloguer.model.MyVanTableModel;
import com.mordor.mordorLloguer.model.Vehiculo;
import com.mordor.mordorLloguer.vistas.VistaCargarBD;
import com.mordor.mordorLloguer.vistas.VistaCrearVehiculo;
import com.mordor.mordorLloguer.vistas.VistaTablaVehiculos;

public class ControladorViVehiculos implements ActionListener, TableModelListener {

	private VistaTablaVehiculos vistaTablaVehiculo;
	private AlmacenDatosDB model;
	private ArrayList<Coche> coches;
	private ArrayList<Camion> camiones;
	private ArrayList<Furgoneta> furgos;
	private ArrayList<Microbus> micro;
	private static ControladorViVehiculos controlador;
	private MyCarTableModel mtmcar;
	private MyTrukTableModel mtmtru;
	private MyVanTableModel mtmvan;
	private MyMinibusTableModel mtmmic;
	private VistaCrearVehiculo crearVehiculo;
	private VistaCargarBD carga;
	DefaultComboBoxModel<String> combo;

	public ControladorViVehiculos(AlmacenDatosDB model) {

		this.model = model;
		this.vistaTablaVehiculo = new VistaTablaVehiculos();
		this.controlador = this;
		
		inicializar();

	}

	private void inicializar() {

		ControladorPincipal.insertDestokPane(vistaTablaVehiculo);
		ControladorPincipal.center(vistaTablaVehiculo);

		vistaTablaVehiculo.getBtnAdd().addActionListener(this);
		vistaTablaVehiculo.getBtnDelete().addActionListener(this);
		vistaTablaVehiculo.getPanelCar().getTxtMatricula().addActionListener(this);
		vistaTablaVehiculo.getPanelCar().gettxtModel().addActionListener(this);
		vistaTablaVehiculo.getPanelCar().getCboxCarnet().addActionListener(this);
		vistaTablaVehiculo.getPanelCar().getCboxMotor().addActionListener(this);
		vistaTablaVehiculo.getPanelVan().getTxtMatricula().addActionListener(this);
		vistaTablaVehiculo.getPanelVan().gettxtModel().addActionListener(this);
		vistaTablaVehiculo.getPanelVan().getCboxCarnet().addActionListener(this);
		vistaTablaVehiculo.getPanelVan().getCboxMotor().addActionListener(this);
		vistaTablaVehiculo.getPanelTruck().getTxtMatricula().addActionListener(this);
		vistaTablaVehiculo.getPanelTruck().gettxtModel().addActionListener(this);
		vistaTablaVehiculo.getPanelTruck().getCboxCarnet().addActionListener(this);
		vistaTablaVehiculo.getPanelTruck().getCboxMotor().addActionListener(this);
		vistaTablaVehiculo.getPanelMicrobus().getTxtMatricula().addActionListener(this);
		vistaTablaVehiculo.getPanelMicrobus().gettxtModel().addActionListener(this);
		vistaTablaVehiculo.getPanelMicrobus().getCboxCarnet().addActionListener(this);
		vistaTablaVehiculo.getPanelMicrobus().getCboxMotor().addActionListener(this);

		vistaTablaVehiculo.getBtnAdd().setActionCommand("AddVehiculo");
		vistaTablaVehiculo.getBtnDelete().setActionCommand("DeleteVehiculo");
		
		vistaTablaVehiculo.getPanelCar().getTxtMatricula().setActionCommand("filtrarCoche");
		vistaTablaVehiculo.getPanelCar().gettxtModel().setActionCommand("filtrarCoche");
		vistaTablaVehiculo.getPanelCar().getCboxCarnet().setActionCommand("filtrarCoche");
		vistaTablaVehiculo.getPanelCar().getCboxMotor().setActionCommand("filtrarCoche");
		
		vistaTablaVehiculo.getPanelVan().getTxtMatricula().setActionCommand("filtrarVan");
		vistaTablaVehiculo.getPanelVan().gettxtModel().setActionCommand("filtrarVan");
		vistaTablaVehiculo.getPanelVan().getCboxCarnet().setActionCommand("filtrarVan");
		vistaTablaVehiculo.getPanelVan().getCboxMotor().setActionCommand("filtrarVan");
		
		vistaTablaVehiculo.getPanelTruck().getTxtMatricula().setActionCommand("filtrarCamion");
		vistaTablaVehiculo.getPanelTruck().gettxtModel().setActionCommand("filtrarCamion");
		vistaTablaVehiculo.getPanelTruck().getCboxCarnet().setActionCommand("filtrarCamion");
		vistaTablaVehiculo.getPanelTruck().getCboxMotor().setActionCommand("filtrarCamion");
		
		vistaTablaVehiculo.getPanelMicrobus().getTxtMatricula().setActionCommand("filtrarMicro");
		vistaTablaVehiculo.getPanelMicrobus().gettxtModel().setActionCommand("filtrarMicro");
		vistaTablaVehiculo.getPanelMicrobus().getCboxCarnet().setActionCommand("filtrarMicro");
		vistaTablaVehiculo.getPanelMicrobus().getCboxMotor().setActionCommand("filtrarMicro");

		crearTabla();
	}
	//recibimos los datos de todos los vehiculos y los plasmamos los datos en las tablas correctas
	private void crearTabla() {
		SwingWorker task = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				try {
					coches = new ArrayList<Coche>();
					coches = model.getCoche();
					camiones= new ArrayList<Camion>();
					camiones = model.getCamion();
					furgos= new ArrayList<Furgoneta>();
					furgos = model.getFurgonetas();
					micro= new ArrayList<Microbus>();
					micro = model.getMicrobus();
				} catch (Exception es) {
					JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se puede acceder a la base de datos",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
				return null;
			}

			protected void done() {
				carga.setVisible(false);
				// CREAR COCHE
				mtmcar = new MyCarTableModel(coches);
				vistaTablaVehiculo.getCarTable().setModel(mtmcar);
				vistaTablaVehiculo.getCarTable().setDefaultEditor(Date.class, new WebDateEditor());
				// Adding comboBox just to edit the company position in the WebTable
				JComboBox<String> comboBox = new JComboBox<String>();
				comboBox.addItem("electrico");
				comboBox.addItem("diesel");
				comboBox.addItem("hibrido enchufable");
				comboBox.addItem("diesel");
				JComboBox<String> comboBox2 = new JComboBox<String>();
				comboBox2.addItem("preparado");
				comboBox2.addItem("alquilado");
				comboBox2.addItem("taller");
				TableColumn column = vistaTablaVehiculo.getCarTable().getColumn("Motor");
				column.setCellEditor(new DefaultCellEditor(comboBox));
				TableColumn column2 = vistaTablaVehiculo.getCarTable().getColumn("Estado");
				column2.setCellEditor(new DefaultCellEditor(comboBox2));
				mtmcar.addTableModelListener(ControladorViVehiculos.controlador);
				crearCboxCoche();
				// CREAR CAMION
				mtmtru = new MyTrukTableModel(camiones);
				vistaTablaVehiculo.getTruckTable().setModel(mtmtru);
				vistaTablaVehiculo.getTruckTable().setDefaultEditor(Date.class, new WebDateEditor());
				TableColumn column4 = vistaTablaVehiculo.getTruckTable().getColumn("Motor");
				column4.setCellEditor(new DefaultCellEditor(comboBox));
				TableColumn column5 = vistaTablaVehiculo.getTruckTable().getColumn("Estado");
				column5.setCellEditor(new DefaultCellEditor(comboBox2));
				mtmtru.addTableModelListener(ControladorViVehiculos.controlador);
				crearCboxCamion();
				// CREAR FURGONETA
				mtmvan = new MyVanTableModel(furgos);
				vistaTablaVehiculo.getVanTable().setModel(mtmvan);
				vistaTablaVehiculo.getVanTable().setDefaultEditor(Date.class, new WebDateEditor());
				TableColumn column6 = vistaTablaVehiculo.getVanTable().getColumn("Motor");
				column6.setCellEditor(new DefaultCellEditor(comboBox));
				TableColumn column7 = vistaTablaVehiculo.getVanTable().getColumn("Estado");
				column7.setCellEditor(new DefaultCellEditor(comboBox2));
				mtmvan.addTableModelListener(ControladorViVehiculos.controlador);
				crearCboxFurgo();
				// CREAR MICROBUS
				mtmmic = new MyMinibusTableModel(micro);
				vistaTablaVehiculo.getMicrobusTable().setModel(mtmmic);
				vistaTablaVehiculo.getMicrobusTable().setDefaultEditor(Date.class, new WebDateEditor());
				TableColumn column8 = vistaTablaVehiculo.getMicrobusTable().getColumn("Motor");
				column8.setCellEditor(new DefaultCellEditor(comboBox));
				TableColumn column9 = vistaTablaVehiculo.getMicrobusTable().getColumn("Estado");
				column9.setCellEditor(new DefaultCellEditor(comboBox2));
				mtmmic.addTableModelListener(ControladorViVehiculos.controlador);
				crearCboxMicro();
				
			}
		};
		carga = new VistaCargarBD(task, "Cargando las tablas de Vehiculos");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//creamos el comboBox para filtrar coches
	private void crearCboxCoche() {
		Set<String> datos = coches.stream().map((c)->c.getMotor())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String motor:datos)
			combo.addElement(motor);
		vistaTablaVehiculo.getPanelCar().getCboxMotor().setModel(combo);

		Set<String> datos2 = coches.stream().map((c)->c.getCarnet())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String carnet:datos2)
			combo.addElement(carnet);
		vistaTablaVehiculo.getPanelCar().getCboxCarnet().setModel(combo);
	}
	//creamos el comboBox para filtrar furgos
	private void crearCboxFurgo() {
		Set<String> datos = furgos.stream().map((c)->c.getMotor())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String motor:datos)
			combo.addElement(motor);
		vistaTablaVehiculo.getPanelVan().getCboxMotor().setModel(combo);

		Set<String> datos2 = furgos.stream().map((c)->c.getCarnet())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String carnet:datos2)
			combo.addElement(carnet);
		vistaTablaVehiculo.getPanelVan().getCboxCarnet().setModel(combo);
	}
	//creamos el comboBox para filtrar camiones
	private void crearCboxCamion() {
		Set<String> datos = camiones.stream().map((c)->c.getMotor())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String motor:datos)
			combo.addElement(motor);
		vistaTablaVehiculo.getPanelTruck().getCboxMotor().setModel(combo);

		Set<String> datos2 = camiones.stream().map((c)->c.getCarnet())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String carnet:datos2)
			combo.addElement(carnet);
		vistaTablaVehiculo.getPanelTruck().getCboxCarnet().setModel(combo);
	}
	//creamos el comboBox para filtrar microbuses
	private void crearCboxMicro() {
		Set<String> datos = micro.stream().map((c)->c.getMotor())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String motor:datos)
			combo.addElement(motor);
		vistaTablaVehiculo.getPanelMicrobus().getCboxMotor().setModel(combo);

		Set<String> datos2 = micro.stream().map((c)->c.getCarnet())
				.collect(Collectors.toSet());
		combo = new DefaultComboBoxModel<String>();
		combo.addElement("All");
		for(String carnet:datos2)
			combo.addElement(carnet);
		vistaTablaVehiculo.getPanelMicrobus().getCboxCarnet().setModel(combo);
	}
	//Updatemos en la BD dependiendo de la pestaña
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		if (e.getType() == TableModelEvent.UPDATE) {
			if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 0) {
				System.out.println("Se ha actualizado la tabla " + e.getFirstRow());
				Coche car = mtmcar.get(e.getFirstRow());
				//coches.remove(car);
				//coches.add(car);
				System.out.println(car);
				try {
					model.updateCoche(car);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se puede actualizar al tabla Coches",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 1) {
				System.out.println("Se ha actualizado la tabla " + e.getFirstRow());
				Furgoneta car = mtmvan.get(e.getFirstRow());
				//furgos.remove(car);
				//furgos.add(car);
				System.out.println(car);
				try {
					model.updateFurgoneta(car);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se puede actualizar al tabla Furgoneta",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 2) {
				System.out.println("Se ha actualizado la tabla " + e.getFirstRow());
				Camion car = mtmtru.get(e.getFirstRow());
				System.out.println(car);
				//camiones.remove(car);
				//camiones.add(car);
				try {
					model.updateCamion(car);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se puede actualizar al tabla Camion",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);

				}
			} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 3) {
				System.out.println("Se ha actualizado la tabla " + e.getFirstRow());
				Microbus car = mtmmic.get(e.getFirstRow());
				System.out.println(car);
				//micro.remove(car);
				//micro.add(car);
				try {
					model.updateMicrobus(car);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se puede actualizar al tabla Microbus",
							"Cuidado", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("DeleteVehiculo")) {
			deleteVehiculo();
		} else if (comando.equals("AddVehiculo")) {
			newVehiculo();
		} else if (comando.equals("CrearVehiculo")) {
			addVehiculo();
		}else if (comando.equals("filtrarCoche")) {
			filtroCoche(coches);
		}else if (comando.equals("filtrarVan")) {
			filtroFurgoneta(furgos);
		}else if (comando.equals("filtrarCamion")) {
			filtroCamion(camiones);
		}else if (comando.equals("filtrarMicro")) {
			filtroMicrobus(micro);
		}

	}
	//metodo para crear vehiculos donde dependiendo de la pestaña se creara un tipo de vehiculo
	private void addVehiculo() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {
			protected Boolean doInBackground() {
				String matricula = crearVehiculo.getTxtMatricula().getText();
				float precioDia = Float.parseFloat(crearVehiculo.getTxtPreciodia().getText());
				String marca = crearVehiculo.getTxtMarca().getText();
				String descripcion = crearVehiculo.getTextPaneDescrip().getText();
				String color = crearVehiculo.getTxtColor().getText();
				String motor = crearVehiculo.getComboBoxMotor().getSelectedItem().toString();
				int cilindrada = Integer.valueOf(crearVehiculo.getTxtCilindrada().getText());
				Date fechaAdq = new java.sql.Date(crearVehiculo.getTxtFecha().getDate().getTime());
				String estado = crearVehiculo.getComboBoxEstado().getSelectedItem().toString();
				String carnet = crearVehiculo.getComboBoxCarnet().getSelectedItem().toString();

				if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 0) {
					int numplazas = Integer.valueOf(crearVehiculo.getTxtDatosExtra1().getText());
					int numpuertas = Integer.valueOf(crearVehiculo.getTxtDatosExtra2().getText());
					Coche car = new Coche(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
							(java.sql.Date) fechaAdq, estado, carnet, numplazas, numpuertas);
					mtmcar.add(car);
					coches.add(car);
					try {
						return model.addCoche(car);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo crear el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 1) {
					int mma = Integer.valueOf(crearVehiculo.getTxtDatosExtra1().getText());
					Furgoneta car = new Furgoneta(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
							(java.sql.Date) fechaAdq, estado, carnet, mma);
					mtmvan.add(car);
					furgos.add(car);
					try {
						return model.addFurgoneta(car);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo crear el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 2) {
					int mma = Integer.valueOf(crearVehiculo.getTxtDatosExtra1().getText());
					int numruedas = Integer.valueOf(crearVehiculo.getTxtDatosExtra2().getText());
					Camion car = new Camion(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
							(java.sql.Date) fechaAdq, estado, carnet, numruedas, mma);
					mtmtru.add(car);
					camiones.add(car);
					try {
						return model.addCamion(car);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo crear el Camion", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 3) {
					int numplazas = Integer.valueOf(crearVehiculo.getTxtDatosExtra1().getText());
					float medida = Float.parseFloat(crearVehiculo.getTxtDatosExtra2().getText());
					Microbus car = new Microbus(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
							(java.sql.Date) fechaAdq, estado, carnet, numplazas, medida);
					mtmmic.add(car);
					micro.add(car);
					try {
						return model.addMicrobus(car);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo crear el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get() == true) {
						JOptionPane.showMessageDialog(crearVehiculo, "El Vehiculo se insertó correctamente",
								"Creado correctamente", JOptionPane.INFORMATION_MESSAGE);
						crearVehiculo.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo crear el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException | InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo crear el Vehiculo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		carga = new VistaCargarBD(task, "Creando Cliente");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//Creacion de la vista para crear los vehiculos
	private void newVehiculo() {

		crearVehiculo = new VistaCrearVehiculo();
		if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 0) {
			crearVehiculo.getLblTextoAtributoExtra2().setText("Num Puertas");
			crearVehiculo.getLabelTextoAtributoExtra().setText("Num Plazas");
		} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 1) {
			crearVehiculo.getLabelTextoAtributoExtra().setText("MMA");
			crearVehiculo.getLblTextoAtributoExtra2().setVisible(false);
			crearVehiculo.getTxtDatosExtra2().setVisible(false);
		} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 2) {
			crearVehiculo.getLabelTextoAtributoExtra().setText("MMA");
			crearVehiculo.getLblTextoAtributoExtra2().setText("Num Ruedas");
		} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 3) {
			crearVehiculo.getLblTextoAtributoExtra2().setText("Medida");
			crearVehiculo.getLabelTextoAtributoExtra().setText("Num Plazas");
		}
		ControladorPincipal.insertDestokPane(crearVehiculo);
		ControladorPincipal.center(crearVehiculo);
		crearVehiculo.getBtnAdd().addActionListener(this);
		crearVehiculo.getBtnAdd().setActionCommand("CrearVehiculo");
	}
	//eliminar vehiculos de la BD seleccionando las filas de los vehiculos
	private void deleteVehiculo() {
		SwingWorker task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {

				if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 0) {
					int[] coche = vistaTablaVehiculo.getCarTable().getSelectedRows();
					ArrayList<Coche> cars = new ArrayList<Coche>();
					for (int i = 0; i < coche.length; i++) {
						cars.add(mtmcar.get(coche[i]));
					}
					try {
						for (int j = 0; j < cars.size(); j++) {
							if (model.deleteCoche(cars.get(j).getMatricula())) {
								mtmcar.remove(cars.get(j));
								coches.remove(cars.get(j));
								return true;
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo eliminar el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 1) {
					int[] furg = vistaTablaVehiculo.getVanTable().getSelectedRows();
					ArrayList<Furgoneta> furgonet = new ArrayList<Furgoneta>();
					for (int i = 0; i < furg.length; i++) {
						furgonet.add(mtmvan.get(furg[i]));
					}
					try {
						for (int j = 0; j < furgonet.size(); j++) {
							if (model.deleteFurgoneta(furgonet.get(j).getMatricula())) {
								mtmvan.remove(furgonet.get(j));
								furgos.remove(furgonet.get(j));
								return true;
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo eliminar el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 2) {
					int[] camion = vistaTablaVehiculo.getTruckTable().getSelectedRows();
					ArrayList<Camion> cami = new ArrayList<Camion>();
					for (int i = 0; i < camion.length; i++) {
						cami.add(mtmtru.get(camion[i]));
					}
					try {
						for (int j = 0; j < cami.size(); j++) {
							if (model.deleteCamion(cami.get(j).getMatricula())) {
								mtmtru.remove(cami.get(j));
								camiones.remove(cami.get(j));
								return true;
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo eliminar el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (vistaTablaVehiculo.getTabbedPane().getSelectedIndex() == 3) {
					int[] micros = vistaTablaVehiculo.getMicrobusTable().getSelectedRows();
					ArrayList<Microbus> mi = new ArrayList<Microbus>();
					for (int i = 0; i < micros.length; i++) {
						mi.add(mtmmic.get(micros[i]));
					}
					try {
						for (int j = 0; j < mi.size(); j++) {
							if (model.deleteMicrobus(mi.get(j).getMatricula())) {
								mtmmic.remove(mi.get(j));
								micro.remove(mi.get(j));
								return true;
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "No se pudo eliminar el Vehiculo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				return false;
			}

			protected void done() {
				carga.setVisible(false);
				try {
					if (get() == true) {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "El Vehiculo se eliminó correctamente",
								"Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(vistaTablaVehiculo, "El Vehiculo, no existe",
								"No se puede eliminar", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		carga = new VistaCargarBD(task, "Borrando Vehiculos");
		ControladorPincipal.insertDestokPane(carga);
		ControladorPincipal.center(carga);
		task.execute();
	}
	//metodo para filtrar coches por los parametros recibidos
	private void filtroCoche(ArrayList<Coche> vehiculos) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("electrico");
		comboBox.addItem("diesel");
		comboBox.addItem("hibrido enchufable");
		comboBox.addItem("diesel");
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.addItem("preparado");
		comboBox2.addItem("alquilado");
		comboBox2.addItem("taller");
			List clientesFiltrados = vehiculos.stream().filter( c -> c.getMatricula().contains(vistaTablaVehiculo.getPanelCar().getTxtMatricula().getText()))
				.filter( c -> c.getMarca().contains(vistaTablaVehiculo.getPanelCar().gettxtModel().getText()))
				.filter( c -> c.getMotor().contains(vistaTablaVehiculo.getPanelCar().getCboxMotor().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelCar().getCboxMotor().getSelectedItem().toString().equals("All"))
				.filter( c -> c.getCarnet().contains(vistaTablaVehiculo.getPanelCar().getCboxCarnet().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelCar().getCboxCarnet().getSelectedItem().toString().equals("All"))    
				.collect(Collectors.toList());
			ArrayList<Coche> carse = (ArrayList<Coche>) clientesFiltrados; 
		MyCarTableModel mtmcar = new MyCarTableModel(carse);
		vistaTablaVehiculo.getCarTable().setModel(mtmcar);
		vistaTablaVehiculo.getCarTable().setDefaultEditor(Date.class, new WebDateEditor());
		TableColumn column = vistaTablaVehiculo.getCarTable().getColumn("Motor");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		TableColumn column2 = vistaTablaVehiculo.getCarTable().getColumn("Estado");
		column2.setCellEditor(new DefaultCellEditor(comboBox2));
		mtmcar.addTableModelListener(ControladorViVehiculos.controlador);
		
	}
	//metodo para filtrar furgonetas por los parametros recibidos
	private void filtroFurgoneta(ArrayList<Furgoneta> vehiculos) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("electrico");
		comboBox.addItem("diesel");
		comboBox.addItem("hibrido enchufable");
		comboBox.addItem("diesel");
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.addItem("preparado");
		comboBox2.addItem("alquilado");
		comboBox2.addItem("taller");
		List clientesFiltrados = vehiculos.stream().filter( c -> c.getMatricula().contains(vistaTablaVehiculo.getPanelVan().getTxtMatricula().getText()))
				.filter( c -> c.getMarca().contains(vistaTablaVehiculo.getPanelVan().gettxtModel().getText()))
				.filter( c -> c.getMotor().contains(vistaTablaVehiculo.getPanelVan().getCboxMotor().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelVan().getCboxMotor().getSelectedItem().toString().equals("All"))
				.filter( c -> c.getCarnet().contains(vistaTablaVehiculo.getPanelVan().getCboxCarnet().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelVan().getCboxCarnet().getSelectedItem().toString().equals("All"))    
				.collect(Collectors.toList());
		ArrayList<Furgoneta> carse = (ArrayList<Furgoneta>) clientesFiltrados;
		mtmvan = new MyVanTableModel(carse);
		vistaTablaVehiculo.getVanTable().setModel(mtmvan);
		vistaTablaVehiculo.getVanTable().setDefaultEditor(Date.class, new WebDateEditor());
		TableColumn column = vistaTablaVehiculo.getVanTable().getColumn("Motor");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		TableColumn column2 = vistaTablaVehiculo.getVanTable().getColumn("Estado");
		column2.setCellEditor(new DefaultCellEditor(comboBox2));
		mtmcar.addTableModelListener(ControladorViVehiculos.controlador);
	}
	//metodo para filtrar camiones por los parametros recibidos
	private void filtroCamion(ArrayList<Camion> vehiculos) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("electrico");
		comboBox.addItem("diesel");
		comboBox.addItem("hibrido enchufable");
		comboBox.addItem("diesel");
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.addItem("preparado");
		comboBox2.addItem("alquilado");
		comboBox2.addItem("taller");
		List clientesFiltrados = vehiculos.stream().filter( c -> c.getMatricula().contains(vistaTablaVehiculo.getPanelTruck().getTxtMatricula().getText()))
				.filter( c -> c.getMarca().contains(vistaTablaVehiculo.getPanelTruck().gettxtModel().getText()))
				.filter( c -> c.getMotor().contains(vistaTablaVehiculo.getPanelTruck().getCboxMotor().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelTruck().getCboxMotor().getSelectedItem().toString().equals("All"))
				.filter( c -> c.getCarnet().contains(vistaTablaVehiculo.getPanelTruck().getCboxCarnet().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelTruck().getCboxCarnet().getSelectedItem().toString().equals("All"))    
				.collect(Collectors.toList());
		ArrayList<Camion> carse = (ArrayList<Camion>) clientesFiltrados;
		mtmtru = new MyTrukTableModel(carse);
		vistaTablaVehiculo.getTruckTable().setModel(mtmtru);
		vistaTablaVehiculo.getTruckTable().setDefaultEditor(Date.class, new WebDateEditor());
		TableColumn column = vistaTablaVehiculo.getTruckTable().getColumn("Motor");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		TableColumn column2 = vistaTablaVehiculo.getTruckTable().getColumn("Estado");
		column2.setCellEditor(new DefaultCellEditor(comboBox2));
		mtmcar.addTableModelListener(ControladorViVehiculos.controlador);
	}
	//metodo para filtrar microbuses por los parametros recibidos
	private void filtroMicrobus(ArrayList<Microbus> vehiculos) {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("electrico");
		comboBox.addItem("diesel");
		comboBox.addItem("hibrido enchufable");
		comboBox.addItem("diesel");
		JComboBox<String> comboBox2 = new JComboBox<String>();
		comboBox2.addItem("preparado");
		comboBox2.addItem("alquilado");
		comboBox2.addItem("taller");
		List clientesFiltrados = vehiculos.stream().filter( c -> c.getMatricula().contains(vistaTablaVehiculo.getPanelMicrobus().getTxtMatricula().getText()))
				.filter( c -> c.getMarca().contains(vistaTablaVehiculo.getPanelMicrobus().gettxtModel().getText()))
				.filter( c -> c.getMotor().contains(vistaTablaVehiculo.getPanelMicrobus().getCboxMotor().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelMicrobus().getCboxMotor().getSelectedItem().toString().equals("All"))
				.filter( c -> c.getCarnet().contains(vistaTablaVehiculo.getPanelMicrobus().getCboxCarnet().getSelectedItem().toString()) || vistaTablaVehiculo.getPanelMicrobus().getCboxCarnet().getSelectedItem().toString().equals("All"))    
				.collect(Collectors.toList());
		ArrayList<Microbus> carse = (ArrayList<Microbus>) clientesFiltrados;
		mtmmic = new MyMinibusTableModel(carse);
		vistaTablaVehiculo.getMicrobusTable().setModel(mtmmic);
		vistaTablaVehiculo.getMicrobusTable().setDefaultEditor(Date.class, new WebDateEditor());
		TableColumn column = vistaTablaVehiculo.getMicrobusTable().getColumn("Motor");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		TableColumn column2 = vistaTablaVehiculo.getMicrobusTable().getColumn("Estado");
		column2.setCellEditor(new DefaultCellEditor(comboBox2));
		mtmcar.addTableModelListener(ControladorViVehiculos.controlador);
	}
}
