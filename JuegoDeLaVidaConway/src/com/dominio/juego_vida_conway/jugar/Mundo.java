package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;
import com.dominio.juego_vida_conway.utilidades.Precondiciones;

//import static com.dominio.juego_vida_conway.utilidades.Operaciones.*;

public final class Mundo{
	
	private final Célula[][] células;
	
	public static final String SALTO_DE_LÍNEA = String.format("%n");
	private static final Coordenada máscara[] = {new Coordenada(-1,-1), new Coordenada(-1,0), new Coordenada(-1,1), 
												 new Coordenada(0,-1),  					  new Coordenada(0,1),
												 new Coordenada(1,-1),  new Coordenada(1,0),  new Coordenada(1,1)};		
	
	public enum Célula {
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
		this.células = builder.células;
	}
	
	public static class Builder {
		
		private final Célula[][] células;
		
		//package-private para pruebas unitarias
		Builder(final Célula[][] células){
			this.células = Objects.requireNonNull(células);	
		}
		
		public Builder(final int m, final int n){
			this(new Célula[m][n]);
			inicializar(this.células);
		}
		
		public Builder(File archivoInicioJuego){
			this(Archivo.leerMundo(archivoInicioJuego).obtenerCélulas());					
		}

		public Builder(){
			this(Archivo.leerMundo().obtenerCélulas());		
		}
		
		public Builder llenoDe(final Célula estadoCélulas){
			llenar(células, estadoCélulas); 
			return this;
		} 
		
		public Builder conCélulaVivaEn(final Coordenada coordenada){
			return con(coordenada, Célula.VIVA);
		}
		
		public Builder conCélulaMuertaEn(final Coordenada coordenada){				 
			return con(coordenada, Célula.MUERTA);
		}
		
		public Mundo build(){
			if(células[0].length < 3 || células.length < 3) throw new IllegalArgumentException("El mundo de Conway debe ser al menos de 3 x 3");
			return new Mundo(this);			
		}
		
		private Builder con(Coordenada coordenada, Célula estado){
			if(coordenada.columna() >= células.length || coordenada.columna() < 0 || coordenada.fila() >= células[0].length || coordenada.fila() < 0) throw new IllegalArgumentException("Esta poniendo por células por fuera del mundo");
			células[coordenada.columna()][coordenada.fila()] = estado;
			return this; 
		}
			
	}//fin Builder
	
	public Mundo nuevaGeneración(){
		Célula[][] generaciónActualDeTrabajo = crearGeneraciónActualDeTrabajo(arregloDeTrabajo());		
		
		Mundo mundoSiguienteDeTrabajo = new Mundo.Builder((calcularGeneraciónSiguienteDeTrabajo(generaciónActualDeTrabajo))).build();
		asignarMundoSiguienteDeTrabajoAGeneraciónActual(mundoSiguienteDeTrabajo.obtenerCélulas());
	
		return copia();
	}
	
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		for (int fila = 0; fila < númeroFilas() ; ++fila) {
			sb.append(leerCarácter(fila)).append(SALTO_DE_LÍNEA);	
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
	
	//..
	Mundo copia(){
		Célula[][] nuevasCélulas = new Célula[númeroColumnas()][númeroFilas()];		
		return new Mundo.Builder(copiarCélulas(nuevasCélulas, this.células, this.células, new Coordenada(0,0), new Coordenada(0,0))).build();
	}
	
	private int númeroColumnas(){
		return células.length;
	}
	
	private int númeroFilas(){
		return células[0].length;
	}
	
	private Célula[][] obtenerCélulas(){
		return this.células;
	}
	
	private static Célula[][] inicializar(final Célula[][] células){
		return llenar(células, Célula.MUERTA); 
	}
	
	private static Célula[][] llenar(final Célula[][] células , final Célula estadoCélulas){
		if(células == null) throw new IllegalArgumentException();  				
		for (int columna = 0; columna < células.length ; ++columna) {
			for (int fila = 0; fila < células[columna].length ; ++fila) {
				células[columna][fila] = estadoCélulas; 
			}		
		}
		return células; 		
	}
	
	/**
	 * 
	 * @return una matriz agrandada en 2 para simplificar la aplicacion de las reglas de calculo de la siguiente generación e inicializada(con células muertas)
	 */
	private Célula[][] arregloDeTrabajo(){
		return inicializar(new Célula[númeroColumnas() + 2][númeroFilas() + 2]);
	}	
	
	private Célula[][] crearGeneraciónActualDeTrabajo(Célula[][] generaciónActualDeTrabajo) {
		return copiarCélulas(generaciónActualDeTrabajo, this.células, this.células, new Coordenada(1,1), new Coordenada(0,0));
	}
	
	private Mundo asignarMundoSiguienteDeTrabajoAGeneraciónActual(Célula[][] generaciónSiguienteDeTrabajo) {
		return new Mundo.Builder(copiarCélulas(this.células, generaciónSiguienteDeTrabajo, this.células, new Coordenada(0,0), new Coordenada(1,1))).build();		
	}
		
	private Célula[][] copiarCélulas(final Célula[][] destino, final Célula[][] fuente, final Célula[][] ref, final Coordenada inicioDestino, final Coordenada inicioFuente){	
		if(Precondiciones.precondicionesFallan(destino, fuente, ref, inicioDestino, inicioFuente)) throw new IllegalArgumentException();
		for (int fila = 0; fila < ref[0].length; ++fila) {
			for (int columna = 0; columna < ref.length ; ++columna) {
				destino[columna + inicioDestino.columna()][fila + inicioDestino.fila()] = fuente[columna + inicioFuente.columna()][fila + inicioFuente.fila()];	
			}
		}
		return destino;
	}
	
	 Célula[][] calcularGeneraciónSiguienteDeTrabajo(final Célula[][] generaciónActualDeTrabajo) {
		if(generaciónActualDeTrabajo == null) throw new IllegalArgumentException();  
		
		Célula[][] generaciónSiguiente = new Célula[generaciónActualDeTrabajo.length][generaciónActualDeTrabajo[0].length];
		inicializar(generaciónSiguiente);
		
		return generaciónSiguiente(generaciónActualDeTrabajo, generaciónSiguiente);
	}

	private Célula[][] generaciónSiguiente(final Célula[][] generaciónActualDeTrabajo, Célula[][] generaciónSiguiente) {
		int númeroVecinasVivas = 0;
		for (int fila = 1; fila < generaciónActualDeTrabajo[0].length - 1 ; ++fila) {
			for (int columna = 1; columna < generaciónActualDeTrabajo.length - 1 ; ++columna) {					
				númeroVecinasVivas = calcularVecinasVivas(generaciónActualDeTrabajo, fila, columna);				
				generaciónSiguiente = reglasGeneraciónSiguiente(generaciónActualDeTrabajo, generaciónSiguiente, númeroVecinasVivas, fila, columna);					
			}			
		}
		return generaciónSiguiente;
	}
	
	private Célula[][] reglasGeneraciónSiguiente(final Célula[][] generaciónActual, final Célula[][] generaciónSiguiente, final int númeroVecinasVivas, final int fila, final int columna) {
		if(generaciónActual == null || generaciónSiguiente == null || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		generaciónSiguiente[columna][fila] = generaciónActual[columna][fila];	
		if(númeroVecinasVivas == 3) 						 generaciónSiguiente[columna][fila] = Célula.VIVA;
		if(númeroVecinasVivas < 2 || númeroVecinasVivas > 3) generaciónSiguiente [columna][fila] = Célula.MUERTA;
		return  generaciónSiguiente;
	}
	
	private static int calcularVecinasVivas(final Célula[][] generaciónActualDeTrabajo, final int fila, final int columna) {
		if(generaciónActualDeTrabajo == null || fila >= generaciónActualDeTrabajo.length || fila < 0 || columna < 0) throw new IllegalArgumentException();  		
		int númeroVecinasVivas = 0;				
		for (int i = 0; i < máscara.length; ++i) {
			númeroVecinasVivas += generaciónActualDeTrabajo[columna - máscara[i].columna()][fila - máscara[i].fila()].ordinal();
		}		
		return númeroVecinasVivas;
	}
	
	private String leerCarácter(final int fila){
		StringBuilder sb = new StringBuilder();
		for (int columna = 0; columna < númeroColumnas() ; ++columna) {
			if(células[columna][fila] == Célula.MUERTA) sb.append(Célula.MUERTA);
			if(células[columna][fila] == Célula.VIVA) sb.append(Célula.VIVA);
		}
		return sb.toString();		
	}	
	

}
	
