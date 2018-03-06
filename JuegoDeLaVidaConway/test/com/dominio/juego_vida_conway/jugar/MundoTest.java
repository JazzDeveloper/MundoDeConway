package com.dominio.juego_vida_conway.jugar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Mundo.C�lula;

public class MundoTest {
	
	@Test
	public void unMundoMuertoDeConwaySePuedeConstruirAPartirDelN�meroDeFilasYColumnas() {		
		assertThat(Mundo.de(3, 3).toString(), is( "..." + Mundo.SALTO_DE_L�NEA + 
												  "..." + Mundo.SALTO_DE_L�NEA +
												  "..." + Mundo.SALTO_DE_L�NEA));
	} 
	
	@Test
	public void unMundoConAlgunasC�lulasVivasDeConwaySePuedeConstruirAPartirDelN�meroDeFilasYColumnasYLasCoordenadasDeLasC�lulasVivas() {
		Mundo mundo = Mundo.de(3, 3)
						.conC�lulaVivaEn(new Coordenada(0,0))
								.conC�lulaVivaEn(new Coordenada(1,1));

		assertThat(mundo.toString(), is( "*.." + Mundo.SALTO_DE_L�NEA + 
										 ".*." + Mundo.SALTO_DE_L�NEA +
										 "..." + Mundo.SALTO_DE_L�NEA));
	}
	
	@Test
	public void unMundoDeConwaySePuedeConstruirAPartirDeUnArchivo() {//resources/iniciar_juego.txt
	    Mundo mundo = Mundo.desdeArchivo();
		assertThat(mundo.toString(), is( "......" + Mundo.SALTO_DE_L�NEA + 
										 ".*...." + Mundo.SALTO_DE_L�NEA +
										 "..*..." + Mundo.SALTO_DE_L�NEA + 
										 "***..." + Mundo.SALTO_DE_L�NEA +
										 "......" + Mundo.SALTO_DE_L�NEA + 
										 "......" + Mundo.SALTO_DE_L�NEA));
	}
	
	//@Test(expected = IllegalArgumentException.class)
	public void unMundoDeConwayTieneQueTenerComoM�nimoTresFilasYTresColumnas() {		
		assertThat(Mundo.de(2, 2).toString(), is( ".." + Mundo.SALTO_DE_L�NEA + 
												  ".." + Mundo.SALTO_DE_L�NEA));		
	}
	

}
