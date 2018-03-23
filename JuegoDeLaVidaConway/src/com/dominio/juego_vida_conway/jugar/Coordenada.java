package com.dominio.juego_vida_conway.jugar;

public final class Coordenada {
	private final int columna;
	private final int fila;
	
	public Coordenada(final int columna, final int fila) {
		this.columna = columna;
		this.fila = fila; 
	}

	public int columna() {
		return this.columna;
	}

	public int fila() {
		return this.fila;
	}

}