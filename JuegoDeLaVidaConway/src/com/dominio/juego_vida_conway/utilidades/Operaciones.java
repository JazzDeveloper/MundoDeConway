package com.dominio.juego_vida_conway.utilidades;

import com.dominio.juego_vida_conway.jugar.Coordenada;
import com.dominio.juego_vida_conway.jugar.Mundo.C�lula;

public final class Operaciones {
	
	private static final int N�MERO_CONSTANTES_JUEGO = 3;//n�mero filas generaci�n cero, n�mero columnas generaci�n cero, generaciones
	private static final int CONSTANTE_ASCII = 48;
	private static final Coordenada m�scara[] = {new Coordenada(-1,-1), new Coordenada(-1,0), new Coordenada(-1,1), 
			new Coordenada(0,-1), new Coordenada(0,1),
				new Coordenada(1,-1), new Coordenada(1,0), new Coordenada(1,1)};		
	
	private Operaciones(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static int[] cadenaDeCar�cteresAArregloInt(final String string){
		if(string.length() < N�MERO_CONSTANTES_JUEGO) throw new IllegalArgumentException("Deben haber por lo menos 3 car�cteres");  
		int[] arregloInt = new int[N�MERO_CONSTANTES_JUEGO];	
		for(int car�cter = 0; car�cter < N�MERO_CONSTANTES_JUEGO; car�cter++){
			arregloInt[car�cter] =  string.charAt(car�cter) - CONSTANTE_ASCII;
		}		
		return arregloInt;		
	}
	
	public static C�lula[][] inicializar(final C�lula[][] c�lulas){
		if(c�lulas == null) throw new IllegalArgumentException();  
		for (int columna = 0; columna < c�lulas.length ; ++columna) {
			for (int fila = 0; fila < c�lulas[columna].length ; ++fila) {
				c�lulas[columna][fila] = C�lula.Muerta; 
			}		
		}
		return c�lulas; 
	}
	
	//ref: determina es el tama�o de la ventana que se va acopiar
	public static C�lula[][] copiar(final C�lula[][] destino, final C�lula[][] fuente, final C�lula[][] ref, final Coordenada inicioDestino, final Coordenada inicioFuente){	
		if(precondicionesFallan(destino, fuente, ref, inicioDestino, inicioFuente)) throw new IllegalArgumentException();
		
		for (int fila = 0; fila < ref[0].length; ++fila) {
			for (int columna = 0; columna < ref.length ; ++columna) {
				destino[columna + inicioDestino.x()][fila + inicioDestino.y()] = fuente[columna + inicioFuente.x()][fila + inicioFuente.y()];	
			}
		}
		return destino;
	}
	
	public static C�lula[][] calcularGeneraci�nSiguienteDeTrabajo(final C�lula[][] generaci�nActualDeTrabajo) {
		if(generaci�nActualDeTrabajo == null) throw new IllegalArgumentException();  
		
		C�lula[][] generaci�nSiguiente = new C�lula[generaci�nActualDeTrabajo.length][generaci�nActualDeTrabajo[0].length];
		inicializar(generaci�nSiguiente);
		
		int n�meroVecinasVivas = 0;
		for (int fila = 1; fila < generaci�nActualDeTrabajo.length - 2 ; ++fila) {
			for (int columna = 1; columna < generaci�nActualDeTrabajo[fila].length - 2 ; ++columna) {					
				n�meroVecinasVivas = calcularVecinasVivas(generaci�nActualDeTrabajo, fila, columna);				
				generaci�nSiguiente = reglasGeneraci�nSiguiente(generaci�nActualDeTrabajo, generaci�nSiguiente, n�meroVecinasVivas, fila, columna);					
			}			
		}
		return generaci�nSiguiente;
	}
	
	//..
	private static C�lula[][] reglasGeneraci�nSiguiente(final C�lula[][] generaci�nActual, final C�lula[][] generaci�nSiguiente, final int n�meroVecinasVivas, final int fila, final int columna) {
		if(generaci�nActual == null || generaci�nSiguiente == null || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		generaci�nSiguiente[columna][fila] = generaci�nActual[columna][fila];	
		if(n�meroVecinasVivas == 3) 						 generaci�nSiguiente[columna][fila] = C�lula.Viva;
		if(n�meroVecinasVivas < 2 || n�meroVecinasVivas > 3) generaci�nSiguiente [columna][fila] = C�lula.Muerta;
		return  generaci�nSiguiente;
	}
	
	private static int calcularVecinasVivas(final C�lula[][] generaci�nActualDeTrabajo, final int fila, final int columna) {
		if(generaci�nActualDeTrabajo == null || fila >= generaci�nActualDeTrabajo.length || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		int n�meroVecinasVivas = 0;				
		for (int i = 0; i < 8; ++i) {
			n�meroVecinasVivas += generaci�nActualDeTrabajo[columna - m�scara[i].x()][fila - m�scara[i].y()].ordinal();
		}		
		return n�meroVecinasVivas;
	}
	
	private static boolean precondicionesFallan(final C�lula[][] destino, final C�lula[][] fuente, final C�lula[][] ref,
															final Coordenada inicioDestino, final Coordenada inicioFuente) {
		
				return destino == null || fuente == null || ref == null || inicioFuente == null || inicioDestino == null || 
							precondicionesCoordenadas(fuente, inicioFuente) || precondicionesCoordenadas(destino, inicioDestino) ||
							precondicionesVentana(destino, fuente, ref, inicioDestino, inicioFuente); 
	}
	
	//asegura que la copia no salga de los limites de las matrices que se estan copiando
	private static boolean precondicionesVentana(final C�lula[][] destino, final C�lula[][] fuente, final C�lula[][] ref,
			final Coordenada inicioDestino, final Coordenada inicioFuente) {
		
		return ref.length > (destino.length - inicioDestino.x()) || ref.length > (fuente.length - inicioFuente.x()) ||
				ref[0].length > (destino[0].length - inicioDestino.y()) || ref[0].length > (fuente[0].length - inicioFuente.y());
	}

	//asegura que las coordenadas esten dentro del arreglo
	private static boolean precondicionesCoordenadas(C�lula[][] c�lulas, Coordenada coordenada){
		return coordenada.x() < 0 || coordenada.y() < 0 || coordenada.x() >= c�lulas.length || coordenada.y() >= c�lulas[0].length;
	}
	
}

