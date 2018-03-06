package com.dominio.juego_vida_conway.jugar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

public class MundoTest {
	
	@Test
	public void unMundoMuertoDeConwaySePuedeConstruirAPartirDelNúmeroDeFilasYColumnas() {		
		assertThat(Mundo.de(3, 3).toString(), is( "..." + Mundo.SALTO_DE_LÍNEA + 
												  "..." + Mundo.SALTO_DE_LÍNEA +
												  "..." + Mundo.SALTO_DE_LÍNEA));
	} 
	
	@Test
	public void unMundoConAlgunasCélulasVivasDeConwaySePuedeConstruirAPartirDelNúmeroDeFilasYColumnasYLasCoordenadasDeLasCélulasVivas() {
		Mundo mundo = Mundo.de(3, 3)
						.conCélulaVivaEn(new Coordenada(0,0))
								.conCélulaVivaEn(new Coordenada(1,1));

		assertThat(mundo.toString(), is( "*.." + Mundo.SALTO_DE_LÍNEA + 
										 ".*." + Mundo.SALTO_DE_LÍNEA +
										 "..." + Mundo.SALTO_DE_LÍNEA));
	}
	
	@Test
	public void unMundoDeConwaySePuedeConstruirAPartirDeUnArchivo() {//resources/iniciar_juego.txt
	    Mundo mundo = Mundo.desdeArchivo();
		assertThat(mundo.toString(), is( "......" + Mundo.SALTO_DE_LÍNEA + 
										 ".*...." + Mundo.SALTO_DE_LÍNEA +
										 "..*..." + Mundo.SALTO_DE_LÍNEA + 
										 "***..." + Mundo.SALTO_DE_LÍNEA +
										 "......" + Mundo.SALTO_DE_LÍNEA + 
										 "......" + Mundo.SALTO_DE_LÍNEA));
	}
	
	//@Test(expected = IllegalArgumentException.class)
	public void unMundoDeConwayTieneQueTenerComoMínimoTresFilasYTresColumnas() {		
		assertThat(Mundo.de(2, 2).toString(), is( ".." + Mundo.SALTO_DE_LÍNEA + 
												  ".." + Mundo.SALTO_DE_LÍNEA));		
	}
	

}
