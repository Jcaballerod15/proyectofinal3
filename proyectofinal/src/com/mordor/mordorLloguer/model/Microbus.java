package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Microbus extends Vehiculo{
	
	protected int numPlazas;
	protected float medida;
	
	public Microbus(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int numPlazas, float medida) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		this.numPlazas = numPlazas;
		this.medida = medida;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public float getMedida() {
		return medida;
	}

	public void setMedida(float medida) {
		this.medida = medida;
	}
	public boolean equals(Object o) {
		if (o instanceof Microbus) {
			Microbus c = (Microbus) o;
			if (this.getMatricula().compareTo(c.getMatricula()) == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	

}
