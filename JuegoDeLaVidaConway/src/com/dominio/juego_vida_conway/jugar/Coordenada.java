package com.dominio.juego_vida_conway.jugar;

public class Coordenada {
	private final int x;
	private final int y;
	
	public Coordenada(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

}
