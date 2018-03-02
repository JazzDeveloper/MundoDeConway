package com.dominio.juego_vida_conway.utilidades;

import java.util.List;

import com.dominio.juego_vida_conway.Mundo;
import com.dominio.juego_vida_conway.Delta;
import com.dominio.juego_vida_conway.Mundo.C�lula;

public final class Matriz {
	
	private static final int CONSTANTE_ASCII = 48;
	private static final char CAR�CTER_C�LULA_VIVA = '*';

	private Matriz(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCar�cteresAArregloInt(final String string){
		int[] arregloInt = new int[3];	
		for(int car�cter = 0; car�cter < 3; car�cter++){
			arregloInt[car�cter] =  string.charAt(car�cter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
	
	public static Mundo llenarMundoConC�lulas(List<String> list, Mundo mundo){	
		for(int l�nea = 1; l�nea < list.size() ; ++l�nea){
			for (int columna = 0; columna < list.get(l�nea).length() ; ++columna) {
				if(list.get(l�nea).charAt(columna) == CAR�CTER_C�LULA_VIVA) mundo.conC�lulaVivaEn(columna , l�nea - 1);
			}
		}		
		return mundo;	
	}
	
	public static C�lula[][] llenarConEstado(final C�lula[][] c�lulas, final C�lula estadoC�lulas){
		for (int columna = 0; columna < c�lulas.length ; ++columna) {
			for (int fila = 0; fila < c�lulas[columna].length ; ++fila) {
				c�lulas[columna][fila] = estadoC�lulas; 
			}		
		}
		return c�lulas; 
	}
	
	public static C�lula[][] copiar(final C�lula[][] destino, final C�lula[][] fuente, C�lula[][] referencia, Delta deltaDestino, Delta deltaFuente){	
		for (int fila = 0; fila < referencia[0].length; ++fila) {
			for (int columna = 0; columna < referencia.length ; ++columna) {
				destino[columna + deltaDestino.dx()][fila + deltaDestino.dy()] = fuente[columna + deltaFuente.dx()][fila + deltaFuente.dy()];	
			}
		}
		return destino;
	}
	
}

