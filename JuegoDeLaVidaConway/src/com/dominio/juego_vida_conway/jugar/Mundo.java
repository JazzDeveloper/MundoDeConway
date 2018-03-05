package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;
import static com.dominio.juego_vida_conway.utilidades.Operaciones.*;

public class Mundo{
	
	private final C�lula[][] c�lulas;
	public static final char SALTO_DE_L�NEA = '\n';
	public static final char CAR�CTER_C�LULA_MUERTA = '.';
	public static final char CAR�CTER_C�LULA_VIVA = '*';
	
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
	public static Mundo de(final int n�meroFilas, final int n�meroColumnas){
		if(n�meroFilas < 3 ||  n�meroColumnas < 3) throw new IllegalArgumentException("El mundo de Conway debe ser al menos de 3 x 3");
		return new Mundo(n�meroFilas , n�meroColumnas);
	}

	public static Mundo desdeArchivo(File archivoInicioJuego){
		return Archivo.leerMundo(archivoInicioJuego);		
	}

	public static Mundo desdeArchivo(){
		return Archivo.leerMundo();		
	}
	//fin f�bricas
	
	public Mundo conC�lulaVivaEn(Coordenada coordenada){
		return con(coordenada, C�lula.Viva);
	}
	
	public Mundo conC�lulaMuertaEn(Coordenada coordenada){				 
		return con(coordenada, C�lula.Muerta);
	}

	//..
	private C�lula[][] obtenerC�lulas(){
		return this.c�lulas;
	}
	
	public String nuevaGeneraci�n(){
		C�lula[][] generaci�nActualDeTrabajo = crearGeneraci�nActualDeTrabajo(arregloDeTrabajo());		
		Mundo mundoSiguienteDeTrabajo = new Mundo(calcularGeneraci�nSiguienteDeTrabajo(generaci�nActualDeTrabajo));
		
		return asignarMundoSiguienteDeTrabajoAGeneraci�nActual(mundoSiguienteDeTrabajo.obtenerC�lulas()).toString();
	}
	
	//..
	private Mundo con(Coordenada coordenada, C�lula estado){
		if(coordenada.x() >= n�meroColumnas() || coordenada.x() < 0 || coordenada.y() >= n�meroFilas() || coordenada.y() < 0) throw new IllegalArgumentException("Esta poniendo por c�lulas por fuera del mundo");
		c�lulas[coordenada.x()][coordenada.y()] = estado;
		return new Mundo(c�lulas); 
	}
	
	private Mundo llenoDe(final C�lula estadoC�lulas){
		return new Mundo(inicializar(this.c�lulas));
	} 
	
	private int n�meroColumnas(){
		return c�lulas.length;
	}
	
	private int n�meroFilas(){
		return c�lulas[0].length;
	}

	/**
	 * 
	 * @return una matriz agrandada en uno para simplificar la aplicacion de las reglas de calculo de la siguiente generaci�n e inicializada(con c�lulas muertas)
	 */
	private C�lula[][] arregloDeTrabajo(){
		return inicializar(new C�lula[n�meroColumnas() + 2][n�meroFilas() + 2]);
	}	

	private Mundo asignarMundoSiguienteDeTrabajoAGeneraci�nActual(C�lula[][] generaci�nSiguienteDeTrabajo) {
		return new Mundo(copiar(this.c�lulas, generaci�nSiguienteDeTrabajo, this.c�lulas, new Coordenada(0,0), new Coordenada(1,1)));		
	}
	
	private C�lula[][] crearGeneraci�nActualDeTrabajo(C�lula[][] generaci�nActualDeTrabajo) {
		return copiar(generaci�nActualDeTrabajo,this.c�lulas,this.c�lulas, new Coordenada(1,1),new Coordenada(0,0));
	}
	
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
