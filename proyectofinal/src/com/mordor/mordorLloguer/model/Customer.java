package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.Arrays;

public class Customer {

	private int idCliente;
	private String DNI;
	private String nombre;
	private String apellidos;
	private String CP;
	private String email;
	private Date fechaNac;
	private char carnet;
	private byte[] imgfoto;
	private String domicilio;

	public Customer(String dNI, String nombre, String apellidos, String cP, String email, Date fechaNac, char carnet,
			String domicilio, byte[] imgfoto) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
		this.domicilio = domicilio;
		this.imgfoto = imgfoto;

	}
	
	public Customer(int idCliente, String dNI,String nombre, String apellidos, String cP, String email, Date fechaNac, char carnet,
			String domicilio, byte[] imgfoto) {
		super();
		this.idCliente = idCliente;
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
		this.imgfoto = imgfoto;
		this.domicilio = domicilio;
	}


	public String getDNI() {
		return DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getCP() {
		return CP;
	}

	public String getEmail() {
		return email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public char getCarnet() {
		return carnet;
	}

	public byte[] getImgfoto() {
		return imgfoto;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setImgfoto(byte[] imgfoto) {
		this.imgfoto = imgfoto;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setCarnet(char carnet) {
		this.carnet = carnet;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	

	public int getIdCliente() {
		return idCliente;
	}

	@Override
	public String toString() {
		return "Customer [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", CP=" + CP + ", email="
				+ email + ", fechaNac=" + fechaNac + ", carnet=" + carnet + ", domicilio=" + domicilio + "]";
	}
	@Override
	public boolean equals(Object o) {
		if (o instanceof Customer) {
			Customer c = (Customer) o;
			if (this.getDNI().compareTo(c.getDNI()) == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
