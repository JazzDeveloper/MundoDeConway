package com.dominio.juego_vida_conway.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Mundo;

public final class Archivo {
	
	public static final String RUTA_DE_ARCHIVO_INICIO_JUEGO = "resources/iniciar_juego.txt";
	private static final int M�NIMO_N�MERO_DE_FILAS_ARCHIVO = 4;
	
	private Archivo(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	 
	public static Mundo leerMundo(){
		return leerMundo(archivoInicioJuego());
	}
	
	public static int leerN�meroGeneraciones(){
		return leerConstantesDelJuego(2);
	}
	
	public static Mundo leerMundo(final File archivoInicioJuego){
		if(!Archivo.existe(archivoInicioJuego) ) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga el formato adecuado");			
		
		final int n�meroColumnas = leerN�meroColumnas(archivoInicioJuego);
		final int n�meroFilas = leerN�meroFilas(archivoInicioJuego);		
		final Mundo mundo = verificarFormato(archivoInicioJuego, n�meroColumnas, n�meroFilas);
		
		return llenarMundoConC�lulas(datosInicioJuego(archivoInicioJuego), mundo);
	}
	
	public static int leerN�meroGeneraciones(final File archivoInicioJuego){
		return leerConstantesDelJuego(2);
	}
	
	//..	
	private static Mundo llenarMundoConC�lulas(final List<String> list, final Mundo mundo){
		for(int l�nea = 1; l�nea < list.size() ; ++l�nea){
			for (int columna = 0; columna < list.get(l�nea).length() ; ++columna) {
				if(list.get(l�nea).charAt(columna) == Mundo.CAR�CTER_C�LULA_VIVA) mundo.conC�lulaVivaEn(new Coordenada(columna, l�nea - 1));
				else mundo.conC�lulaMuertaEn(new Coordenada(columna, l�nea - 1));
			}
		}		
		return mundo;	
	}
	
	private static int leerConstantesDelJuego(final int columnaConstante){
		return leerConstantesDelJuego(archivoInicioJuego())[columnaConstante];
	}
	
	private static Mundo verificarFormato(final File archivoInicioJuego, final int n�meroColumnas, final int n�meroFilas) {
		if(!esFormatoAdecuado(archivoInicioJuego, datosInicioJuego(archivoInicioJuego), n�meroColumnas, n�meroFilas)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga el formato adecuado(revisa el m�mero de filas y columnas)");		
		return Mundo.de(n�meroColumnas, n�meroFilas);
	}
	
	private static List<String> datosInicioJuego(final File archivoInicioJuego) {
		return Archivo.leer(archivoInicioJuego);
	}
	
	private static boolean esFormatoAdecuado(final File archivoInicioJuego, final List<String> datosInicioJuego, final int n�meroColumnas, final int n�meroDeFilas){
		return datosInicioJuego.size() >= M�NIMO_N�MERO_DE_FILAS_ARCHIVO ^ datosInicioJuego.size() - 1 == n�meroDeFilas ^ datosInicioJuego.get(1).length() == n�meroColumnas;
	}
		
	private static List<String> leer(final File archivo){//quienes lo llaman deben verificar tama�o lista
		try{			
			return new ArrayList<String>(leerPorL�neas(archivo));
		} catch(IOException ex){
			return Collections.emptyList();
		}		
	}
	
	private static File archivoInicioJuego(){
		return new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO);
	}
	
	private static boolean existe(final File archivo){
		return archivo.exists() || archivo.isFile();
	}
	
	private static int leerN�meroColumnas(final File archivoInicioJuego){
		return leerConstantesDelJuego(0);
	}
	
	private static int leerN�meroFilas(final File archivoInicioJuego){
		return leerConstantesDelJuego(1);
	}
	
	private static List<String> leerPorL�neas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
	
	private static int[] leerConstantesDelJuego(final File archivoInicioJuego){
		return Operaciones.cadenaDeCar�cteresAArregloInt(leerPrimeraL�nea(archivoInicioJuego));
	}
	
	private static String leerPrimeraL�nea(final File archivoInicioJuego){
		return Archivo.leer(archivoInicioJuego).get(0);
	}
				
}
