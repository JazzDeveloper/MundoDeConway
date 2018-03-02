package com.dominio.juego_vida_conway.utilidades;

import java.util.List;

import com.dominio.juego_vida_conway.Mundo;
import com.dominio.juego_vida_conway.Delta;
import com.dominio.juego_vida_conway.Mundo.Célula;

public final class Matriz {
	
	private static final int CONSTANTE_ASCII = 48;
	private static final char CARÁCTER_CÉLULA_VIVA = '*';

	private Matriz(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCarácteresAArregloInt(final String string){
		int[] arregloInt = new int[3];	
		for(int carácter = 0; carácter < 3; carácter++){
			arregloInt[carácter] =  string.charAt(carácter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
	
	public static Mundo llenarMundoConCélulas(List<String> list, Mundo mundo){	
		for(int línea = 1; línea < list.size() ; ++línea){
			for (int columna = 0; columna < list.get(línea).length() ; ++columna) {
				if(list.get(línea).charAt(columna) == CARÁCTER_CÉLULA_VIVA) mundo.conCélulaVivaEn(columna , línea - 1);
			}
		}		
		return mundo;	
	}
	
	public static Célula[][] llenarConEstado(final Célula[][] células, final Célula estadoCélulas){
		for (int columna = 0; columna < células.length ; ++columna) {
			for (int fila = 0; fila < células[columna].length ; ++fila) {
				células[columna][fila] = estadoCélulas; 
			}		
		}
		return células; 
	}
	
	public static Célula[][] copiar(final Célula[][] destino, final Célula[][] fuente, Célula[][] referencia, Delta deltaDestino, Delta deltaFuente){	
		for (int fila = 0; fila < referencia[0].length; ++fila) {
			for (int columna = 0; columna < referencia.length ; ++columna) {
				destino[columna + deltaDestino.dx()][fila + deltaDestino.dy()] = fuente[columna + deltaFuente.dx()][fila + deltaFuente.dy()];	
			}
		}
		return destino;
	}
	
}

