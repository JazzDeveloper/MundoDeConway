package com.dominio.juego_vida_conway.utilidades;

public final class Operaciones {
	
	private static final int CONSTANTE_ASCII = 48;

	private Operaciones(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCarácteresAArregloInt(final String string){
		int[] arregloInt = new int[3];	
		for(int carácter = 0; carácter < 3; carácter++){
			arregloInt[carácter] =  string.charAt(carácter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
}
