package com.dominio.juego_vida_conway.jugar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

public final class MundoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void unMundoDeConwayTieneQueTenerComoMínimoTresFilasYTresColumnas() {		
		assertThat(new Mundo.Builder(2, 2).build().toString(), is( ".." + Mundo.SALTO_DE_LÍNEA + 
												  				   ".." + Mundo.SALTO_DE_LÍNEA));		
	}
	
	@Test
	public void unMundoPorDefectoMuertoDeConwaySePuedeConstruirAPartirDelNúmeroDeFilasYColumnas() {		
		assertThat(new Mundo.Builder(3, 3).build().toString(), is( "..." + Mundo.SALTO_DE_LÍNEA + 
												  				   "..." + Mundo.SALTO_DE_LÍNEA +
												  				   "..." + Mundo.SALTO_DE_LÍNEA));
	} 
	
	@Test
	public void unMundoConAlgunasCélulasVIVAsDeConwaySePuedeConstruirAPartirDelNúmeroDeFilasYColumnasYLasCoordenadasDeLasCélulasVIVAs() {
		Mundo mundo = new Mundo.Builder(3, 3)
							.conCélulaVivaEn(new Coordenada(0,0))
								.conCélulaVivaEn(new Coordenada(1,1)).build();

		assertThat(mundo.toString(), is( "*.." + Mundo.SALTO_DE_LÍNEA + 
										 ".*." + Mundo.SALTO_DE_LÍNEA +
										 "..." + Mundo.SALTO_DE_LÍNEA));
	}
	
	@Test
	public void unMundoDeConwaySePuedeConstruirAPartirDeUnArchivo() {//resources/iniciar_juego.txt
	    Mundo mundo = new Mundo.Builder().build();
		assertThat(mundo.toString(), is( "......" + Mundo.SALTO_DE_LÍNEA + 
										 ".*...." + Mundo.SALTO_DE_LÍNEA +
										 "..*..." + Mundo.SALTO_DE_LÍNEA + 
										 "***..." + Mundo.SALTO_DE_LÍNEA +
										 "......" + Mundo.SALTO_DE_LÍNEA + 
										 "......" + Mundo.SALTO_DE_LÍNEA));
	}
	
	@Test
	public void cualquierCélulaConMenosDeDosVecinosVivosEnLaSiguienteGeneraciónMuerenPorBajaPoblación() {	
		Mundo mundoActual = new Mundo.Builder(3, 3)
									.conCélulaVivaEn(new Coordenada(1, 1))
									.build();							
		
		assertThat(mundoActual.nuevaGeneración().toString(), is("..." + Mundo.SALTO_DE_LÍNEA + 
																"..." + Mundo.SALTO_DE_LÍNEA +
													 			"..." + Mundo.SALTO_DE_LÍNEA));
										
	}
	
	@Test
	public void cualquierCélulaVivaConMásDeTresVecinosVivosMuereEnLaSiguienteGeneraciónMuerePorSobrePoblación() {
		
		Mundo mundoActual = new Mundo.Builder(3, 3).llenoDe(Célula.VIVA).build();							
		
		assertThat(mundoActual.nuevaGeneración().toString(), is("*.*" + Mundo.SALTO_DE_LÍNEA + 
								 							    "..." + Mundo.SALTO_DE_LÍNEA +
								 							    "*.*" + Mundo.SALTO_DE_LÍNEA ));
	
	}
	
	@Test
	public void cualquierCélulaConTresVecinosVivosEnLaSiguienteGeneraciónVive() {		
		Mundo mundoActual = new Mundo.Builder(5, 5)
									.conCélulaVivaEn(new Coordenada(2, 1))
									.conCélulaVivaEn(new Coordenada(2, 2)).conCélulaVivaEn(new Coordenada(3, 2))
									.conCélulaVivaEn(new Coordenada(2, 3))
									.build();							
									
		assertThat(mundoActual.nuevaGeneración().toString(), is("....." + Mundo.SALTO_DE_LÍNEA + 
								 							    "..**." + Mundo.SALTO_DE_LÍNEA +
								 							    ".***." + Mundo.SALTO_DE_LÍNEA +
								 							    "..**." + Mundo.SALTO_DE_LÍNEA + 
								 							    "....." + Mundo.SALTO_DE_LÍNEA ));
	}
	
	

	@Test
	public void cualquierCélulaConExactamenteTresVecinosVivosEnLaSiguienteGeneraciónVivePorReproducción() {
		
		Mundo mundoActual = new Mundo.Builder(4, 4)
									.conCélulaVivaEn(new Coordenada(1, 1)).conCélulaVivaEn(new Coordenada(2, 1))
									.conCélulaVivaEn(new Coordenada(1, 2)).conCélulaVivaEn(new Coordenada(2, 2))
									.build();	

		assertThat(mundoActual.nuevaGeneración().toString(), is("...." + Mundo.SALTO_DE_LÍNEA + 
															    ".**." + Mundo.SALTO_DE_LÍNEA +
															    ".**." + Mundo.SALTO_DE_LÍNEA +
															    "...." + Mundo.SALTO_DE_LÍNEA ));
	}
	
	
}
