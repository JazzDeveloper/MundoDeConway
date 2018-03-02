package com.dominio.juego_vida_conway.jugar;

public class Delta{
	private final int dx;
	private final int dy;
	
	public Delta(final int dx, final int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int dx() {
		return dx;
	}

	public int dy() {
		return dy;
	}
	
}
