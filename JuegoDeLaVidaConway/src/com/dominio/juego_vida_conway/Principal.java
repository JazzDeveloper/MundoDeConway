package com.dominio.juego_vida_conway;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Principal {
    
	public static void main(String[] args) {
		
		
		Mundo mundo = Mundo.de(5, 5)
					.conCélulaVivaEn(1 , 2)
						.conCélulaVivaEn(2, 2)
							.conCélulaVivaEn(3, 2);
		//
		Juego juego = new Juego(mundo, 3);
		juego.iniciar();
		/*
		Juego juego = new Juego();
		juego.iniciar();*/
		
		
		//System.out.println(juego);
			
		Juego juego2 = new Juego();
		juego2.iniciar();

		//System.out.println(juego2);
		

		
		
		//System.out.println(juego2);
		/*
		Juego juego = new Juego.Mundo(archivo).build();

		Juego juego2 = new Juego.Mundo(5,5)
				.conCélulaVivaEn(1 , 2)
					.conCélulaVivaEn(2, 2)
						.conCélulaVivaEn(3, 2);
						.build();
		*/
		
		/*
		File archivoInicioJuego = new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO);	
		Juego juego2 = new Juego(archivoInicioJuego);
		juego2.iniciar();*/
		
		//System.out.println(juego2);
		
		
		
	}
		
}






