package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public final class Juego{
	private final Mundo mundo; 
	private final int generaciones;
	private final Mundo[] mundos;
	
	public Juego(final Mundo mundo, final int generaciones) {
		if(generaciones < 0 ) throw new IllegalArgumentException("El número de generaciones debe ser positivo");
		this.generaciones = generaciones;
		this.mundo = Objects.requireNonNull(mundo);	
		mundos = new Mundo[this.generaciones + 1];//el mundo inicial y los generados
		mundos[0] =  mundo.copia();
		
	}

	public Juego(final File archivoDatosJuego) {
		this(new Mundo.Builder(archivoDatosJuego).build(), Archivo.leerNúmeroGeneraciones(archivoDatosJuego));
	}
	
	public Juego() {
		this(new Mundo.Builder().build(), Archivo.leerNúmeroGeneraciones(new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO)));
	}
	
	public void jugar(){
		//mundos[0] =  mundo.copia();
		for(int generaciones = 1; generaciones <= this.generaciones; ++generaciones){
			mundos[generaciones] = mundo.nuevaGeneración();						
		}			
	}
	
	public String verÚltimaGeneración(){
		return mundos[this.generaciones].toString();
	}
	
	public String verGeneración(int generación){
		if(generación < 0 || generación > generaciones) throw new IllegalArgumentException("El número de generaciones debe ser positivo");
		return mundos[generación].toString();
	}
	
	@Override
	public String toString() {
		return Arrays.toString(mundos);

	}
		
}
