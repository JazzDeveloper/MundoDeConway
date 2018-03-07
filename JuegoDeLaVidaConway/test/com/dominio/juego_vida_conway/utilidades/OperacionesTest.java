package com.dominio.juego_vida_conway.utilidades;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Mundo.C�lula;

import static com.dominio.juego_vida_conway.utilidades.Operaciones.*; 

public final class OperacionesTest {

	@Test
	public void laCadenaDeCar�teresQuinientosCincuentaYTresEsUnArregloIntConM�merosCincoCincoTres() {
		final int [] arregloInt = {5 , 5 , 3};
		assertThat(cadenaDeCar�cteresAArregloInt("553"), is(arregloInt));
	}
	
	@Test
	public void unArregloBidimensionalDeC�lulasEsInicializadoConC�luasMuertas() {
		final C�lula[][] c�lulas = {{C�lula.Viva, 	C�lula.Muerta, 	C�lula.Viva}, 
							  	    {C�lula.Muerta, C�lula.Viva, 	C�lula.Muerta}, 
							        {C�lula.Viva, 	C�lula.Muerta, 	C�lula.Viva}};
		
		final C�lula[][] muertas = {{C�lula.Muerta, C�lula.Muerta, 	C�lula.Muerta}, 
									{C�lula.Muerta, C�lula.Muerta, 	C�lula.Muerta}, 
									{C�lula.Muerta, C�lula.Muerta, 	C�lula.Muerta}};
		
		assertThat(inicializar(c�lulas), is(muertas));
	}
	
	@Test
	public void copiarUnArregloFuenteSobreUnArregloDeDestinoEsElArregloDeDestinoLlenadoConElArregloFuenteEnLaVentanaDeLaReferencia() {

		C�lula[][] fuente =     {{C�lula.Viva, 	 C�lula.Muerta, C�lula.Viva}, 
							     {C�lula.Muerta, C�lula.Viva, 	C�lula.Muerta}, 
							     {C�lula.Viva,   C�lula.Muerta, C�lula.Viva},};
		
		C�lula[][] destino =    {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
							     {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
							     {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
						         {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
		
		C�lula[][] referencia = {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
				  				 {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
				  				 {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
		

		C�lula[][] copia =      {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
							     {C�lula.Muerta, C�lula.Viva,   C�lula.Muerta, C�lula.Viva}, 
							     {C�lula.Muerta, C�lula.Muerta, C�lula.Viva,   C�lula.Muerta},
						         {C�lula.Muerta, C�lula.Viva,   C�lula.Muerta, C�lula.Viva}};
		
		assertThat(copiar(destino, fuente, referencia, new Coordenada(1,1), new Coordenada(0, 0)), is(copia));
	}

	
	@Test
	public void cualquierC�lulaConMenosDeDosVecinosVivosEnLaSiguienteGeneraci�nMuerenPorBajaPoblaci�n() {
		
		C�lula[][] generaci�nActualDeTrabajo =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva, 	 C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
													
		C�lula[][] generaci�nSiguienteDeTrabajo =    		  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
				   											   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
				   											   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};												
		
		assertThat(calcularGeneraci�nSiguienteDeTrabajo(generaci�nActualDeTrabajo), is(generaci�nSiguienteDeTrabajo));
	
	}
	
	@Test
	public void cualquierC�lulaConDosOTresVecinosVivosEnLaSiguienteGeneraci�nVive() {
		
		C�lula[][] generaci�nActualDeTrabajo =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
													
		
		C�lula[][] generaci�nActualDeTrabajos =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
		
		assertThat(calcularGeneraci�nSiguienteDeTrabajo(generaci�nActualDeTrabajo), is(generaci�nActualDeTrabajos));
	
	}


	@Test
	public void cualquierC�lulaVivaConM�sDeTresVecinosVivosMuereEnLaSiguienteGeneraci�nMuerePorSobrePoblaci�n() {
		
		C�lula[][] generaci�nActualDeTrabajo =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Viva,   C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Viva,   C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Viva,   C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
													
		
		C�lula[][] generaci�nActualDeTrabajos =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
		
		assertThat(calcularGeneraci�nSiguienteDeTrabajo(generaci�nActualDeTrabajo), is(generaci�nActualDeTrabajos));
	
	}

	@Test
	public void cualquierC�lulaConExactamenteTresVecinosVivosEnLaSiguienteGeneraci�nVivePorReproducci�n() {
		
		C�lula[][] generaci�nActualDeTrabajo =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
													
		
		C�lula[][] generaci�nActualDeTrabajos =    			  {{C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}, 
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Viva,   C�lula.Viva,   C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta},
															   {C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta, C�lula.Muerta}};
		
		assertThat(calcularGeneraci�nSiguienteDeTrabajo(generaci�nActualDeTrabajo), is(generaci�nActualDeTrabajos));
	
	}
	
}
