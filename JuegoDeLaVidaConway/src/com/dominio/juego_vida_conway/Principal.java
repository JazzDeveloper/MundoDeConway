package com.dominio.juego_vida_conway;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.dominio.juego_vida_conway.utilidades.Archivo;

public class Principal {
    
	public static void main(String[] args) {
		
		Mundo mc = Mundo.de(3, 3).conCélulaVivaEn(1, 1).conCélulaVivaEn(2, 2);
		File archivoInicioJuego = new File(Archivo.RUTA_DE_ARCHIVO_INICIO_JUEGO);		
		System.out.println(mc);
		System.out.println("-----------------------------");	
		System.out.println(Mundo.desdeArchivo(archivoInicioJuego));	
		
	}
	
	static void logicaMutacionMundo(){
		//Path rutaDeArchivoInicioJuego = Paths.get(RUTA_DE_ARCHIVO_INICIAR_JUEGO);
		System.out.println("");								
		
		int mIngresado = 10;		
		int nIngresado = 10;
		
		int m = mIngresado + 2;		
		int n = nIngresado + 2;
		
		int g = 2;
		
		int [][] tablero = new int [m][n];//actual
		int [][] tableroX = new int [m][n];//futura
		
		tablero [3][3] = 1;
		tablero [4][3] = 1;
		tablero [5][3] = 1;
			
		//grafico generacion 0
		for (int j = 1; j < n - 1; j++ ) {
			for (int i = 1; i < m - 1 ; i++ ) {
				System.out.print(tablero[i][j] + " ");
				
			}
			System.out.println("");			
		}
	
		System.out.println("---------------------------");			
				
		
		for(int gen = 1; gen <= g ; gen++){//generacion
			
			int suma;
			//tablero = tableroX;
			for (int j = 1; j < n - 1; j++ ) {
				for (int i = 1; i < m - 1 ; i++ ) {
					//condiciones de la generacion siguiente
					suma = tablero[i - 1][j - 1] + tablero[i][j - 1] + tablero[i + 1][j - 1] +
							tablero[i - 1][j] + tablero[i + 1][j] +
								tablero[i - 1][j + 1] + tablero[i][j + 1] + tablero[i + 1][j + 1];
					
					//asignar a tableroX la siguiente generacion
					tableroX [i][j] = tablero [i][j];	
					if(suma == 3) tableroX [i][j] = 1;
					if(suma < 2 || suma > 3) tableroX [i][j] = 0;
					
				}
				//System.out.println("");			
				
			}
			//copiando la generacion futura en la actual para que sea la entrada de la siguiente
			for (int j = 1; j < n - 1; j++ ) {
				for (int i = 1; i < m - 1 ; i++ ) {
					tablero [i][j] = tableroX [i][j];	
				}
			}
			
			System.out.println("........................");	
		
			//grafico de cada generacion
			System.out.println("---------------------------");			
			for (int j = 1; j < n - 1; j++ ) {
				for (int i = 1; i < m - 1 ; i++ ) {
					System.out.print(tablero[i][j] + " ");
					
				}
				System.out.println("");			
			}
		}
		
	}

}






