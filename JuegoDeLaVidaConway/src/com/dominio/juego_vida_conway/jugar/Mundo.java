package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;
import static com.dominio.juego_vida_conway.utilidades.Matriz.*;

public class Mundo{
	
	private final Célula[][] células;
	
	public enum Célula {
		Muerta ,  
		Viva;
	}
	
	private Mundo(final Célula[][] células){		
		this.células = Objects.requireNonNull(células);		
	}
	
	//Por defecto el mundo de Conway se llena con células muertas y tiene un tamaño minimo de 3 x 3
	private Mundo(final int m, final int n){
		this(new Célula[m][n]);
		llenoDe(Célula.Muerta);
	}
	
	//fábricas estáticas	
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

	
	public Mundo conCélulaVivaEn(Coordenada coordenada){
		if(coordenada.x() >= númeroColumnas() || coordenada.x() < 0 || coordenada.y() >= númeroFilas() || coordenada.y() < 0) throw new IllegalArgumentException("Esta poniendo por células por fuera del mundo");
		células[coordenada.x()][coordenada.y()] = Célula.Viva;				 
		return new Mundo(células); 
	}
	
	private Mundo llenoDe(final Célula estadoCélulas){
		return new Mundo(llenarConEstado(this.células, estadoCélulas));
	} 
	
	private int númeroColumnas(){
		return células.length;
	}
	
	private int númeroFilas(){
		return células[0].length;
	}
	
	/**
	 * 
	 * @return una matriz agrandada en uno para simplificar la aplicacion de las reglas de calculo de la siguiente generación
	 */
	private Célula[][] arregloDeTrabajo(){
		return llenarConEstado(new Célula[númeroColumnas() + 2][númeroFilas() + 2],Célula.Muerta);
	}
	
	public Mundo nuevaGeneracion(){
		Célula[][] generaciónActual = arregloDeTrabajo();
		Célula[][] generaciónSiguiente = arregloDeTrabajo();
		
		generaciónActual = copiar(generaciónActual,this.células,this.células, new Delta(1,1),new Delta(0,0));
		
		int númeroVecinasVivas = 0;
		for (int fila = 1; fila < númeroFilas() + 1; ++fila) {
			for (int columna = 1; columna < númeroColumnas() + 1 ; ++columna) {					
				númeroVecinasVivas = calcularVecinasVivas(generaciónActual, fila, columna);				
				generaciónSiguiente[columna][fila] = generaciónActual[columna][fila];	
				if(númeroVecinasVivas == 3) generaciónSiguiente[columna][fila] = Célula.Viva;
				if(númeroVecinasVivas < 2 || númeroVecinasVivas > 3) generaciónSiguiente [columna][fila] = Célula.Muerta;				
			}
			
		}		
		return new Mundo(copiar(this.células, generaciónSiguiente,this.células,new Delta(0,0),new Delta(1,1)));		
	}
		
	private static final char SALTO_DE_LÍNEA = '\n';
	private static final char CARÁCTER_CÉLULA_MUERTA = '.';
	private static final char CARÁCTER_CÉLULA_VIVA = '*';
	
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		for (int filas = 0; filas < númeroFilas() ; ++filas) {
			for (int columnas = 0; columnas < númeroColumnas() ; ++columnas) {
				if(células[columnas][filas] == Célula.Muerta) sb.append(CARÁCTER_CÉLULA_MUERTA);
				if(células[columnas][filas] == Célula.Viva) sb.append(CARÁCTER_CÉLULA_VIVA);
			}
			sb.append(SALTO_DE_LÍNEA);	
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
		if (!Arrays.deepEquals(células, otro.células))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + Arrays.deepHashCode(células);
		return resultado;
	}
	
}
