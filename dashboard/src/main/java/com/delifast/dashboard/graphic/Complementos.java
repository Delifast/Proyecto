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

		private Color() {
		}
	}

	public static class Data {
		public static int numeroAleatorio() {
			Random r = new Random();
			return r.nextInt(250 - 50 + 1);
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
