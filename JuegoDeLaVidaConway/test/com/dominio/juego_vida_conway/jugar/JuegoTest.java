package com.dominio.juego_vida_conway.jugar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public final class JuegoTest {

	@Test
	public void cualquiereGeneraci�nSiguienteDeUnaTinaEsLaMismaTina() {
		int generaciones = 5;
		Mundo tina = Mundo.de(5, 5)
						.conC�lulaVivaEn(new Coordenada(2,1))
							.conC�lulaVivaEn(new Coordenada(1,2))
								.conC�lulaVivaEn(new Coordenada(3,2))
									.conC�lulaVivaEn(new Coordenada(2,3));
		
		Juego juego = new Juego(tina, generaciones);
		juego.jugar();
		
		assertThat(juego.ver�ltimaGeneraci�n(), is( "....." + Mundo.SALTO_DE_L�NEA + 
													"..*.." + Mundo.SALTO_DE_L�NEA +
													".*.*." + Mundo.SALTO_DE_L�NEA +
													"..*.." + Mundo.SALTO_DE_L�NEA +
													"....." + Mundo.SALTO_DE_L�NEA));
	}
	
	@Test
	public void laGeneraci�nSiguienteDeUnaL�neaHorizontalDeC�lulasVivasImparEsLaMismaL�neaDeC�lulasVivasVertical() {
		int generaciones = 1;
		Mundo mundo = Mundo.de(5, 5)
						.conC�lulaVivaEn(new Coordenada(1,2))
							.conC�lulaVivaEn(new Coordenada(2,2))
								.conC�lulaVivaEn(new Coordenada(3,2));
		
		Juego juego = new Juego(mundo, generaciones);
		juego.jugar();
		
		assertThat(juego.ver�ltimaGeneraci�n(), is( "....." + Mundo.SALTO_DE_L�NEA + 
													"..*.." + Mundo.SALTO_DE_L�NEA +
													"..*.." + Mundo.SALTO_DE_L�NEA +
													"..*.." + Mundo.SALTO_DE_L�NEA +
													"....." + Mundo.SALTO_DE_L�NEA));
	}
	
	@Test
	public void laGeneraci�nSiguienteDeUnFaroSonDosBloquesEnLaMismaPosici�nDelFaro() {
		int generaciones = 1;
		Mundo faro = Mundo.de(6, 6)
						.conC�lulaVivaEn(new Coordenada(1,1))
							.conC�lulaVivaEn(new Coordenada(2,1))
								.conC�lulaVivaEn(new Coordenada(1,2))
										.conC�lulaVivaEn(new Coordenada(4,3))
											.conC�lulaVivaEn(new Coordenada(3,4))
												.conC�lulaVivaEn(new Coordenada(4,4));
		
		Juego juego = new Juego(faro, generaciones);
		juego.jugar();
		
		assertThat(juego.ver�ltimaGeneraci�n(), is( "......" + Mundo.SALTO_DE_L�NEA + 
													".**..." + Mundo.SALTO_DE_L�NEA +
													".**..." + Mundo.SALTO_DE_L�NEA +
													"...**." + Mundo.SALTO_DE_L�NEA +
													"...**." + Mundo.SALTO_DE_L�NEA +
													"......" + Mundo.SALTO_DE_L�NEA));
	}
	
	@Test
	public void laFrecuenciaDeOscilaci�nDeUnSapoEsDos() {
		int generaciones = 1;
		
		Mundo sapo = Mundo.de(6, 6)
						.conC�lulaVivaEn(new Coordenada(2,2))
							.conC�lulaVivaEn(new Coordenada(3,2))
								.conC�lulaVivaEn(new Coordenada(4,2))
										.conC�lulaVivaEn(new Coordenada(1,3))
											.conC�lulaVivaEn(new Coordenada(2,3))
											.conC�lulaVivaEn(new Coordenada(3,3));
		
		Juego juego = new Juego(sapo, 2 * generaciones);
		juego.jugar();
		
		assertThat(juego.ver�ltimaGeneraci�n(), is( "......" + Mundo.SALTO_DE_L�NEA + 
													"......" + Mundo.SALTO_DE_L�NEA +
													"..***." + Mundo.SALTO_DE_L�NEA +
													".***.." + Mundo.SALTO_DE_L�NEA +
													"......" + Mundo.SALTO_DE_L�NEA +
													"......" + Mundo.SALTO_DE_L�NEA));
	}
	//
	
	@Test
	public void laCuartaGeneraci�nDeUnPlaneadorEsElMismoPlaneadorDesplazadoEnUnaC�lulasHaciaAbajoYUnaHaciaLaDerecha() {
		int generaciones = 4;
		
		Mundo planeador = Mundo.de(6, 6)
							.conC�lulaVivaEn(new Coordenada(1,1))
								.conC�lulaVivaEn(new Coordenada(2,2))
									.conC�lulaVivaEn(new Coordenada(0,3))
											.conC�lulaVivaEn(new Coordenada(1,3))
												.conC�lulaVivaEn(new Coordenada(2,3));
			
		Juego juego = new Juego(planeador, generaciones);
		juego.jugar();
		
		Mundo planeadorDesplazado = Mundo.de(6, 6)
										.conC�lulaVivaEn(new Coordenada(1 + 1, 1 + 1))
											.conC�lulaVivaEn(new Coordenada(2 + 1, 2 + 1))
												.conC�lulaVivaEn(new Coordenada(0 + 1, 3 + 1))
														.conC�lulaVivaEn(new Coordenada(1 + 1, 3 + 1))
															.conC�lulaVivaEn(new Coordenada(2 + 1, 3 + 1));
							
		assertThat(juego.ver�ltimaGeneraci�n(), is(planeadorDesplazado.toString()));
	}

}
