package com.dominio.juego_vida_conway;

import java.io.File;
import java.util.List;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Mundo{
	
	private Célula[][] células;
	
	private Mundo(final Célula[][] mundoDeCélulas){		
		this.células = Objects.requireNonNull(mundoDeCélulas);		
	}
	
	/**
	 * Por defecto el mundo de Conway se llena con células muertas y tiene un tamaño minimo de 3 x 3**/
	private Mundo(final int m, final int n){
		this(new Célula[m][n]);
		llenoDe(Célula.Muerta);
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
	
	private Mundo llenoDe(Célula estadoCélulas){
		for (int columna = 0; columna < células.length ; columna++) {
			for (int fila = 0; fila < células[columna].length ; fila++) {
				células[columna][fila] = estadoCélulas; 
			}		
		}
		return new Mundo(células); 
	} 
	
	public Mundo conCélulaVivaEn(int x, int y){
		if(x > células.length || x < 0 || y > células[0].length || y < 0) throw new IllegalArgumentException("Esta poniendo por células por fuera del mundo");
		células[x][y] = Célula.Viva;				 
		return new Mundo(células); 
	}
	
	private static final char SALTO_DE_LÍNEA = '\n';
	private static final char CARÁCTER_CÉLULA_MUERTA = '.';
	private static final char CARÁCTER_CÉLULA_VIVA = '*';
	
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < células.length ; ++j) {
			for (int i = 0; i < células[j].length ; ++i) {
				if(células[i][j] == Célula.Muerta) sb.append(CARÁCTER_CÉLULA_MUERTA);
				if(células[i][j] == Célula.Viva) sb.append(CARÁCTER_CÉLULA_VIVA);
			}
			sb.append(SALTO_DE_LÍNEA);	
		}
		return sb.toString();		
	}	
	
}