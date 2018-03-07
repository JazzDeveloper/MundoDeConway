package com.dominio.juego_vida_conway.utilidades;

import static org.junit.Assert.assertThat;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import com.dominio.juego_vida_conway.jugar.Mundo;

public final class ArchivoTest {
	
	//ubicaci�n por defecto resources/iniciar_juego.txt
	//@Test //: esta deshabilitado el test para que el usuario pueda modificar el archivo 
	public void elMundoDelArchivoEnLaUbicacionPorDefectoEsUnBarco() {		
		assertThat(Archivo.leerMundo().toString(), is(  "......" + Mundo.SALTO_DE_L�NEA + 
												  		".*...." + Mundo.SALTO_DE_L�NEA +
												  		"..*..." + Mundo.SALTO_DE_L�NEA +
												  		"***..." + Mundo.SALTO_DE_L�NEA +
												  		"......" + Mundo.SALTO_DE_L�NEA +
												  		"......" + Mundo.SALTO_DE_L�NEA));												 
	}
	
	//@Test //: esta deshabilitado el test para que el usuario pueda modificar el archivo 
	public void elN�meroDeGeneracionesDelArchivoEnLaUbicacionPorDefectoEsCuatro() {		
		assertThat(Archivo.leerN�meroGeneraciones(), is(4));
												 
	}		

}
