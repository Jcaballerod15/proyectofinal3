package com.mordor.mordorLloguer.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

public class MyOracleDataBase implements AlmacenDatosDB {
//todos los comentarios con lo que hace cada metodo esta en la clase abastracta que implementa
	/**
	 * 
	 * @param where insertar condicion
	 * @return devuelve una lista de empleados
	 */
	public ArrayList<Empleado> getCustomEmpleados(String where) {

		DataSource ds = MyDataSource.getOracleDataSource();

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String query = "SELECT * FROM EMPLEADO";
		if (where != null) {
			query += " WHERE " + where;
		}

		try (Connection con = ds.getConnection();

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(query);) {

			Empleado empleado;

			while (rs.next()) {

				empleado = new Empleado(rs.getString("DNI"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"), rs.getString("cargo"),
						rs.getString("domicilio"), null);
				empleados.add(empleado);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return empleados;

	}
	/**
	 * @param cp insertar codigo postal
	 * @return devuelve una lista de empleados
	 */
	@Override
	public ArrayList<Empleado> getEmpleadosPorCP(String cp) {

		return getCustomEmpleados("CP=" + cp);
	}
	/**
	 * @param cargo inserta cargo de empleado
	 * @return devuelve una lista de empleados
	 */
	@Override
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {

		return getCustomEmpleados("cargo='" + cargo + "'");
	}
	/**
	 * 
	 * @return devuelve una lista de empleados
	 */
	@Override
	public ArrayList<Empleado> getEmpleados() {

		return getCustomEmpleados(null);
	}
	/**
	 * @param dni insertar dni empleado
	 * @return devuelve una lista de empleados
	 */
	@Override
	public Empleado getEmpleadoPorDNI(String dni) {
		if (getCustomEmpleados("DNI='" + dni + "'").size() > 0) {
			return getCustomEmpleados("DNI='" + dni + "'").get(0);
		} else

			return null;
	}
	@Override
	/**
	 * @return devuelve true si se crea un empleado en la BD
	 */
	public boolean crearEmpleado(Empleado empleado) {
		boolean creado = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "insert into Empleado (dni, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password)"
				+ "VALUES(?,?,?,?,?,?,?,?,ENCRYPT_PASWD.encrypt_val(?))";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			int pos = 0;

			pstmt.setString(++pos, empleado.getDNI());
			pstmt.setString(++pos, empleado.getNombre());
			pstmt.setString(++pos, empleado.getApellidos());
			pstmt.setString(++pos, empleado.getDomicilio());
			pstmt.setString(++pos, empleado.getCP());
			pstmt.setString(++pos, empleado.getEmail());
			pstmt.setDate(++pos, empleado.getFechaNac());
			pstmt.setString(++pos, empleado.getCargo());
			pstmt.setString(++pos, empleado.getPassword());

			if (getEmpleadoPorDNI(empleado.getDNI()) != null) {
				creado = false;
			} else {
				pstmt.executeUpdate();
				creado = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return creado;
	}

	@Override
	/**
	 * @return devuelve una true si se actualiza un empleado
	 */
	public boolean updateEmpleado(Empleado empleado) {
		boolean actualizado = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();

				Statement stmt = con.createStatement();) {

			String query = "UPDATE EMPLEADO SET nombre=\"" + empleado.getNombre() + "\", " + "apellidos=\""
					+ empleado.getApellidos() + "\"," + "domicilio=\"" + empleado.getDomicilio() + "\"," + "CP=\""
					+ empleado.getCP() + "\"," + "email=\"" + empleado.getEmail() + "\"," + "fechaNac=TO_DATE('\""
					+ empleado.getFechaNac() + "','yyyy'-'mm'-'dd')" + "\"," + "cargo=\"" + empleado.getCargo() + "\" "
					+ "WHERE DNI=\"" + empleado.getDNI() + "\"";

			stmt.executeUpdate(query);

			actualizado = (stmt.executeUpdate(query) == 1) ? true : false;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return actualizado;
	}
	/**
	 * @return devuelve un true si elimina un empleado
	 */
	@Override
	public boolean deleteEmpleado(String dni) {
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "DELETE FROM EMPLEADO WHERE DNI= '" + dni + "'";
		try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();) {

			if (stmt.executeUpdate(query) == 1)
				borrar = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrar;
	}
	/**
	 * @return devuelve un true si el usuario y la contraseña existen en la BD
	 */
	@Override
	public boolean athenticate(String dni, String password) throws Exception {

		DataSource ds = MyDataSource.getOracleDataSource();
		boolean authenticated = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND password=ENCRYPT_PASWD.encrypt_val(?)";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, dni);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			int cantidad = 0;

			if (rs.next())
				cantidad = rs.getInt(1);

			authenticated = cantidad == 1;

			if (!authenticated)
				throw new Exception("Usuario/Password incorrecto");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1017)
				throw new Exception("Connection refused. Check server configuration.");
			else
				throw new Exception(e.getMessage());
		}

		return authenticated;
	}

	@Override

	public boolean addCustomer(Customer customer) throws SQLException {
		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONALQUILER.grabarCliente(?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			System.out.println(customer.getImgfoto()[1]);
			cstmt.setString(++pos, customer.getDNI());

			cstmt.setString(++pos, customer.getNombre());

			cstmt.setString(++pos, customer.getApellidos());

			cstmt.setString(++pos, customer.getEmail());

			cstmt.setDate(++pos, customer.getFechaNac());

			cstmt.setString(++pos, String.valueOf(customer.getCarnet()));

			cstmt.setBytes(++pos, customer.getImgfoto());

			cstmt.setString(++pos, customer.getDomicilio());

			cstmt.setString(++pos, customer.getCP());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;

	}

	public boolean deleteCliente(String dni) {
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONALQUILER.bajaCliente(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setString(1, dni);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrar;
	}

	@Override
	public ArrayList<Customer> getCustomers() {
		ResultSet rs = null;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call ?:=GESTIONALQUILER.listarclientes() }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.execute();

			rs = (ResultSet) cstmt.getObject(1);
			Customer customer;

			while (rs.next()) {

				customer = new Customer(rs.getInt("idcliente") ,rs.getString("DNI"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("carnet").charAt(0), rs.getString("domicilio"), rs.getBytes("foto"));
				customers.add(customer);
				//System.out.println(customer);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return customers;
	}

	@Override
	public ArrayList<Coche> getCoche() {

		ResultSet rs = null;
		ArrayList<Coche> coches = new ArrayList<Coche>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call GESTIONARVEHICULO.listarvehiculos(?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			cstmt.setString(1, "COCHE");
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();

			rs = (ResultSet) cstmt.getObject(2);
			Coche car;

			String matricula, marca, descripcion, color, motor, estado, carnet;
			float precioDia, medida;
			int cilindarada, numpuertas, numPlazas, mma, numRuedas;
			Date fechaAdq;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			while (rs.next()) {
				matricula = rs.getString("c1");
				marca = rs.getString("c2");
				descripcion = rs.getString("c3");
				color = rs.getString("c4");
				motor = rs.getString("c5");
				fechaAdq = new Date(format.parse(rs.getString("c6")).getTime());
				estado = rs.getString("c7");
				carnet = rs.getString("c8");
				precioDia = rs.getFloat("n1");
				cilindarada = rs.getInt("n2");
				numpuertas = rs.getInt("n4");
				numPlazas = rs.getInt("n3");

				car = new Coche(matricula, precioDia, marca, descripcion, color, motor, cilindarada, fechaAdq, estado,
						carnet, numPlazas, numpuertas);
				coches.add(car);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return coches;
	}

	public ArrayList<Camion> getCamion() {

		ResultSet rs = null;
		ArrayList<Camion> camiones = new ArrayList<Camion>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call GESTIONARVEHICULO.listarvehiculos(?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			cstmt.setString(1, "CAMION");
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();

			rs = (ResultSet) cstmt.getObject(2);
			Camion cam;

			String matricula, marca, descripcion, color, motor, estado, carnet;
			float precioDia, medida;
			int cilindarada, numpuertas, numPlazas, mma, numRuedas;
			Date fechaAdq;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			while (rs.next()) {
				matricula = rs.getString("c1");
				marca = rs.getString("c2");
				descripcion = rs.getString("c3");
				color = rs.getString("c4");
				motor = rs.getString("c5");
				fechaAdq = new Date(format.parse(rs.getString("c6")).getTime());
				estado = rs.getString("c7");
				carnet = rs.getString("c8");
				precioDia = rs.getFloat("n1");
				cilindarada = rs.getInt("n2");
				numRuedas = rs.getInt("n4");
				mma = rs.getInt("n3");

				cam = new Camion(matricula, precioDia, marca, descripcion, color, motor, cilindarada, fechaAdq, estado,
						carnet, numRuedas, mma);
				camiones.add(cam);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return camiones;
	}

	public ArrayList<Furgoneta> getFurgonetas() {

		ResultSet rs = null;
		ArrayList<Furgoneta> furgo = new ArrayList<Furgoneta>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call GESTIONARVEHICULO.listarvehiculos(?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			cstmt.setString(1, "FURGONETA");
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();

			rs = (ResultSet) cstmt.getObject(2);
			Furgoneta fur;

			String matricula, marca, descripcion, color, motor, estado, carnet;
			float precioDia, medida;
			int cilindarada, numpuertas, numPlazas, mma, numRuedas;
			Date fechaAdq;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			while (rs.next()) {
				matricula = rs.getString("c1");
				marca = rs.getString("c2");
				descripcion = rs.getString("c3");
				color = rs.getString("c4");
				motor = rs.getString("c5");
				fechaAdq = new Date(format.parse(rs.getString("c6")).getTime());
				estado = rs.getString("c7");
				carnet = rs.getString("c8");
				precioDia = rs.getFloat("n1");
				cilindarada = rs.getInt("n2");
				mma = rs.getInt("n3");

				fur = new Furgoneta(matricula, precioDia, marca, descripcion, color, motor, cilindarada, fechaAdq, estado,
						carnet, mma);
				furgo.add(fur);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return furgo;
	}
	public ArrayList<Microbus> getMicrobus() {

		ResultSet rs = null;
		ArrayList<Microbus> microbus = new ArrayList<Microbus>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call GESTIONARVEHICULO.listarvehiculos(?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			cstmt.setString(1, "MICROBUS");
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();

			rs = (ResultSet) cstmt.getObject(2);
			Microbus mi;

			String matricula, marca, descripcion, color, motor, estado, carnet;
			float precioDia, medida;
			int cilindarada, numpuertas, numPlazas, mma, numRuedas;
			Date fechaAdq;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			while (rs.next()) {
				matricula = rs.getString("c1");
				marca = rs.getString("c2");
				descripcion = rs.getString("c3");
				color = rs.getString("c4");
				motor = rs.getString("c5");
				fechaAdq = new Date(format.parse(rs.getString("c6")).getTime());
				estado = rs.getString("c7");
				carnet = rs.getString("c8");
				precioDia = rs.getFloat("n1");
				cilindarada = rs.getInt("n2");
				numPlazas = rs.getInt("n3");
				medida = rs.getInt("n4");

				mi = new Microbus(matricula, precioDia, marca, descripcion, color, motor, cilindarada, fechaAdq, estado,
						carnet, numPlazas, medida);
				microbus.add(mi);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return microbus;
	}

	@Override
	public boolean addCoche(Coche car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.insetarCoche(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getNumPlazas());
			
			cstmt.setInt(++pos, car.getNumPuertas());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean addCamion(Camion car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.insetarCamion(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getNumRuedas());
			
			cstmt.setInt(++pos, car.getMMA());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean addFurgoneta(Furgoneta car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.insetarFurgoneta(?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getMMA());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean addMicrobus(Microbus car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.insetarMicrobus(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getNumPlazas());
			
			cstmt.setFloat(++pos, car.getMedida());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean deleteCoche(String matricula) {
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONARVEHICULO.borrarCoche(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setString(1, matricula);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrar;
	}

	@Override
	public boolean deleteCamion(String matricula) {
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONARVEHICULO.borrarCamion(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setString(1, matricula);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrar;
	}

	@Override
	public boolean deleteFurgoneta(String matricula) {
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONARVEHICULO.borrarFurgoneta(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setString(1, matricula);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrar;
	}

	@Override
	public boolean deleteMicrobus(String matricula) {
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONARVEHICULO.borrarMicrobus(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setString(1, matricula);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrar;
	}
	@Override
	public boolean updateCoche(Coche car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.actualizarCoche(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getNumPlazas());
			
			cstmt.setInt(++pos, car.getNumPuertas());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean updateCamion(Camion car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.actualizarCamion(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getNumRuedas());
			
			cstmt.setInt(++pos, car.getMMA());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean updateFurgoneta(Furgoneta car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.actualizarFurgoneta(?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getMMA());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public boolean updateMicrobus(Microbus car) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONARVEHICULO.actualizarMicrobus(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection();

				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;

			cstmt.setString(++pos, car.getMatricula());

			cstmt.setFloat(++pos, car.getPrecioDia());

			cstmt.setString(++pos, car.getMarca());

			cstmt.setString(++pos, car.getDescripcion());
			
			cstmt.setString(++pos, car.getColor());
			
			cstmt.setString(++pos, car.getMotor());
			
			cstmt.setInt(++pos, car.getCilindrada());
			
			cstmt.setDate(++pos, car.getFechaAdq());
			
			cstmt.setString(++pos, car.getEstado());

			cstmt.setString(++pos, String.valueOf(car.getCarnet()));

			cstmt.setInt(++pos, car.getNumPlazas());
			
			cstmt.setFloat(++pos, car.getMedida());

			added = (cstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	@Override
	public ArrayList<Factura> getFactura() {
		ResultSet rs = null;
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call ?:=GESTIONALQUILER.listarfacturas() }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);

			cstmt.execute();

			rs = (ResultSet) cstmt.getObject(1);
			Factura fac;

			while (rs.next()) {

				fac = new Factura(rs.getInt("IDFACTURA"), rs.getDate("fecha"),rs.getFloat("IMPORTEBASE"),rs.getFloat("IMPORTEIVA"),rs.getInt("clienteid"));
				facturas.add(fac);
				//System.out.println(facturas);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return facturas;
	}

	@Override
	public ArrayList<Alquiler> getAlquiler() {

		DataSource ds = MyDataSource.getOracleDataSource();

		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		String query = "SELECT * FROM ALQUILER";

		try (Connection con = ds.getConnection();

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(query);) {

			Alquiler alqui;

			while (rs.next()) {

				alqui = new Alquiler(rs.getInt("idalquiler"),rs.getInt("idfactura"),rs.getString("matricula"),rs.getDate("fechainicio"),rs.getDate("fechafin"),rs.getFloat("precio"));
				alquileres.add(alqui);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return alquileres;
	}
	@Override
	public ArrayList<Integer> insertarAlquiler(int idFactura, String dni, String matricula, Date fechainicio, Date fechafin) throws SQLException{
		ArrayList<Integer> identi = new ArrayList<Integer>();
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call ?:=GESTIONALQUILER.insertarAlquiler(?,?,?,?,?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			
			int pos = 0;
			
			cstmt.registerOutParameter(++pos, OracleTypes.INTEGER);
			
			cstmt.setInt(++pos, idFactura);
			
			cstmt.registerOutParameter(++pos, OracleTypes.INTEGER);
			
			cstmt.setString(++pos, dni);

			cstmt.setString(++pos, matricula);
			
			cstmt.setDate(++pos, fechainicio);
			
			cstmt.setDate(++pos, fechafin);
			
			cstmt.execute();
			
			int idfac = cstmt.getInt(1);
			
			int idAlqui = cstmt.getInt(3);
			
			//System.out.println(idfac + " " + idAlqui);
			//rs = (ResultSet) cstmt.getObject(1);
			
				identi.add(idfac);
				identi.add(idAlqui);
			
			
		} 
		return identi;
	}

	@Override
	public ArrayList<Integer> insertarFactura(String dni, String matricula,Date fechainicio,Date fechafin) throws SQLException{
		
		ArrayList<Integer> identi = new ArrayList<Integer>();
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call ?:=GESTIONALQUILER.insertarAlquiler(?,?,?,?,?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			
			int pos = 0;

//			function insertarAlquiler (idenfac number,idAlqui out number, r_dni varchar2, r_matricula varchar2, r_fechainicio date, r_fechafin date) return number is
			cstmt.registerOutParameter(++pos, OracleTypes.INTEGER);
			
			cstmt.setString(++pos, null);
			
			cstmt.registerOutParameter(++pos, OracleTypes.INTEGER);
			
			cstmt.setString(++pos, dni);

			cstmt.setString(++pos, matricula);
			
			cstmt.setDate(++pos, fechainicio);
			
			cstmt.setDate(++pos, fechafin);
			//System.out.println("dfaf");
			cstmt.execute();
			
			int idfac = cstmt.getInt(1);
			
			int idAlqui = cstmt.getInt(3);
			
			//System.out.println(idfac + " " + idAlqui);
			//rs = (ResultSet) cstmt.getObject(1);
			
				identi.add(idfac);
				identi.add(idAlqui);

		} 
		return identi;
	}

	@Override
	public boolean updateAlquiler(int idAlqui, Date fechainicio, Date fechafin) throws SQLException{
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call GESTIONALQUILER.modificarAlquiler(?,?,?) }";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {
			
			int pos = 0;
			
			cstmt.setInt(++pos, idAlqui);
			
			cstmt.setDate(++pos, fechainicio);
			
			cstmt.setDate(++pos, fechafin);
			//System.out.println("dfaf");
			added = cstmt.execute();

		} 
		return added;
	}

	@Override
	public boolean deleteAlquiler(int idAlqui){
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONALQUILER.bajaAlquiler(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setInt(1, idAlqui);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return borrar;
	}

	@Override
	public boolean facturaCobrada(int idFactura) throws SQLException{
		boolean borrar = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{call GESTIONALQUILER.facturaCobrada(?)}";
		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			cstmt.setInt(1, idFactura);

			if (cstmt.executeUpdate() == 1)
				borrar = true;

		} 

		return borrar;
	}

//	public boolean authenticate(String login, String passwd) {
//		boolean entrada = false;
//		
//		DataSource ds = MyDataSource.getOracleDataSource();
//		
//		String query="SELECT COUNT(*) FROM EMPLEADO WHERE DNI='" + login +"' AND "+"password='"+passwd+"'";
//		
//
//		try (Connection con = ds.getConnection();
//
//				Statement stmt = con.createStatement();
//
//				ResultSet rs = stmt.executeQuery(query);) {
//
//			rs.next();
//			if(rs.getInt(1)>0)
//				entrada=true;
//				
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//
//		}
//		return false;
//	}
}
