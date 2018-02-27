package com.dominio.juego_vida_conway;

import java.io.File;
import java.util.List;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Mundo{
	
	private C�lula[][] c�lulas;
	
	private Mundo(final C�lula[][] mundoDeC�lulas){		
		this.c�lulas = Objects.requireNonNull(mundoDeC�lulas);		
	}
	
	/**
	 * Por defecto el mundo de Conway se llena con c�lulas muertas y tiene un tama�o minimo de 3 x 3**/
	private Mundo(final int m, final int n){
		this(new C�lula[m][n]);
		llenoDe(C�lula.Muerta);
	}
	
	//fabricas estaticas	
	public static Mundo de(final int m, final int n){
		if(m < 3 ||  n < 3) throw new IllegalArgumentException("El mundo de Conway debe ser al menos de 3 x 3");
		return new Mundo(m , n);
	}

	public static Mundo desdeArchivo(File archivoInicioJuego){
		return Archivo.leerMundo(archivoInicioJuego);		
	}
	//
	
	private Mundo llenoDe(C�lula estadoC�lulas){
		for (int columna = 0; columna < c�lulas.length ; columna++) {
			for (int fila = 0; fila < c�lulas[columna].length ; fila++) {
				c�lulas[columna][fila] = estadoC�lulas; 
			}		
		}
		return new Mundo(c�lulas); 
	} 
	
	public Mundo conC�lulaVivaEn(int x, int y){
		if(x > c�lulas.length || x < 0 || y > c�lulas[0].length || y < 0) throw new IllegalArgumentException("Esta poniendo por c�lulas por fuera del mundo");
		c�lulas[x][y] = C�lula.Viva;				 
		return new Mundo(c�lulas); 
	}
	
	private static final char SALTO_DE_L�NEA = '\n';
	private static final char CAR�CTER_C�LULA_MUERTA = '.';
	private static final char CAR�CTER_C�LULA_VIVA = '*';
	
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < c�lulas.length ; ++j) {
			for (int i = 0; i < c�lulas[j].length ; ++i) {
				if(c�lulas[i][j] == C�lula.Muerta) sb.append(CAR�CTER_C�LULA_MUERTA);
				if(c�lulas[i][j] == C�lula.Viva) sb.append(CAR�CTER_C�LULA_VIVA);
			}
			sb.append(SALTO_DE_L�NEA);	
		}
		return sb.toString();		
	}	
	
}