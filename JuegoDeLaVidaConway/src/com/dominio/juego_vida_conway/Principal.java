package com.dominio.juego_vida_conway;

import java.util.Arrays;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Juego;
import com.dominio.juego_vida_conway.jugar.Mundo;
import com.dominio.juego_vida_conway.jugar.Mundo.C�lula;

public class Principal {
	
	public static void main(String[] args) {
		
		int generaciones = 3;
		
		/*Mundo mundo = Mundo.de(5, 5)
						.conC�lulaVivaEn(new Coordenada(1,2))
							.conC�lulaVivaEn(new Coordenada(2,2))
								.conC�lulaVivaEn(new Coordenada(3,2));

		Juego juego = new Juego(mundo, generaciones);
		juego.jugar();
		
		System.out.println(juego);
		
		
		Mundo mundo = new Mundo.Builder(5 , 5)
							.conC�lulaVivaEn(new Coordenada(1,2))
								.conC�lulaVivaEn(new Coordenada(2,2))
									.conC�lulaVivaEn(new Coordenada(3,2)).build();
		Juego juego = new Juego(mundo, generaciones);
		juego.jugar();
		
		System.out.println(juego);*/
		
		/*
		Mundo mundo2 = new Mundo.Builder().build();
		Juego juego2 = new Juego(mundo2, generaciones);
		juego2.jugar();*/

		//System.out.println(juego2);
		
		
		/*
		C�lula[][] c�lulasMundoActual = {{C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA}, 
				   						{C�lula.MUERTA, C�lula.VIVA,   C�lula.VIVA, 	 C�lula.MUERTA},
				   						{C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA},
				   						{C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA}};
				
		C�lula[][] c�lulasMundoSiguienteGeneraci�n =  {{C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA}, 
				   {C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA},
				   {C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA}};												

		//Mundo mundoActual = new Mundo.Builder(c�lulasMundoActual).build();
		
		Mundo mundo = new Mundo.Builder(4,3)
				.conC�lulaVivaEn(new Coordenada(1,1))
					.conC�lulaVivaEn(new Coordenada(2,1)).build();*/

		//System.out.println(mundoActual);
		//Mundo mundoSiguienteGeneraci�n = new Mundo.Builder(c�lulasMundoSiguienteGeneraci�n).build();
		
		/*
		Juego juego2 = new Juego(mundo, 3);
		juego2.jugar();
		System.out.println(juego2);
		System.out.println("----------------------");
		*/
		/*
		System.out.println("----------------------");
		Mundo mundo3 = new Mundo.Builder(4,4)
				.conC�lulaVivaEn(new Coordenada(1,1))
					.conC�lulaVivaEn(new Coordenada(2,1)).build();
		

		C�lula[][] c�lulasMundoActual = {{C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA}, 
										 {C�lula.MUERTA, C�lula.VIVA,   C�lula.MUERTA, C�lula.MUERTA},
										 {C�lula.MUERTA, C�lula.VIVA,   C�lula.MUERTA, C�lula.MUERTA},
										 {C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA, C�lula.MUERTA}};
		
		System.out.println(mundo3);		
		System.out.println("----------------------------------------");
		
		System.out.println(new Mundo.Builder(c�lulasMundoActual).build());
		
		System.out.println("----------------------------------------");*/

		//Mundo mundoActual = new Mundo.Builder(3, 3)
		//		.llenoDe(C�lula.VIVA).build();							
		
		/*Mundo mundoActual = new Mundo.Builder(5, 5)
									.conC�lulaVivaEn(new Coordenada(1, 1)).conC�lulaVivaEn(new Coordenada(2, 1)).conC�lulaVivaEn(new Coordenada(3, 1))
									.conC�lulaVivaEn(new Coordenada(1, 2)).conC�lulaVivaEn(new Coordenada(2, 2)).conC�lulaVivaEn(new Coordenada(3, 2))
									.conC�lulaVivaEn(new Coordenada(1, 3)).conC�lulaVivaEn(new Coordenada(2, 3)).conC�lulaVivaEn(new Coordenada(3, 3))
									.build();	*/
		
		/*Mundo mundoActual = new Mundo.Builder(6, 6)
				 .conC�lulaVivaEn(new Coordenada(1,1))
				 									.conC�lulaVivaEn(new Coordenada(2,2))
					.conC�lulaVivaEn(new Coordenada(0,3)).conC�lulaVivaEn(new Coordenada(1,3)).conC�lulaVivaEn(new Coordenada(2,3))
					.build();*/
		
		Mundo mundoActual = new Mundo.Builder(3, 3)
				.conC�lulaVivaEn(new Coordenada(0, 0)).conC�lulaVivaEn(new Coordenada(1, 0)).conC�lulaVivaEn(new Coordenada(2, 0))
				.conC�lulaVivaEn(new Coordenada(0, 1)).conC�lulaVivaEn(new Coordenada(1, 1)).conC�lulaVivaEn(new Coordenada(2, 1))
				.conC�lulaVivaEn(new Coordenada(0, 2)).conC�lulaVivaEn(new Coordenada(1, 2)).conC�lulaVivaEn(new Coordenada(2, 2))
				
				.build();		
							
		System.out.println(mundoActual);		
		
		
		Juego juego3 = new Juego(mundoActual, 1);
		juego3.jugar();
		System.out.println(juego3);
		
		
	}
		
}















