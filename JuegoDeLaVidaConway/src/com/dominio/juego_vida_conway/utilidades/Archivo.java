package com.dominio.juego_vida_conway.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dominio.juego_vida_conway.C�lula;
import com.dominio.juego_vida_conway.Mundo;

public final class Archivo {
	
	public static final String RUTA_DE_ARCHIVO_INICIO_JUEGO = "resources/iniciar_juego.txt";
	private static final char CAR�CTER_C�LULA_VIVA = '*';
	
	private Archivo(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static boolean existe(final File archivo){
		return archivo.exists() || archivo.isFile();
	}
	
	public static List<String> leer(final File archivo){
		try{			
			return new ArrayList<String>(leerPorL�neas(archivo));
		} catch(IOException ex){
			return Collections.emptyList();
		}		
	}
	
	private static List<String> leerPorL�neas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
	
	public static int[] leerConstantesDelJuego(final File archivoInicioJuego){
		String constantesJuego = leerPrimeraL�nea(archivoInicioJuego);
		if(constantesJuego.length() != 3) throw new IllegalArgumentException("El archivo de inicio de juego debe tener en la primra l�nea tres n�meros, m , n , g");		
		return Operaciones.cadenaDeCar�cteresAArregloInt(leerPrimeraL�nea(archivoInicioJuego));
	}
	
	public static String leerPrimeraL�nea(final File archivoInicioJuego){
		if(!Archivo.existe(archivoInicioJuego)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista");		
		return Archivo.leer(archivoInicioJuego).get(0);
	}
	
	public  static Mundo leerMundo(final File archivoInicioJuego){// verificar dimensiones con la entrada
		if(!Archivo.existe(archivoInicioJuego)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista");		
		List<String> listaDatosInicioJuego = Archivo.leer(archivoInicioJuego);
		
		int m = leerConstantesDelJuego(archivoInicioJuego)[0];
		int n = leerConstantesDelJuego(archivoInicioJuego)[1];
		//TODO: comprobaci�n de datos if(listaDatosInicioJuego.size() == n && listaDatosInicioJuego.get(1).length()== m){				
		
		return llenarMundoConL�neas(listaDatosInicioJuego , Mundo.de(m, n));
	}
			
	private static Mundo llenarMundoConL�neas(List<String> list, Mundo mundo){	
		for(int l�nea = 1; l�nea < list.size() ; ++l�nea){
			for (int columna = 0; columna < list.get(l�nea).length() ; ++columna) {
				if(list.get(l�nea).charAt(columna) == CAR�CTER_C�LULA_VIVA) mundo.conC�lulaVivaEn(columna , l�nea - 1);
			}
		}		
		return mundo;	
	}
		
}
