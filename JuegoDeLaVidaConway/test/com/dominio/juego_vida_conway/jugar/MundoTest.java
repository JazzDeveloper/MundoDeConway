package com.dominio.juego_vida_conway.jugar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Mundo.C�lula;

public final class MundoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void unMundoDeConwayTieneQueTenerComoM�nimoTresFilasYTresColumnas() {		
		assertThat(new Mundo.Builder(2, 2).build().toString(), is( ".." + Mundo.SALTO_DE_L�NEA + 
												  				   ".." + Mundo.SALTO_DE_L�NEA));		
	}
	
	@Test
	public void unMundoPorDefectoMuertoDeConwaySePuedeConstruirAPartirDelN�meroDeFilasYColumnas() {		
		assertThat(new Mundo.Builder(3, 3).build().toString(), is( "..." + Mundo.SALTO_DE_L�NEA + 
												  				   "..." + Mundo.SALTO_DE_L�NEA +
												  				   "..." + Mundo.SALTO_DE_L�NEA));
	} 
	
	@Test
	public void unMundoConAlgunasC�lulasVIVAsDeConwaySePuedeConstruirAPartirDelN�meroDeFilasYColumnasYLasCoordenadasDeLasC�lulasVIVAs() {
		Mundo mundo = new Mundo.Builder(3, 3)
							.conC�lulaVivaEn(new Coordenada(0,0))
								.conC�lulaVivaEn(new Coordenada(1,1)).build();

		assertThat(mundo.toString(), is( "*.." + Mundo.SALTO_DE_L�NEA + 
										 ".*." + Mundo.SALTO_DE_L�NEA +
										 "..." + Mundo.SALTO_DE_L�NEA));
	}
	
	@Test
	public void unMundoDeConwaySePuedeConstruirAPartirDeUnArchivo() {//resources/iniciar_juego.txt
	    Mundo mundo = new Mundo.Builder().build();
		assertThat(mundo.toString(), is( "......" + Mundo.SALTO_DE_L�NEA + 
										 ".*...." + Mundo.SALTO_DE_L�NEA +
										 "..*..." + Mundo.SALTO_DE_L�NEA + 
										 "***..." + Mundo.SALTO_DE_L�NEA +
										 "......" + Mundo.SALTO_DE_L�NEA + 
										 "......" + Mundo.SALTO_DE_L�NEA));
	}
	
	@Test
	public void cualquierC�lulaConMenosDeDosVecinosVivosEnLaSiguienteGeneraci�nMuerenPorBajaPoblaci�n() {	
		Mundo mundoActual = new Mundo.Builder(3, 3)
									.conC�lulaVivaEn(new Coordenada(1, 1))
									.build();							
		
		assertThat(mundoActual.nuevaGeneraci�n().toString(), is("..." + Mundo.SALTO_DE_L�NEA + 
																"..." + Mundo.SALTO_DE_L�NEA +
													 			"..." + Mundo.SALTO_DE_L�NEA));
										
	}
	
	@Test
	public void cualquierC�lulaVivaConM�sDeTresVecinosVivosMuereEnLaSiguienteGeneraci�nMuerePorSobrePoblaci�n() {
		
		Mundo mundoActual = new Mundo.Builder(3, 3).llenoDe(C�lula.VIVA).build();							
		
		assertThat(mundoActual.nuevaGeneraci�n().toString(), is("*.*" + Mundo.SALTO_DE_L�NEA + 
								 							    "..." + Mundo.SALTO_DE_L�NEA +
								 							    "*.*" + Mundo.SALTO_DE_L�NEA ));
	
	}
	
	@Test
	public void cualquierC�lulaConTresVecinosVivosEnLaSiguienteGeneraci�nVive() {		
		Mundo mundoActual = new Mundo.Builder(5, 5)
									.conC�lulaVivaEn(new Coordenada(2, 1))
									.conC�lulaVivaEn(new Coordenada(2, 2)).conC�lulaVivaEn(new Coordenada(3, 2))
									.conC�lulaVivaEn(new Coordenada(2, 3))
									.build();							
									
		assertThat(mundoActual.nuevaGeneraci�n().toString(), is("....." + Mundo.SALTO_DE_L�NEA + 
								 							    "..**." + Mundo.SALTO_DE_L�NEA +
								 							    ".***." + Mundo.SALTO_DE_L�NEA +
								 							    "..**." + Mundo.SALTO_DE_L�NEA + 
								 							    "....." + Mundo.SALTO_DE_L�NEA ));
	}
	
	

	@Test
	public void cualquierC�lulaConExactamenteTresVecinosVivosEnLaSiguienteGeneraci�nVivePorReproducci�n() {
		
		Mundo mundoActual = new Mundo.Builder(4, 4)
									.conC�lulaVivaEn(new Coordenada(1, 1)).conC�lulaVivaEn(new Coordenada(2, 1))
									.conC�lulaVivaEn(new Coordenada(1, 2)).conC�lulaVivaEn(new Coordenada(2, 2))
									.build();	

		assertThat(mundoActual.nuevaGeneraci�n().toString(), is("...." + Mundo.SALTO_DE_L�NEA + 
															    ".**." + Mundo.SALTO_DE_L�NEA +
															    ".**." + Mundo.SALTO_DE_L�NEA +
															    "...." + Mundo.SALTO_DE_L�NEA ));
	}
	
	
}
