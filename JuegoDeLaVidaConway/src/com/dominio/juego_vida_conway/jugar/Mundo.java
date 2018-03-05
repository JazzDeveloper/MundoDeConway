package com.dominio.juego_vida_conway.jugar;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;
import static com.dominio.juego_vida_conway.utilidades.Operaciones.*;

public class Mundo{
	
	private final Célula[][] células;
	public static final char SALTO_DE_LÍNEA = '\n';
	public static final char CARÁCTER_CÉLULA_MUERTA = '.';
	public static final char CARÁCTER_CÉLULA_VIVA = '*';
	
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
	public static Mundo de(final int númeroFilas, final int númeroColumnas){
		if(númeroFilas < 3 ||  númeroColumnas < 3) throw new IllegalArgumentException("El mundo de Conway debe ser al menos de 3 x 3");
		return new Mundo(númeroFilas , númeroColumnas);
	}

	public static Mundo desdeArchivo(File archivoInicioJuego){
		return Archivo.leerMundo(archivoInicioJuego);		
	}

	public static Mundo desdeArchivo(){
		return Archivo.leerMundo();		
	}
	//fin fábricas
	
	public Mundo conCélulaVivaEn(Coordenada coordenada){
		return con(coordenada, Célula.Viva);
	}
	
	public Mundo conCélulaMuertaEn(Coordenada coordenada){				 
		return con(coordenada, Célula.Muerta);
	}

	//..
	private Célula[][] obtenerCélulas(){
		return this.células;
	}
	
	public String nuevaGeneración(){
		Célula[][] generaciónActualDeTrabajo = crearGeneraciónActualDeTrabajo(arregloDeTrabajo());		
		Mundo mundoSiguienteDeTrabajo = new Mundo(calcularGeneraciónSiguienteDeTrabajo(generaciónActualDeTrabajo));
		
		return asignarMundoSiguienteDeTrabajoAGeneraciónActual(mundoSiguienteDeTrabajo.obtenerCélulas()).toString();
	}
	
	//..
	private Mundo con(Coordenada coordenada, Célula estado){
		if(coordenada.x() >= númeroColumnas() || coordenada.x() < 0 || coordenada.y() >= númeroFilas() || coordenada.y() < 0) throw new IllegalArgumentException("Esta poniendo por células por fuera del mundo");
		células[coordenada.x()][coordenada.y()] = estado;
		return new Mundo(células); 
	}
	
	private Mundo llenoDe(final Célula estadoCélulas){
		return new Mundo(inicializar(this.células));
	} 
	
	private int númeroColumnas(){
		return células.length;
	}
	
	private int númeroFilas(){
		return células[0].length;
	}

	/**
	 * 
	 * @return una matriz agrandada en uno para simplificar la aplicacion de las reglas de calculo de la siguiente generación e inicializada(con células muertas)
	 */
	private Célula[][] arregloDeTrabajo(){
		return inicializar(new Célula[númeroColumnas() + 2][númeroFilas() + 2]);
	}	

	private Mundo asignarMundoSiguienteDeTrabajoAGeneraciónActual(Célula[][] generaciónSiguienteDeTrabajo) {
		return new Mundo(copiar(this.células, generaciónSiguienteDeTrabajo, this.células, new Coordenada(0,0), new Coordenada(1,1)));		
	}
	
	private Célula[][] crearGeneraciónActualDeTrabajo(Célula[][] generaciónActualDeTrabajo) {
		return copiar(generaciónActualDeTrabajo,this.células,this.células, new Coordenada(1,1),new Coordenada(0,0));
	}
	
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
