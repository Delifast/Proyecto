package com.delifast.dashboard.graphic.util;

import java.util.ArrayList;
import java.util.List;

public class Graficador {
	private List<DataSet> datasets;
	private DataSet dataset;

	public Graficador() {
		this.datasets = new ArrayList<>(10);
		this.dataset = new DataSet();
	}

	public void agregarDataDataSet(int[] data) {
		this.dataset.setData(data);
	}

	public void agregarBackgroundColorDataSet(String[] backgroundcolor) {
		this.dataset.setBackgroundColor(backgroundcolor);
	}

	public void agregarLabelDataSet(String backgroundcolor) {
		this.dataset.setLabel(backgroundcolor);
	}
	
	public void agregarDataSetToGrafica() {
		this.datasets.add(this.dataset);
		this.dataset = new DataSet();
	}
	
	public void resetearPropiedades() {
		this.datasets = new ArrayList<>(10);
		this.dataset = new DataSet();
	}

	public Grafica generarGrafica(String type, String title, String[] labels) {
		Grafica grafica = new Grafica();
		grafica.setLabels(labels);
		grafica.setTitle(title);
		grafica.setType(type);
		grafica.setDatasets(this.datasets);
		this.datasets = new ArrayList<DataSet>();
		return grafica;
	}

}
