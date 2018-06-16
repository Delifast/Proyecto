package com.delifast.dashboard.graphic;

import java.util.Random;

public class Complementos {

	private Complementos() {
	}

	public static class Color {
		public final static String ROJO = "rgb(255, 99, 132)";
		public final static String NARANJA = "rgb(255, 159, 64)";
		public final static String AMARILLO = "rgb(255, 205, 86)";
		public final static String VERDE = "rgb(75, 192, 192)";
		public final static String AZUL = "rgb(54, 162, 235)";
		public final static String MORADO = "rgb(153, 102, 255)";
		public final static String PLOMO = "rgb(201, 203, 207)";
		public final static String RANDOM = "rgb(" + Data.numeroAleatorio() + "," + Data.numeroAleatorio() + ", " + Data.numeroAleatorio() + ")";
		
		public static String[] arrayColores(int leng) {
			
			String[] colores = null;
			
			if(leng == 1) {
				colores = new String[]{
						RANDOM	
				};
				
			}else if(leng == 2){
				colores = new String[]{
						ROJO,
						NARANJA
				};
			}else if(leng == 3) {
				colores = new String[]{
						ROJO,
						NARANJA,
						AMARILLO
				};
			}else if(leng == 4) {
				colores = new String[]{
						ROJO,
						NARANJA,
						AMARILLO,
						VERDE
				};
			}else if(leng == 5) {
				colores = new String[]{
						ROJO,
						NARANJA,
						AMARILLO,
						VERDE,
						AZUL
				};
			}else if(leng == 6){
				colores = new String[]{
						ROJO,
						NARANJA,
						AMARILLO,
						VERDE,
						AZUL,
						MORADO
				};
			}else if(leng == 7) {
				colores = new String[]{
						ROJO,
						NARANJA,
						AMARILLO,
						VERDE,
						AZUL,
						MORADO,
						PLOMO
				};
			}else {
				colores = new String[]{
						ROJO,
						NARANJA,
						AMARILLO,
						VERDE,
						AZUL,
						MORADO,
						PLOMO,
						RANDOM
				};
			}
			
			return colores;
		}
		
		private Color() {
		}
	}

	public static class Data {
		public static int numeroAleatorio() {
			Random r = new Random();
			return r.nextInt(250 - 80 + 1);
		}

		public static int[] arrayAleatorio(int leng) {
			int[] array = new int[leng];
			
			for (int i = 0; i < leng; i++) {
				array[i] = numeroAleatorio();
			}
			
			return array;
		}
		
		private Data() {
		}
	}

}
