package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AlmacenDatosDB {
	
	//metodo para recibir empleados de la BD
	public ArrayList<Empleado> getEmpleados();
	//metodo como el anterior pero depurado por codigo postal
	public ArrayList<Empleado> getEmpleadosPorCP(String cp);
	//metodo como el primero pero depurado por cargo
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo);
	//metodo para recibir un empleado de la BD por su dni
	public Empleado getEmpleadoPorDNI(String dni);
	//metodo para acturalizar un empleado de la BD
	public boolean updateEmpleado(Empleado empleado);
	//metodo para eliminar un empleado de la BD
	public boolean deleteEmpleado(String dni);
	//metodo para autenticar un usuario y contraseña en la BD
	public boolean athenticate (String login, String passwd) throws Exception;
	//metodo para crear empleados en la BD
	public boolean crearEmpleado(Empleado Employee);
	//metodo para crear clientes en la BD
	public boolean addCustomer(Customer customer) throws SQLException;
	//metodo para eliminar clientes de la BD
	public boolean deleteCliente(String dni);
	//metodo para recibirt todos los datos de los clientes en la BD
	public ArrayList<Customer> getCustomers();
	//metodo para recibirt todos los datos de los coches en la BD
	public ArrayList<Coche> getCoche();
	//metodo para recibirt todos los datos de los camiones en la BD
	public ArrayList<Camion> getCamion();
	//metodo para recibirt todos los datos de los furgonetas en la BD
	public ArrayList<Furgoneta> getFurgonetas();
	//metodo para recibirt todos los datos de los microbuses en la BD
	public ArrayList<Microbus> getMicrobus();
	//metodo para crear coches en la BD
	public boolean addCoche(Coche car) throws SQLException;
	//metodo para crear camiones en la BD
	public boolean addCamion(Camion car) throws SQLException;
	//metodo para crear furgonetas en la BD
	public boolean addFurgoneta(Furgoneta car) throws SQLException;
	//metodo para crear microbuses en la BD
	public boolean addMicrobus(Microbus car) throws SQLException;
	//metodo para borrar coches en la BD
	public boolean deleteCoche(String matricula);
	//metodo para borrar camiones en la BD
	public boolean deleteCamion(String matricula);
	//metodo para borrar furgonetas en la BD
	public boolean deleteFurgoneta(String matricula);
	//metodo para borrar microbuses en la BD
	public boolean deleteMicrobus(String matricula);
	//metodo para actualizar datos de los coches en la BD
	public boolean updateCoche(Coche car) throws SQLException;
	//metodo para actualizar datos de los camiones en la BD
	public boolean updateCamion(Camion car) throws SQLException;
	//metodo para actualizar datos de los furgonetas en la BD
	public boolean updateFurgoneta(Furgoneta car) throws SQLException;
	//metodo para actualizar datos de los microbuses en la BD
	public boolean updateMicrobus(Microbus car) throws SQLException;
	//metodo para recibir datos de las facturas de la BD
	public ArrayList<Factura> getFactura();
	//metodo para recibir datos de los alquileres de la BD
	public ArrayList<Alquiler> getAlquiler();
	//metodo para insertar facturas a la BD
	public ArrayList<Integer> insertarFactura(String dni,String matricula,Date fechainicio,Date fechafin)throws SQLException;
	//metodo para insertar alquileres en la BD
	public ArrayList<Integer> insertarAlquiler(int idFactura,String dni,String matricula,Date fechainicio,Date fechafin)throws SQLException;
	//metodo para acturalizar los datos en alquileres en la BD
	public boolean updateAlquiler(int idAlqui,Date fechainicio,Date fechafin)throws SQLException;
	//metodo para eliminar alquileres o facturas de la BD
	public boolean deleteAlquiler(int idAlqui);
	//metodo para comprobar que la fecha final del alquiler todavía no ha pasado 
	public boolean facturaCobrada(int idAlqui)throws SQLException;
}
