package com.dominio.juego_vida_conway;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Juego{
	private final Mundo mundo; 
	private final int generaciones;
	Mundo[] mundos;
	
	public Juego(final Mundo mundo, final int generaciones) {
		if(generaciones < 0 ) throw new IllegalArgumentException("Las generaciones no pueden tener valores negativos");
		this.generaciones = generaciones;
		this.mundo = Objects.requireNonNull(mundo);
	}
	
	public Juego(final File archivoDatosJuego) {
		this(Mundo.desdeArchivo(archivoDatosJuego), Archivo.leerNúmeroGeneraciones(archivoDatosJuego));
	}
	
	/***
	 * Crea juego con archivo de inicio en ubicacion por defecto
	 */
	public Juego() {
		this(Mundo.desdeArchivo(), Archivo.leerNúmeroGeneraciones(new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO)));
	}
	
	public void iniciar(){
		System.out.println(mundo);
		//mundos = new Mundo[this.generaciones];
		//mundos[0] = mundo;
		for(int generaciones = 1; generaciones <= this.generaciones; ++generaciones){
			//mundos[generaciones] = Mundo.;
			mundo.nuevaGeneracion();	
			System.out.println(mundo);					
		}
	}

	@Override
	public String toString() {
		//return Arrays.toString(mundos);
		return mundo.toString();
	}
		
}
