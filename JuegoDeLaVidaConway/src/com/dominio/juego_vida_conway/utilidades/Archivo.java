package com.dominio.juego_vida_conway.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dominio.juego_vida_conway.jugar.Mundo;

public final class Archivo {
	
	public static final String RUTA_DE_ARCHIVO_INICIO_JUEGO = "resources/iniciar_juego.txt";
	private static final int M�NIMO_N�MERO_DE_FILAS_ARCHIVO = 4;
	
	private Archivo(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public  static Mundo leerMundo(final File archivoInicioJuego){// verificar dimensiones con la entrada
		if(!Archivo.existe(archivoInicioJuego) ) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga el formato adecuado");			
		
		final List<String> datosInicioJuego = Archivo.leer(archivoInicioJuego);
		final int m = leerN�meroColumnas(archivoInicioJuego);
		final int n = leerN�meroFilas(archivoInicioJuego);
		
		if(!esFormatoAdecuado(archivoInicioJuego, datosInicioJuego, m, n)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga el formato adecuado");		
		
		return Matriz.llenarMundoConC�lulas(datosInicioJuego , Mundo.de(m, n));
	}
	
	private static boolean esFormatoAdecuado(final File archivoInicioJuego, final List<String> datosInicioJuego, final int m, final int n){
		return datosInicioJuego.size() >= M�NIMO_N�MERO_DE_FILAS_ARCHIVO ^ datosInicioJuego.size() - 1 == n ^ datosInicioJuego.get(1).length() == m;
	}
	
	/**
	 * 
	 * @return lee el mundo desde el archivo de inicio de juego en la ubicaci�n por defecto(resources/iniciar_juego.txt)
	 */
	public  static Mundo leerMundo(){
		File archivoInicioJuego = new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO);	
		return leerMundo(archivoInicioJuego);
	}
	
	public static int leerN�meroGeneraciones(final File archivoInicioJuego){
		return leerConstantesDelJuego(archivoInicioJuego)[2];
	}
	
	/**
	 * 
	 * @return lee el n�mero de generaciones desde el archivo de inicio de juego en la ubicaci�n por defecto(resources/iniciar_juego.txt)
	 */
	public static int leerN�meroGeneraciones(){
		File archivoInicioJuego = new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO);	
		return leerConstantesDelJuego(archivoInicioJuego)[2];
	}
	
	private static boolean existe(final File archivo){
		return archivo.exists() || archivo.isFile();
	}
	
	private static List<String> leer(final File archivo){//quienes lo llaman deben verificar tama�o lista
		try{			
			return new ArrayList<String>(leerPorL�neas(archivo));
		} catch(IOException ex){
			return Collections.emptyList();
		}		
	}
	
	private static int leerN�meroColumnas(final File archivoInicioJuego){
		return leerConstantesDelJuego(archivoInicioJuego)[0];
	}
	
	private static int leerN�meroFilas(final File archivoInicioJuego){
		return leerConstantesDelJuego(archivoInicioJuego)[1];
	}
	
	private static List<String> leerPorL�neas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
	
	private static int[] leerConstantesDelJuego(final File archivoInicioJuego){
		return Matriz.cadenaDeCar�cteresAArregloInt(leerPrimeraL�nea(archivoInicioJuego));
	}
	
	private static String leerPrimeraL�nea(final File archivoInicioJuego){
		return Archivo.leer(archivoInicioJuego).get(0);
	}
				
}
