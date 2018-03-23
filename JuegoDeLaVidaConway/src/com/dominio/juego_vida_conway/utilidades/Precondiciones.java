package com.dominio.juego_vida_conway.utilidades;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

public final class Precondiciones {
	
	private Precondiciones(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	  
	public static boolean precondicionesFallan(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref,
															final Coordenada inicioDestino, final Coordenada inicioFuente) {

		return precondicionesReferenciaNoNulas(destino, fuente, ref, inicioDestino, inicioFuente) || 
				precondicionesCoordenadas(fuente, inicioFuente) || precondicionesCoordenadas(destino, inicioDestino) ||
					precondicionesVentana(destino, fuente, ref, inicioDestino, inicioFuente); 
	}

	private static boolean precondicionesReferenciaNoNulas(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref, 
																final Coordenada inicioDestino, final Coordenada inicioFuente) {
		return destino == null || fuente == null || ref == null || inicioFuente == null || inicioDestino == null;
	}

	//asegura que la copia no salga de los limites de las matrices que se estan copiando
		private static boolean precondicionesVentana(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref,
															final Coordenada inicioDestino, final Coordenada inicioFuente) {
				
			return ref.length > (destino.length - inicioDestino.columna()) || ref.length > (fuente.length - inicioFuente.columna()) ||
					ref[0].length > (destino[0].length - inicioDestino.fila()) || ref[0].length > (fuente[0].length - inicioFuente.fila());
		}

		//asegura que las coordenadas esten dentro del arreglo
		private static boolean precondicionesCoordenadas(Célula[][] células, Coordenada coordenada){
			return coordenada.columna() < 0 || coordenada.fila() < 0 || coordenada.columna() >= células.length || coordenada.fila() >= células[0].length;
	}

}
