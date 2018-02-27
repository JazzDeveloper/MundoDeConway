package com.dominio.juego_vida_conway.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dominio.juego_vida_conway.Célula;
import com.dominio.juego_vida_conway.Mundo;

public final class Archivo {
	
	public static final String RUTA_DE_ARCHIVO_INICIO_JUEGO = "resources/iniciar_juego.txt";
	private static final char CARÁCTER_CÉLULA_VIVA = '*';
	
	private Archivo(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static boolean existe(final File archivo){
		return archivo.exists() || archivo.isFile();
	}
	
	public static List<String> leer(final File archivo){
		try{			
			return new ArrayList<String>(leerPorLíneas(archivo));
		} catch(IOException ex){
			return Collections.emptyList();
		}		
	}
	
	private static List<String> leerPorLíneas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
	
	public static int[] leerConstantesDelJuego(final File archivoInicioJuego){
		String constantesJuego = leerPrimeraLínea(archivoInicioJuego);
		if(constantesJuego.length() != 3) throw new IllegalArgumentException("El archivo de inicio de juego debe tener en la primra línea tres números, m , n , g");		
		return Operaciones.cadenaDeCarácteresAArregloInt(leerPrimeraLínea(archivoInicioJuego));
	}
	
	public static String leerPrimeraLínea(final File archivoInicioJuego){
		if(!Archivo.existe(archivoInicioJuego)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista");		
		return Archivo.leer(archivoInicioJuego).get(0);
	}
	
	public  static Mundo leerMundo(final File archivoInicioJuego){// verificar dimensiones con la entrada
		if(!Archivo.existe(archivoInicioJuego)) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista");		
		List<String> listaDatosInicioJuego = Archivo.leer(archivoInicioJuego);
		
		int m = leerConstantesDelJuego(archivoInicioJuego)[0];
		int n = leerConstantesDelJuego(archivoInicioJuego)[1];
		//TODO: comprobación de datos if(listaDatosInicioJuego.size() == n && listaDatosInicioJuego.get(1).length()== m){				
		
		return llenarMundoConLíneas(listaDatosInicioJuego , Mundo.de(m, n));
	}
			
	private static Mundo llenarMundoConLíneas(List<String> list, Mundo mundo){	
		for(int línea = 1; línea < list.size() ; ++línea){
			for (int columna = 0; columna < list.get(línea).length() ; ++columna) {
				if(list.get(línea).charAt(columna) == CARÁCTER_CÉLULA_VIVA) mundo.conCélulaVivaEn(columna , línea - 1);
			}
		}		
		return mundo;	
	}
		
}
