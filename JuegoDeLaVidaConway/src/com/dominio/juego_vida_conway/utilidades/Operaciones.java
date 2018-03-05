package com.dominio.juego_vida_conway.utilidades;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Mundo.Célula;

public final class Operaciones {
	
	private static final int NÚMERO_CONSTANTES_JUEGO = 3;//número filas generación cero, número columnas generación cero, generaciones
	private static final int CONSTANTE_ASCII = 48;
	private static final Coordenada máscara[] = {new Coordenada(-1,-1), new Coordenada(-1,0), new Coordenada(-1,1), 
			new Coordenada(0,-1), new Coordenada(0,1),
				new Coordenada(1,-1), new Coordenada(1,0), new Coordenada(1,1)};		
	
	private Operaciones(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCarácteresAArregloInt(final String string){
		if(string.length() < NÚMERO_CONSTANTES_JUEGO) throw new IllegalArgumentException("Deben haber por lo menos 3 carácteres");  
		int[] arregloInt = new int[NÚMERO_CONSTANTES_JUEGO];	
		for(int carácter = 0; carácter < NÚMERO_CONSTANTES_JUEGO; carácter++){
			arregloInt[carácter] =  string.charAt(carácter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
	
	public static Célula[][] inicializar(final Célula[][] células){
		if(células == null) throw new IllegalArgumentException();  
		for (int columna = 0; columna < células.length ; ++columna) {
			for (int fila = 0; fila < células[columna].length ; ++fila) {
				células[columna][fila] = Célula.Muerta; 
			}		
		}
		return células; 
	}
	
	//ref: determina es el tamaño de la ventana que se va acopiar
	public static Célula[][] copiar(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref, final Coordenada inicioDestino, final Coordenada inicioFuente){	
		if(precondicionesFallan(destino, fuente, ref, inicioDestino, inicioFuente)) throw new IllegalArgumentException();
		
		for (int fila = 0; fila < ref[0].length; ++fila) {
			for (int columna = 0; columna < ref.length ; ++columna) {
				destino[columna + inicioDestino.x()][fila + inicioDestino.y()] = fuente[columna + inicioFuente.x()][fila + inicioFuente.y()];	
			}
		}
		return destino;
	}
	
	public static Célula[][] calcularGeneraciónSiguienteDeTrabajo(final Célula[][] generaciónActualDeTrabajo) {
		if(generaciónActualDeTrabajo == null) throw new IllegalArgumentException();  
		
		Célula[][] generaciónSiguiente = new Célula[generaciónActualDeTrabajo.length][generaciónActualDeTrabajo[0].length];
		inicializar(generaciónSiguiente);
		
		int númeroVecinasVivas = 0;
		for (int fila = 1; fila < generaciónActualDeTrabajo.length - 2 ; ++fila) {
			for (int columna = 1; columna < generaciónActualDeTrabajo[fila].length - 2 ; ++columna) {					
				númeroVecinasVivas = calcularVecinasVivas(generaciónActualDeTrabajo, fila, columna);				
				generaciónSiguiente = reglasGeneraciónSiguiente(generaciónActualDeTrabajo, generaciónSiguiente, númeroVecinasVivas, fila, columna);					
			}			
		}
		return generaciónSiguiente;
	}
	
	//..
	private static Célula[][] reglasGeneraciónSiguiente(final Célula[][] generaciónActual, final Célula[][] generaciónSiguiente, final int númeroVecinasVivas, final int fila, final int columna) {
		if(generaciónActual == null || generaciónSiguiente == null || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		generaciónSiguiente[columna][fila] = generaciónActual[columna][fila];	
		if(númeroVecinasVivas == 3) 						 generaciónSiguiente[columna][fila] = Célula.Viva;
		if(númeroVecinasVivas < 2 || númeroVecinasVivas > 3) generaciónSiguiente [columna][fila] = Célula.Muerta;
		return  generaciónSiguiente;
	}
	
	private static int calcularVecinasVivas(final Célula[][] generaciónActualDeTrabajo, final int fila, final int columna) {
		if(generaciónActualDeTrabajo == null || fila >= generaciónActualDeTrabajo.length || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		int númeroVecinasVivas = 0;				
		for (int i = 0; i < 8; ++i) {
			númeroVecinasVivas += generaciónActualDeTrabajo[columna - máscara[i].x()][fila - máscara[i].y()].ordinal();
		}		
		return númeroVecinasVivas;
	}
	
	private static boolean precondicionesFallan(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref,
															final Coordenada inicioDestino, final Coordenada inicioFuente) {
		
				return destino == null || fuente == null || ref == null || inicioFuente == null || inicioDestino == null || 
							precondicionesCoordenadas(fuente, inicioFuente) || precondicionesCoordenadas(destino, inicioDestino) ||
							precondicionesVentana(destino, fuente, ref, inicioDestino, inicioFuente); 
	}
	
	//asegura que la copia no salga de los limites de las matrices que se estan copiando
	private static boolean precondicionesVentana(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref,
			final Coordenada inicioDestino, final Coordenada inicioFuente) {
		
		return ref.length > (destino.length - inicioDestino.x()) || ref.length > (fuente.length - inicioFuente.x()) ||
				ref[0].length > (destino[0].length - inicioDestino.y()) || ref[0].length > (fuente[0].length - inicioFuente.y());
	}

	//asegura que las coordenadas esten dentro del arreglo
	private static boolean precondicionesCoordenadas(Célula[][] células, Coordenada coordenada){
		return coordenada.x() < 0 || coordenada.y() < 0 || coordenada.x() >= células.length || coordenada.y() >= células[0].length;
	}
	
}

