package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;
import com.dominio.juego_vida_conway.utilidades.Precondiciones;

//import static com.dominio.juego_vida_conway.utilidades.Operaciones.*;

public final class Mundo{
	
	private final C�lula[][] c�lulas;
	
	public static final String SALTO_DE_L�NEA = String.format("%n");
	private static final Coordenada m�scara[] = {new Coordenada(-1,-1), new Coordenada(-1,0), new Coordenada(-1,1), 
												 new Coordenada(0,-1),  					  new Coordenada(0,1),
												 new Coordenada(1,-1),  new Coordenada(1,0),  new Coordenada(1,1)};		
	
	public enum C�lula {
		MUERTA {
			@Override
			public String toString() {
				return ".";
			}			
		}, 
		VIVA {
			@Override
			public String toString() {
				return "*";
			}
		}
	}
	
	//Builder
	private Mundo(Builder builder) {
		this.c�lulas = builder.c�lulas;
	}
	
	public static class Builder {
		
		private final C�lula[][] c�lulas;
		
		//package-private para pruebas unitarias
		Builder(final C�lula[][] c�lulas){
			this.c�lulas = Objects.requireNonNull(c�lulas);	
		}
		
		public Builder(final int m, final int n){
			this(new C�lula[m][n]);
			inicializar(this.c�lulas);
		}
		
		public Builder(File archivoInicioJuego){
			this(Archivo.leerMundo(archivoInicioJuego).obtenerC�lulas());					
		}

		public Builder(){
			this(Archivo.leerMundo().obtenerC�lulas());		
		}
		
		public Builder llenoDe(final C�lula estadoC�lulas){
			llenar(c�lulas, estadoC�lulas); 
			return this;
		} 
		
		public Builder conC�lulaVivaEn(final Coordenada coordenada){
			return con(coordenada, C�lula.VIVA);
		}
		
		public Builder conC�lulaMuertaEn(final Coordenada coordenada){				 
			return con(coordenada, C�lula.MUERTA);
		}
		
		public Mundo build(){
			if(c�lulas[0].length < 3 || c�lulas.length < 3) throw new IllegalArgumentException("El mundo de Conway debe ser al menos de 3 x 3");
			return new Mundo(this);			
		}
		
		private Builder con(Coordenada coordenada, C�lula estado){
			if(coordenada.columna() >= c�lulas.length || coordenada.columna() < 0 || coordenada.fila() >= c�lulas[0].length || coordenada.fila() < 0) throw new IllegalArgumentException("Esta poniendo por c�lulas por fuera del mundo");
			c�lulas[coordenada.columna()][coordenada.fila()] = estado;
			return this; 
		}
			
	}//fin Builder
	
	public Mundo nuevaGeneraci�n(){
		C�lula[][] generaci�nActualDeTrabajo = crearGeneraci�nActualDeTrabajo(arregloDeTrabajo());		
		
		Mundo mundoSiguienteDeTrabajo = new Mundo.Builder((calcularGeneraci�nSiguienteDeTrabajo(generaci�nActualDeTrabajo))).build();
		asignarMundoSiguienteDeTrabajoAGeneraci�nActual(mundoSiguienteDeTrabajo.obtenerC�lulas());
	
		return copia();
	}
	
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		for (int fila = 0; fila < n�meroFilas() ; ++fila) {
			sb.append(leerCar�cter(fila)).append(SALTO_DE_L�NEA);	
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
	
	//..
	Mundo copia(){
		C�lula[][] nuevasC�lulas = new C�lula[n�meroColumnas()][n�meroFilas()];		
		return new Mundo.Builder(copiarC�lulas(nuevasC�lulas, this.c�lulas, this.c�lulas, new Coordenada(0,0), new Coordenada(0,0))).build();
	}
	
	private int n�meroColumnas(){
		return c�lulas.length;
	}
	
	private int n�meroFilas(){
		return c�lulas[0].length;
	}
	
	private C�lula[][] obtenerC�lulas(){
		return this.c�lulas;
	}
	
	private static C�lula[][] inicializar(final C�lula[][] c�lulas){
		return llenar(c�lulas, C�lula.MUERTA); 
	}
	
	private static C�lula[][] llenar(final C�lula[][] c�lulas , final C�lula estadoC�lulas){
		if(c�lulas == null) throw new IllegalArgumentException();  				
		for (int columna = 0; columna < c�lulas.length ; ++columna) {
			for (int fila = 0; fila < c�lulas[columna].length ; ++fila) {
				c�lulas[columna][fila] = estadoC�lulas; 
			}		
		}
		return c�lulas; 		
	}
	
	/**
	 * 
	 * @return una matriz agrandada en 2 para simplificar la aplicacion de las reglas de calculo de la siguiente generaci�n e inicializada(con c�lulas muertas)
	 */
	private C�lula[][] arregloDeTrabajo(){
		return inicializar(new C�lula[n�meroColumnas() + 2][n�meroFilas() + 2]);
	}	
	
	private C�lula[][] crearGeneraci�nActualDeTrabajo(C�lula[][] generaci�nActualDeTrabajo) {
		return copiarC�lulas(generaci�nActualDeTrabajo, this.c�lulas, this.c�lulas, new Coordenada(1,1), new Coordenada(0,0));
	}
	
	private Mundo asignarMundoSiguienteDeTrabajoAGeneraci�nActual(C�lula[][] generaci�nSiguienteDeTrabajo) {
		return new Mundo.Builder(copiarC�lulas(this.c�lulas, generaci�nSiguienteDeTrabajo, this.c�lulas, new Coordenada(0,0), new Coordenada(1,1))).build();		
	}
		
	private C�lula[][] copiarC�lulas(final C�lula[][] destino, final C�lula[][] fuente, final C�lula[][] ref, final Coordenada inicioDestino, final Coordenada inicioFuente){	
		if(Precondiciones.precondicionesFallan(destino, fuente, ref, inicioDestino, inicioFuente)) throw new IllegalArgumentException();
		for (int fila = 0; fila < ref[0].length; ++fila) {
			for (int columna = 0; columna < ref.length ; ++columna) {
				destino[columna + inicioDestino.columna()][fila + inicioDestino.fila()] = fuente[columna + inicioFuente.columna()][fila + inicioFuente.fila()];	
			}
		}
		return destino;
	}
	
	 C�lula[][] calcularGeneraci�nSiguienteDeTrabajo(final C�lula[][] generaci�nActualDeTrabajo) {
		if(generaci�nActualDeTrabajo == null) throw new IllegalArgumentException();  
		
		C�lula[][] generaci�nSiguiente = new C�lula[generaci�nActualDeTrabajo.length][generaci�nActualDeTrabajo[0].length];
		inicializar(generaci�nSiguiente);
		
		return generaci�nSiguiente(generaci�nActualDeTrabajo, generaci�nSiguiente);
	}

	private C�lula[][] generaci�nSiguiente(final C�lula[][] generaci�nActualDeTrabajo, C�lula[][] generaci�nSiguiente) {
		int n�meroVecinasVivas = 0;
		for (int fila = 1; fila < generaci�nActualDeTrabajo[0].length - 1 ; ++fila) {
			for (int columna = 1; columna < generaci�nActualDeTrabajo.length - 1 ; ++columna) {					
				n�meroVecinasVivas = calcularVecinasVivas(generaci�nActualDeTrabajo, fila, columna);				
				generaci�nSiguiente = reglasGeneraci�nSiguiente(generaci�nActualDeTrabajo, generaci�nSiguiente, n�meroVecinasVivas, fila, columna);					
			}			
		}
		return generaci�nSiguiente;
	}
	
	private C�lula[][] reglasGeneraci�nSiguiente(final C�lula[][] generaci�nActual, final C�lula[][] generaci�nSiguiente, final int n�meroVecinasVivas, final int fila, final int columna) {
		if(generaci�nActual == null || generaci�nSiguiente == null || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		generaci�nSiguiente[columna][fila] = generaci�nActual[columna][fila];	
		if(n�meroVecinasVivas == 3) 						 generaci�nSiguiente[columna][fila] = C�lula.VIVA;
		if(n�meroVecinasVivas < 2 || n�meroVecinasVivas > 3) generaci�nSiguiente [columna][fila] = C�lula.MUERTA;
		return  generaci�nSiguiente;
	}
	
	private static int calcularVecinasVivas(final C�lula[][] generaci�nActualDeTrabajo, final int fila, final int columna) {
		if(generaci�nActualDeTrabajo == null || fila >= generaci�nActualDeTrabajo.length || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		int n�meroVecinasVivas = 0;				
		for (int i = 0; i < m�scara.length; ++i) {
			n�meroVecinasVivas += generaci�nActualDeTrabajo[columna - m�scara[i].columna()][fila - m�scara[i].fila()].ordinal();
		}		
		return n�meroVecinasVivas;
	}
	
	private String leerCar�cter(final int fila){
		StringBuilder sb = new StringBuilder();
		for (int columna = 0; columna < n�meroColumnas() ; ++columna) {
			if(c�lulas[columna][fila] == C�lula.MUERTA) sb.append(C�lula.MUERTA);
			if(c�lulas[columna][fila] == C�lula.VIVA) sb.append(C�lula.VIVA);
		}
		return sb.toString();		
	}	
	

}
	
