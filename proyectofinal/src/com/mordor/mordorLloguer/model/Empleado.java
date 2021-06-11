package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.Comparator;

public class Empleado {
	
	
	private String DNI;
	private String nombre;
	private String apellidos;
	private String CP;
	private String email;
	private Date fechaNac;
	private String cargo;
	private String domicilio;
	private String password;
	public static final Comparator COMPARE_BY_DNI = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p0.getDNI().compareTo(p1.getDNI());
		}			
	};
	public static final Comparator COMPARE_BY_NOMBRE = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p0.getNombre().compareTo(p1.getNombre());
		}			
	};
	public static final Comparator COMPARE_BY_APELLIDO = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p0.getApellidos().compareTo(p1.getApellidos());
		}			
	};
	public static final Comparator COMPARE_BY_CARGO = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p0.getCargo().compareTo(p1.getCargo());
		}			
	};
	public static final Comparator COMPARE_BY_DNI_DESCEN = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p1.getCargo().compareTo(p0.getCargo());
		}			
	};
	public static final Comparator COMPARE_BY_NOMBRE_DESCEN= new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p1.getNombre().compareTo(p0.getNombre());
		}			
	};
	public static final Comparator COMPARE_BY_APELLIDO_DESCEN = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p1.getApellidos().compareTo(p0.getApellidos());
		}			
	};
	public static final Comparator COMPARE_BY_CARGO_DESCEN = new Comparator<Empleado>() {
		@Override
		public int compare(Empleado p0, Empleado p1) {
			
			return p1.getCargo().compareTo(p0.getCargo());
		}			
	};

	public Empleado(String dNI, String nombre, String apellidos, String cP, String email,
			Date date, String cargo, String domicilio, String password) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		CP = cP;
		this.email = email;
		this.fechaNac = date;
		this.cargo = cargo;
		this.domicilio = domicilio;
		this.password = password;
	}
	public Empleado(String dni) {
		this.DNI = dni;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Empleado [DNI=" + DNI + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", CP=" + CP + ", email=" + email + ", fechaNac=" + fechaNac + ", cargo=" + cargo
				+ ", domicilio=" + domicilio + ", password=" + password + "]"+"\n";
	}
	
	
	
}
