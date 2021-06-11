package com.mordor.mordorLloguer.test;

import com.mordor.mordorLloguer.controlador.ControladorViEmpleados;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.MyOracleDataBase;

public class Test {

	public static void main(String[] args) {
		
		AlmacenDatosDB model = new MyOracleDataBase();
		//ControladorViEmpleados control = new ControladorViEmpleados(model);
		try {
			System.out.println(model.athenticate("76543210Y", "1111"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(model);

		
	}

}
