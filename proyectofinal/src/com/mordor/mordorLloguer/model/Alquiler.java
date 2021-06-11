package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Alquiler {
	private int idAlquiler;
	private int idFactura;
	private String matricula;
	private Date fechainicio;
	private Date fechafin;
	private float precio;
	
	
	public Alquiler(int idAlquiler, int idFactura, String matricula, Date fechainicio, Date fechafin, float precio) {
		super();
		this.idAlquiler = idAlquiler;
		this.idFactura = idFactura;
		this.matricula = matricula;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.precio = precio;
	}
	
	public int getIdAlquiler() {
		return idAlquiler;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public String getMatricula() {
		return matricula;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public float getPrecio() {
		return precio;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
