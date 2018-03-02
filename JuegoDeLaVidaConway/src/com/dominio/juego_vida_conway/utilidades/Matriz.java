package com.dominio.juego_vida_conway.utilidades;

import java.util.List;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Delta;
import com.dominio.juego_vida_conway.jugar.Mundo;
import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

public final class Matriz {
	
	private static final int NÚMERO_CONSTANTES_JUEGO = 3;//m,n,g
	private static final int CONSTANTE_ASCII = 48;
	private static final char CARÁCTER_CÉLULA_VIVA = '*';

	private Matriz(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCarácteresAArregloInt(final String string){
		int[] arregloInt = new int[3];	
		for(int carácter = 0; carácter < NÚMERO_CONSTANTES_JUEGO; carácter++){
			arregloInt[carácter] =  string.charAt(carácter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
	
	public static Mundo llenarMundoConCélulas(List<String> list, Mundo mundo){	
		for(int línea = 1; línea < list.size() ; ++línea){
			for (int columna = 0; columna < list.get(línea).length() ; ++columna) {
				if(list.get(línea).charAt(columna) == CARÁCTER_CÉLULA_VIVA) mundo.conCélulaVivaEn(new Coordenada(columna, línea - 1));
				
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
	
	public static int calcularVecinasVivas(Célula[][] generaciónActual, int fila, int columna) {
		int númeroVecinasVivas = 0;				
		final Delta máscara[] = {new Delta(-1,-1), new Delta(-1,0), new Delta(-1,1), new Delta(0,-1),new Delta(0,1),new Delta(1,-1),new Delta(1,0),new Delta(1,1)};		
		for (int i = 0; i < 8; ++i) {
			númeroVecinasVivas += generaciónActual[columna - máscara[i].dx()][fila - máscara[i].dy()].ordinal();
		}		
		return númeroVecinasVivas;
	}
	
}

