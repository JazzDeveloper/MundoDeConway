package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Juego{
	private final Mundo mundo; 
	private final int generaciones;
	private final String[] mundos;

	public Juego(final Mundo mundo, final int generaciones) {
		if(generaciones < 0 ) throw new IllegalArgumentException("El n�mero de generaciones debe ser positivo");
		this.generaciones = generaciones;
		this.mundo = Objects.requireNonNull(mundo);	
		mundos = new String[this.generaciones + 1];//el mundo inicial y los generados
		mundos[0] = mundo.toString();					
	}

	public Juego(final File archivoDatosJuego) {
		this(Mundo.desdeArchivo(archivoDatosJuego), Archivo.leerN�meroGeneraciones(archivoDatosJuego));
	}
	
	/***
	 * Crea juego con archivo de inicio en ubicacion por defecto (resources/iniciar_juego.txt)
	 */
	public Juego() {
		this(Mundo.desdeArchivo(), Archivo.leerN�meroGeneraciones(new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO)));
	}
	//fin constructores
	
	public void jugar(){		
		for(int generaciones = 1; generaciones <= this.generaciones; ++generaciones){
			mundos[generaciones] = mundo.nuevaGeneraci�n();						
		}		
	}
	
	public String ver�ltimaGeneraci�n(){
		return mundos[this.generaciones].toString();
	}
	
	public String verGeneraci�n(int generaci�n){
		if(generaci�n < 0 || generaci�n > generaciones) throw new IllegalArgumentException("El n�mero de generaciones debe ser positivo");
		return mundos[generaci�n].toString();
	}
	
	@Override
	public String toString() {
		return Arrays.toString(mundos);		
	}
		
}
