package com.dominio.juego_vida_conway.utilidades;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

import static com.dominio.juego_vida_conway.utilidades.Operaciones.*; 

public final class OperacionesTest {

	@Test
	public void laCadenaDeCaráteresQuinientosCincuentaYTresEsUnArregloIntConMúmerosCincoCincoTres() {
		final int [] arregloInt = {5 , 5 , 3};
		assertThat(cadenaDeCarácteresAArregloInt("553"), is(arregloInt));
	}
	
	@Test
	public void unArregloBidimensionalDeCélulasEsInicializadoConCéluasMuertas() {
		final Célula[][] células = {{Célula.Viva, 	Célula.Muerta, 	Célula.Viva}, 
							  	    {Célula.Muerta, Célula.Viva, 	Célula.Muerta}, 
							        {Célula.Viva, 	Célula.Muerta, 	Célula.Viva}};
		
		final Célula[][] muertas = {{Célula.Muerta, Célula.Muerta, 	Célula.Muerta}, 
									{Célula.Muerta, Célula.Muerta, 	Célula.Muerta}, 
									{Célula.Muerta, Célula.Muerta, 	Célula.Muerta}};
		
		assertThat(inicializar(células), is(muertas));
	}
	
	@Test
	public void copiarUnArregloFuenteSobreUnArregloDeDestinoEsElArregloDeDestinoLlenadoConElArregloFuenteEnLaVentanaDeLaReferencia() {

		Célula[][] fuente =     {{Célula.Viva, 	 Célula.Muerta, Célula.Viva}, 
							     {Célula.Muerta, Célula.Viva, 	Célula.Muerta}, 
							     {Célula.Viva,   Célula.Muerta, Célula.Viva},};
		
		Célula[][] destino =    {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
							     {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
							     {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
						         {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
		
		Célula[][] referencia = {{Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
				  				 {Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
				  				 {Célula.Muerta, Célula.Muerta, Célula.Muerta}};
		

		Célula[][] copia =      {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
							     {Célula.Muerta, Célula.Viva,   Célula.Muerta, Célula.Viva}, 
							     {Célula.Muerta, Célula.Muerta, Célula.Viva,   Célula.Muerta},
						         {Célula.Muerta, Célula.Viva,   Célula.Muerta, Célula.Viva}};
		
		assertThat(copiar(destino, fuente, referencia, new Coordenada(1,1), new Coordenada(0, 0)), is(copia));
	}

	
	@Test
	public void cualquierCélulaConMenosDeDosVecinosVivosEnLaSiguienteGeneraciónMuerenPorBajaPoblación() {
		
		Célula[][] generaciónActualDeTrabajo =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Viva, 	 Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
													
		Célula[][] generaciónSiguienteDeTrabajo =    		  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
				   											   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
				   											   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};												
		
		assertThat(calcularGeneraciónSiguienteDeTrabajo(generaciónActualDeTrabajo), is(generaciónSiguienteDeTrabajo));
	
	}
	
	@Test
	public void cualquierCélulaConDosOTresVecinosVivosEnLaSiguienteGeneraciónVive() {
		
		Célula[][] generaciónActualDeTrabajo =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
													
		
		Célula[][] generaciónActualDeTrabajos =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
		
		assertThat(calcularGeneraciónSiguienteDeTrabajo(generaciónActualDeTrabajo), is(generaciónActualDeTrabajos));
	
	}


	@Test
	public void cualquierCélulaVivaConMásDeTresVecinosVivosMuereEnLaSiguienteGeneraciónMuerePorSobrePoblación() {
		
		Célula[][] generaciónActualDeTrabajo =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Viva,   Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Viva,   Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Viva,   Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
													
		
		Célula[][] generaciónActualDeTrabajos =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
		
		assertThat(calcularGeneraciónSiguienteDeTrabajo(generaciónActualDeTrabajo), is(generaciónActualDeTrabajos));
	
	}

	@Test
	public void cualquierCélulaConExactamenteTresVecinosVivosEnLaSiguienteGeneraciónVivePorReproducción() {
		
		Célula[][] generaciónActualDeTrabajo =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
													
		
		Célula[][] generaciónActualDeTrabajos =    			  {{Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}, 
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Viva,   Célula.Viva,   Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta},
															   {Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta, Célula.Muerta}};
		
		assertThat(calcularGeneraciónSiguienteDeTrabajo(generaciónActualDeTrabajo), is(generaciónActualDeTrabajos));
	
	}
	
}
