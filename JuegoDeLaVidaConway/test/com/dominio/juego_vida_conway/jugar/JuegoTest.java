package com.dominio.juego_vida_conway.jugar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public final class JuegoTest {

	@Test
	public void cualquiereGeneraciónSiguienteDeUnaTinaEsLaMismaTina() {
		int generaciones = 5;
		Mundo tina = new Mundo.Builder(5, 5)													
											.conCélulaVivaEn(new Coordenada(2,1))
		.conCélulaVivaEn(new Coordenada(1,2))									.conCélulaVivaEn(new Coordenada(3,2))
											.conCélulaVivaEn(new Coordenada(2,3))
											.build();
		
		Juego juego = new Juego(tina, generaciones);
		juego.jugar();
		
		assertThat(juego.verÚltimaGeneración(), is( "....." + Mundo.SALTO_DE_LÍNEA + 
													"..*.." + Mundo.SALTO_DE_LÍNEA +
													".*.*." + Mundo.SALTO_DE_LÍNEA +
													"..*.." + Mundo.SALTO_DE_LÍNEA +
													"....." + Mundo.SALTO_DE_LÍNEA));
	}
	
	@Test
	public void laGeneraciónSiguienteDeUnaLíneaHorizontalDeCélulasVivasImparEsLaMismaLíneaDeCélulasVivasVertical() {
		int generaciones = 1;
		Mundo mundo = new Mundo.Builder(5, 5)
							.conCélulaVivaEn(new Coordenada(1,2))
							.conCélulaVivaEn(new Coordenada(2,2))
							.conCélulaVivaEn(new Coordenada(3,2))
							.build();
		
		Juego juego = new Juego(mundo, generaciones);
		juego.jugar();
		
		assertThat(juego.verÚltimaGeneración(), is( "....." + Mundo.SALTO_DE_LÍNEA + 
													"..*.." + Mundo.SALTO_DE_LÍNEA +
													"..*.." + Mundo.SALTO_DE_LÍNEA +
													"..*.." + Mundo.SALTO_DE_LÍNEA +
													"....." + Mundo.SALTO_DE_LÍNEA));
	}
	
	@Test
	public void laGeneraciónSiguienteDeUnFaroSonDosBloquesEnLaMismaPosiciónDelFaro() {
		int generaciones = 1;
		Mundo faro = new Mundo.Builder(6, 6)
						.conCélulaVivaEn(new Coordenada(1,1)).conCélulaVivaEn(new Coordenada(2,1))
						.conCélulaVivaEn(new Coordenada(1,2))
																															 .conCélulaVivaEn(new Coordenada(4,3))
																						.conCélulaVivaEn(new Coordenada(3,4)).conCélulaVivaEn(new Coordenada(4,4))
																						.build();
		
		Juego juego = new Juego(faro, generaciones);
		juego.jugar();
		
		assertThat(juego.verÚltimaGeneración(), is( "......" + Mundo.SALTO_DE_LÍNEA + 
													".**..." + Mundo.SALTO_DE_LÍNEA +
													".**..." + Mundo.SALTO_DE_LÍNEA +
													"...**." + Mundo.SALTO_DE_LÍNEA +
													"...**." + Mundo.SALTO_DE_LÍNEA +
													"......" + Mundo.SALTO_DE_LÍNEA));
	}
	
	@Test
	public void laFrecuenciaDeOscilaciónDeUnSapoEsDos() {
		int generaciones = 1;
		
		Mundo sapo = new Mundo.Builder(6, 6)
																		 .conCélulaVivaEn(new Coordenada(2,2)).conCélulaVivaEn(new Coordenada(3,2)).conCélulaVivaEn(new Coordenada(4,2))			
									.conCélulaVivaEn(new Coordenada(1,3)).conCélulaVivaEn(new Coordenada(2,3)).conCélulaVivaEn(new Coordenada(3,3))
									.build();
		
		Juego juego = new Juego(sapo, 2 * generaciones);
		juego.jugar();
		
		assertThat(juego.verÚltimaGeneración(), is( "......" + Mundo.SALTO_DE_LÍNEA + 
													"......" + Mundo.SALTO_DE_LÍNEA +
													"..***." + Mundo.SALTO_DE_LÍNEA +
													".***.." + Mundo.SALTO_DE_LÍNEA +
													"......" + Mundo.SALTO_DE_LÍNEA +
													"......" + Mundo.SALTO_DE_LÍNEA));
	}
	//
	
	@Test
	public void laCuartaGeneraciónDeUnPlaneadorEsElMismoPlaneadorDesplazadoEnUnaCélulasHaciaAbajoYUnaHaciaLaDerecha() {
		int generaciones = 4;
		
		Mundo planeador = new Mundo.Builder(6, 6)
													 .conCélulaVivaEn(new Coordenada(1,1))
													 									.conCélulaVivaEn(new Coordenada(2,2))
				.conCélulaVivaEn(new Coordenada(0,3)).conCélulaVivaEn(new Coordenada(1,3)).conCélulaVivaEn(new Coordenada(2,3))
				.build();
					
		Juego juego = new Juego(planeador, generaciones);
		juego.jugar();
		
		Mundo planeadorDesplazado = new Mundo.Builder(6, 6)
													.conCélulaVivaEn(new Coordenada(1 + 1, 1 + 1))
																						.conCélulaVivaEn(new Coordenada(2 + 1, 2 + 1))
				.conCélulaVivaEn(new Coordenada(0 + 1, 3 + 1)).conCélulaVivaEn(new Coordenada(1 + 1, 3 + 1)).conCélulaVivaEn(new Coordenada(2 + 1, 3 + 1))
				.build();
			
		assertThat(juego.verÚltimaGeneración(), is(planeadorDesplazado.toString()));
	}

}
