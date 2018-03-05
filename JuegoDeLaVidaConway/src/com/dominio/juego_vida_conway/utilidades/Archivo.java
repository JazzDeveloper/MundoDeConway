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
	private static final int MÍNIMO_NÚMERO_DE_FILAS_ARCHIVO = 4;
	
	private Archivo(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	 
	public static Mundo leerMundo(){
		return leerMundo(archivoInicioJuego());
	}
	
	public static int leerNúmeroGeneraciones(){
		return leerConstantesDelJuego(2);
	}
	
	public static Mundo leerMundo(final File archivoInicioJuego){
		if(!Archivo.existe(archivoInicioJuego) ) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga el formato adecuado");			
		
		final int númeroColumnas = leerNúmeroColumnas(archivoInicioJuego);
		final int númeroFilas = leerNúmeroFilas(archivoInicioJuego);		
		final Mundo mundo = verificarFormato(archivoInicioJuego, númeroColumnas, númeroFilas);
		
		return llenarMundoConCélulas(datosInicioJuego(archivoInicioJuego), mundo);
	}
	
	public static int leerNúmeroGeneraciones(final File archivoInicioJuego){
		return leerConstantesDelJuego(2);
	}
	
	//..	
	private static Mundo llenarMundoConCélulas(final List<String> list, final Mundo mundo){
		for(int línea = 1; línea < list.size() ; ++línea){
			for (int columna = 0; columna < list.get(línea).length() ; ++columna) {
				if(list.get(línea).charAt(columna) == Mundo.CARÁCTER_CÉLULA_VIVA) mundo.conCélulaVivaEn(new Coordenada(columna, línea - 1));
				else mundo.conCélulaMuertaEn(new Coordenada(columna, línea - 1));
			}
		}		
		return mundo;	
	}
	
	private static int leerConstantesDelJuego(final int columnaConstante){
		return leerConstantesDelJuego(archivoInicioJuego())[columnaConstante];
	}
	
	private static Mundo verificarFormato(final File archivoInicioJuego, final int númeroColumnas, final int númeroFilas) {
		if(!esFormatoAdecuado(archivoInicioJuego, datosInicioJuego(archivoInicioJuego), númeroColumnas, númeroFilas)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga el formato adecuado(revisa el múmero de filas y columnas)");		
		return Mundo.de(númeroColumnas, númeroFilas);
	}
	
	private static List<String> datosInicioJuego(final File archivoInicioJuego) {
		return Archivo.leer(archivoInicioJuego);
	}
	
	private static boolean esFormatoAdecuado(final File archivoInicioJuego, final List<String> datosInicioJuego, final int númeroColumnas, final int númeroDeFilas){
		return datosInicioJuego.size() >= MÍNIMO_NÚMERO_DE_FILAS_ARCHIVO ^ datosInicioJuego.size() - 1 == númeroDeFilas ^ datosInicioJuego.get(1).length() == númeroColumnas;
	}
		
	private static List<String> leer(final File archivo){//quienes lo llaman deben verificar tamaño lista
		try{			
			return new ArrayList<String>(leerPorLíneas(archivo));
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
	
	private static int leerNúmeroColumnas(final File archivoInicioJuego){
		return leerConstantesDelJuego(0);
	}
	
	private static int leerNúmeroFilas(final File archivoInicioJuego){
		return leerConstantesDelJuego(1);
	}
	
	private static List<String> leerPorLíneas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
	
	private static int[] leerConstantesDelJuego(final File archivoInicioJuego){
		return Operaciones.cadenaDeCarácteresAArregloInt(leerPrimeraLínea(archivoInicioJuego));
	}
	
	private static String leerPrimeraLínea(final File archivoInicioJuego){
		return Archivo.leer(archivoInicioJuego).get(0);
	}
				
}
