package com.mordor.mordorLloguer.model;
import java.sql.Date;

public class Furgoneta extends Vehiculo {

	protected int MMA;

	public Furgoneta(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int mMA) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		MMA = mMA;
	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}
	public boolean equals(Object o) {
		if (o instanceof Furgoneta) {
			Furgoneta c = (Furgoneta) o;
			if (this.getMatricula().compareTo(c.getMatricula()) == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
