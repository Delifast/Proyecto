package com.delifast.dashboard.graphic.util;

import java.util.List;

public class Grafica {

	private String type;
	private List<DataSet> datasets;
	private String title;
	private String[] labels;

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DataSet> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSet> datasets) {
		this.datasets = datasets;
	}
}
