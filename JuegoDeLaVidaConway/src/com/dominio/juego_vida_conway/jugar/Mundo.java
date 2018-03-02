package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;
import static com.dominio.juego_vida_conway.utilidades.Matriz.*;

public class Mundo{
	
	private final C�lula[][] c�lulas;
	
	public enum C�lula {
		Muerta ,  
		Viva;
	}
	
	private Mundo(final C�lula[][] c�lulas){		
		this.c�lulas = Objects.requireNonNull(c�lulas);		
	}
	
	//Por defecto el mundo de Conway se llena con c�lulas muertas y tiene un tama�o minimo de 3 x 3
	private Mundo(final int m, final int n){
		this(new C�lula[m][n]);
		llenoDe(C�lula.Muerta);
	}
	
	//f�bricas est�ticas	
	public static Mundo de(final int m, final int n){
		if(m < 3 ||  n < 3) throw new IllegalArgumentException("El mundo de Conway debe ser al menos de 3 x 3");
		return new Mundo(m , n);
	}

	public static Mundo desdeArchivo(File archivoInicioJuego){
		return Archivo.leerMundo(archivoInicioJuego);		
	}
	/**
	 * 
	 * @return Archivo de inicio de juego desde ubicacion por defecto
	 */
	public static Mundo desdeArchivo(){
		return Archivo.leerMundo();		
	}

	
	public Mundo conC�lulaVivaEn(Coordenada coordenada){
		if(coordenada.x() >= n�meroColumnas() || coordenada.x() < 0 || coordenada.y() >= n�meroFilas() || coordenada.y() < 0) throw new IllegalArgumentException("Esta poniendo por c�lulas por fuera del mundo");
		c�lulas[coordenada.x()][coordenada.y()] = C�lula.Viva;				 
		return new Mundo(c�lulas); 
	}
	
	private Mundo llenoDe(final C�lula estadoC�lulas){
		return new Mundo(llenarConEstado(this.c�lulas, estadoC�lulas));
	} 
	
	private int n�meroColumnas(){
		return c�lulas.length;
	}
	
	private int n�meroFilas(){
		return c�lulas[0].length;
	}
	
	/**
	 * 
	 * @return una matriz agrandada en uno para simplificar la aplicacion de las reglas de calculo de la siguiente generaci�n
	 */
	private C�lula[][] arregloDeTrabajo(){
		return llenarConEstado(new C�lula[n�meroColumnas() + 2][n�meroFilas() + 2],C�lula.Muerta);
	}
	
	public Mundo nuevaGeneracion(){
		C�lula[][] generaci�nActual = arregloDeTrabajo();
		C�lula[][] generaci�nSiguiente = arregloDeTrabajo();
		
		generaci�nActual = copiar(generaci�nActual,this.c�lulas,this.c�lulas, new Delta(1,1),new Delta(0,0));
		
		int n�meroVecinasVivas = 0;
		for (int fila = 1; fila < n�meroFilas() + 1; ++fila) {
			for (int columna = 1; columna < n�meroColumnas() + 1 ; ++columna) {					
				n�meroVecinasVivas = calcularVecinasVivas(generaci�nActual, fila, columna);				
				generaci�nSiguiente[columna][fila] = generaci�nActual[columna][fila];	
				if(n�meroVecinasVivas == 3) generaci�nSiguiente[columna][fila] = C�lula.Viva;
				if(n�meroVecinasVivas < 2 || n�meroVecinasVivas > 3) generaci�nSiguiente [columna][fila] = C�lula.Muerta;				
			}
			
		}		
		return new Mundo(copiar(this.c�lulas, generaci�nSiguiente,this.c�lulas,new Delta(0,0),new Delta(1,1)));		
	}
		
	private static final char SALTO_DE_L�NEA = '\n';
	private static final char CAR�CTER_C�LULA_MUERTA = '.';
	private static final char CAR�CTER_C�LULA_VIVA = '*';
	
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		for (int filas = 0; filas < n�meroFilas() ; ++filas) {
			for (int columnas = 0; columnas < n�meroColumnas() ; ++columnas) {
				if(c�lulas[columnas][filas] == C�lula.Muerta) sb.append(CAR�CTER_C�LULA_MUERTA);
				if(c�lulas[columnas][filas] == C�lula.Viva) sb.append(CAR�CTER_C�LULA_VIVA);
			}
			sb.append(SALTO_DE_L�NEA);	
		}
		return sb.toString();		
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mundo otro = (Mundo) obj;
		if (!Arrays.deepEquals(c�lulas, otro.c�lulas))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + Arrays.deepHashCode(c�lulas);
		return resultado;
	}
	
}
