package com.dominio.juego_vida_conway;

import java.io.File;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Juego{
	private final Mundo mundo; 
	private final int generaciones;
	
	public Juego(final Mundo mundo, final int generaciones) {
		this.mundo = mundo;
		this.generaciones = generaciones;
	}
	
	public Juego(final File archivoDatosJuego) {
		this(Mundo.desdeArchivo(archivoDatosJuego), Archivo.leerConstantesDelJuego(archivoDatosJuego)[2]);
	}
	
	public void iniciar(){
		//mutar mundo g veces
		
	}
	
}
