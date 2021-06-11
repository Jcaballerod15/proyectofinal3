package com.mordor.mordorLloguer.model;
import java.sql.Date;

public class Camion extends Vehiculo {

	protected int numRuedas;
	protected int MMA;
	
	public Camion(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int numRuedas, int mMA) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		this.numRuedas = numRuedas;
		MMA = mMA;
	}

	public int getNumRuedas() {
		return numRuedas;
	}

	public void setNumRuedas(int numRuedas) {
		this.numRuedas = numRuedas;
	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}
	public boolean equals(Object o) {
		if (o instanceof Camion) {
			Camion c = (Camion) o;
			if (this.getMatricula().compareTo(c.getMatricula()) == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}

