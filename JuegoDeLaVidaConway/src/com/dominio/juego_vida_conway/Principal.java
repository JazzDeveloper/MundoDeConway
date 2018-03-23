package com.dominio.juego_vida_conway;

import java.util.Arrays;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Juego;
import com.dominio.juego_vida_conway.jugar.Mundo;
import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

public class Principal {
	
	public static void main(String[] args) {
		
		int generaciones = 3;
		
		/*Mundo mundo = Mundo.de(5, 5)
						.conCélulaVivaEn(new Coordenada(1,2))
							.conCélulaVivaEn(new Coordenada(2,2))
								.conCélulaVivaEn(new Coordenada(3,2));

		Juego juego = new Juego(mundo, generaciones);
		juego.jugar();
		
		System.out.println(juego);
		
		
		Mundo mundo = new Mundo.Builder(5 , 5)
							.conCélulaVivaEn(new Coordenada(1,2))
								.conCélulaVivaEn(new Coordenada(2,2))
									.conCélulaVivaEn(new Coordenada(3,2)).build();
		Juego juego = new Juego(mundo, generaciones);
		juego.jugar();
		
		System.out.println(juego);*/
		
		/*
		Mundo mundo2 = new Mundo.Builder().build();
		Juego juego2 = new Juego(mundo2, generaciones);
		juego2.jugar();*/

		//System.out.println(juego2);
		
		
		/*
		Célula[][] célulasMundoActual = {{Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA}, 
				   						{Célula.MUERTA, Célula.VIVA,   Célula.VIVA, 	 Célula.MUERTA},
				   						{Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA},
				   						{Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA}};
				
		Célula[][] célulasMundoSiguienteGeneración =  {{Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA}, 
				   {Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA},
				   {Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA}};												

		//Mundo mundoActual = new Mundo.Builder(célulasMundoActual).build();
		
		Mundo mundo = new Mundo.Builder(4,3)
				.conCélulaVivaEn(new Coordenada(1,1))
					.conCélulaVivaEn(new Coordenada(2,1)).build();*/

		//System.out.println(mundoActual);
		//Mundo mundoSiguienteGeneración = new Mundo.Builder(célulasMundoSiguienteGeneración).build();
		
		/*
		Juego juego2 = new Juego(mundo, 3);
		juego2.jugar();
		System.out.println(juego2);
		System.out.println("----------------------");
		*/
		/*
		System.out.println("----------------------");
		Mundo mundo3 = new Mundo.Builder(4,4)
				.conCélulaVivaEn(new Coordenada(1,1))
					.conCélulaVivaEn(new Coordenada(2,1)).build();
		

		Célula[][] célulasMundoActual = {{Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA}, 
										 {Célula.MUERTA, Célula.VIVA,   Célula.MUERTA, Célula.MUERTA},
										 {Célula.MUERTA, Célula.VIVA,   Célula.MUERTA, Célula.MUERTA},
										 {Célula.MUERTA, Célula.MUERTA, Célula.MUERTA, Célula.MUERTA}};
		
		System.out.println(mundo3);		
		System.out.println("----------------------------------------");
		
		System.out.println(new Mundo.Builder(célulasMundoActual).build());
		
		System.out.println("----------------------------------------");*/

		//Mundo mundoActual = new Mundo.Builder(3, 3)
		//		.llenoDe(Célula.VIVA).build();							
		
		/*Mundo mundoActual = new Mundo.Builder(5, 5)
									.conCélulaVivaEn(new Coordenada(1, 1)).conCélulaVivaEn(new Coordenada(2, 1)).conCélulaVivaEn(new Coordenada(3, 1))
									.conCélulaVivaEn(new Coordenada(1, 2)).conCélulaVivaEn(new Coordenada(2, 2)).conCélulaVivaEn(new Coordenada(3, 2))
									.conCélulaVivaEn(new Coordenada(1, 3)).conCélulaVivaEn(new Coordenada(2, 3)).conCélulaVivaEn(new Coordenada(3, 3))
									.build();	*/
		
		/*Mundo mundoActual = new Mundo.Builder(6, 6)
				 .conCélulaVivaEn(new Coordenada(1,1))
				 									.conCélulaVivaEn(new Coordenada(2,2))
					.conCélulaVivaEn(new Coordenada(0,3)).conCélulaVivaEn(new Coordenada(1,3)).conCélulaVivaEn(new Coordenada(2,3))
					.build();*/
		
		Mundo mundoActual = new Mundo.Builder(3, 3)
				.conCélulaVivaEn(new Coordenada(0, 0)).conCélulaVivaEn(new Coordenada(1, 0)).conCélulaVivaEn(new Coordenada(2, 0))
				.conCélulaVivaEn(new Coordenada(0, 1)).conCélulaVivaEn(new Coordenada(1, 1)).conCélulaVivaEn(new Coordenada(2, 1))
				.conCélulaVivaEn(new Coordenada(0, 2)).conCélulaVivaEn(new Coordenada(1, 2)).conCélulaVivaEn(new Coordenada(2, 2))
				
				.build();		
							
		System.out.println(mundoActual);		
		
		
		Juego juego3 = new Juego(mundoActual, 1);
		juego3.jugar();
		System.out.println(juego3);
		
		
	}
		
}















