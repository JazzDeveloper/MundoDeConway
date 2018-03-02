package com.dominio.juego_vida_conway;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Juego;
import com.dominio.juego_vida_conway.jugar.Mundo;
import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Principal {
    
	public static void main(String[] args) {

		Mundo mundo = Mundo.de(5, 5)
					.conCélulaVivaEn(new Coordenada(1,2))
						.conCélulaVivaEn(new Coordenada(2,2))
							.conCélulaVivaEn(new Coordenada(3,2));
		
		Juego juego = new Juego(mundo, 3);
		juego.iniciar();
		
		System.out.println("-------------------");
		
		//System.out.println(juego);
		
		Juego juego2 = new Juego();
		juego2.iniciar();

		System.out.println(juego2);
		
	}
		
}






