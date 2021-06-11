package com.mordor.mordorlloguer;

import java.awt.EventQueue;

import com.alee.laf.WebLookAndFeel;
import com.mordor.mordorLloguer.controlador.ControladorPincipal;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.MyOracleDataBase;
import com.mordor.mordorLloguer.vistas.VistaPrincipal;

public class App {

	public static void main(String[] args) {
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						WebLookAndFeel.install();
						
						VistaPrincipal frame = new VistaPrincipal();
						AlmacenDatosDB model = new MyOracleDataBase();
						ControladorPincipal c = new ControladorPincipal(frame,model);
						
						c.go();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

}
