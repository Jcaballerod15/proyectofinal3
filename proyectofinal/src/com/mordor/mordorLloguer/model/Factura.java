package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Factura {
	private int idFactura;
	private Date fecha;
	private float importebase;
	private float importeiva;
	private int clienteid;
	
	
	public Factura(int idFactura, Date fecha, float importebase, float importeiva, int clienteid) {
		super();
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.importebase = importebase;
		this.importeiva = importeiva;
		this.clienteid = clienteid;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getImportebase() {
		return importebase;
	}
	public float getImporteiva() {
		return importeiva;
	}
	public int getClienteid() {
		return clienteid;
	}
	
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setImportebase(float importebase) {
		this.importebase = importebase;
	}
	public void setImporteiva(float importeiva) {
		this.importeiva = importeiva;
	}
	public void setClienteid(int clienteid) {
		this.clienteid = clienteid;
	}
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", fecha=" + fecha + ", importebase=" + importebase + ", importeiva="
				+ importeiva + ", clienteid=" + clienteid + "]";
	}
	
	
}
