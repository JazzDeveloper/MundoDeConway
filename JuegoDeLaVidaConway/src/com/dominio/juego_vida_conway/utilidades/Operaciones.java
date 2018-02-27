package com.dominio.juego_vida_conway.utilidades;

public final class Operaciones {
	
	private static final int CONSTANTE_ASCII = 48;

	private Operaciones(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCar�cteresAArregloInt(final String string){
		int[] arregloInt = new int[3];	
		for(int car�cter = 0; car�cter < 3; car�cter++){
			arregloInt[car�cter] =  string.charAt(car�cter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
}
