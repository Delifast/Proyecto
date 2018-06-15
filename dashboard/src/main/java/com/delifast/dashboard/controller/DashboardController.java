package com.delifast.dashboard.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.delifast.dashboard.graphic.util.Complementos;
import com.delifast.dashboard.graphic.util.DataSet;
import com.delifast.dashboard.graphic.util.Grafica;
import com.delifast.dashboard.graphic.util.Graficador;
import com.delifast.dashboard.model.Cliente;
import com.delifast.dashboard.model.Platos;

@Controller
@RequestMapping("/")
public class DashboardController {

	private static final String URL_API_CLIENTES = "http://localhost:8090/api/clientes";
	private static final String URL_API_PLATOS = "http://localhost:8090/api/platos";

	@PostMapping("/dashboard/data")
	public ResponseEntity<List<Grafica>> viewDashboard() {

		List<String> clientes = consumirAPIClientes_Nombre();
		
		List<Grafica> graficas = crearGraficas(clientes);

		
		for (Grafica grafica : graficas) {
			for (DataSet ds : grafica.getDatasets()) {
				System.out.println("ds : " + ds.getLabel());
				System.out.println("indice : " + grafica.getDatasets().indexOf(ds));
			}
		}

		

		clientes.stream().forEach(nombre -> System.out.println("nombre : " + nombre));

		return new ResponseEntity<List<Grafica>>(graficas, HttpStatus.OK);
	}

	public List<Grafica> crearGraficas(List<String> clientes) {
		// List<Grafica> graficas = new ArrayList<Grafica>();
		// Grafica grafica_1 = new Grafica();
		// List<DataSet> datasets_1 = new ArrayList<DataSet>();
		// DataSet dataset_1 = new DataSet();
		//
		//
		// dataset_1.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_1.setLabel("Cantidad de compras");
		// dataset_1.setBackgroundColor(
		// new String[] {
		// Complementos.Color.ROJO,
		// Complementos.Color.NARANJA,
		// Complementos.Color.AMARILLO,
		// Complementos.Color.VERDE,
		// Complementos.Color.AZUL,
		// Complementos.Color.MORADO,
		// });
		// datasets_1.add(dataset_1);
		//
		// grafica_1.setType("doughnut");
		// grafica_1.setTitle("Compra de los Usuarios en unidades");
		// grafica_1.setLabels(
		// new String[]{
		// "Erick",
		// "Yachi",
		// "Antony",
		// "Alza",
		// "Kemy",
		// "Italo"
		// });
		// grafica_1.setDatasets(datasets_1);
		//
		// graficas.add(grafica_1);
		//
		//
		// ///////////////////////////////
		// List<DataSet> datasets_2 = new ArrayList<DataSet>();
		// DataSet dataset_2_1 = new DataSet();
		// Grafica grafica_2 = new Grafica();
		//
		// dataset_2_1.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_2_1.setLabel("Ceviche");
		// dataset_2_1.setBackgroundColor(
		// new String[] {
		// Complementos.Color.ROJO
		// });
		// datasets_2.add(dataset_2_1);
		//
		// DataSet dataset_2_2 = new DataSet();
		// dataset_2_2.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_2_2.setLabel("Arroz con Pollo");
		// dataset_2_2.setBackgroundColor(
		// new String[] {
		// Complementos.Color.VERDE
		// });
		// datasets_2.add(dataset_2_2);
		//
		// DataSet dataset_2_3 = new DataSet();
		// dataset_2_3.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_2_3.setLabel("Aji de Gallina");
		// dataset_2_3.setBackgroundColor(
		// new String[] {
		// Complementos.Color.AZUL
		// });
		// datasets_2.add(dataset_2_3);
		//
		// DataSet dataset_2_4 = new DataSet();
		// dataset_2_4.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_2_4.setLabel("Bistec a lo pobre");
		// dataset_2_4.setBackgroundColor(
		// new String[] {
		// Complementos.Color.MORADO
		// });
		// datasets_2.add(dataset_2_4);
		//
		// DataSet dataset_2_5 = new DataSet();
		// dataset_2_5.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_2_5.setLabel("Pollo a la Brasa");
		// dataset_2_5.setBackgroundColor(
		// new String[] {
		// Complementos.Color.PLOMO
		// });
		// datasets_2.add(dataset_2_5);
		//
		// DataSet dataset_2_6 = new DataSet();
		// dataset_2_6.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_2_6.setLabel("Tallarines Verdes");
		// dataset_2_6.setBackgroundColor(
		// new String[] {
		// Complementos.Color.NARANJA
		// });
		// datasets_2.add(dataset_2_6);
		//
		// grafica_2.setType("bar");
		// grafica_2.setTitle("Platos vendidos por Cliente");
		// grafica_2.setLabels(
		// new String[]{
		// "Alonso",
		// "Erick",
		// "Yachi",
		// "Terrocal",
		// "Jhonathan",
		// "Jhulian"
		// });
		// grafica_2.setDatasets(datasets_2);
		//
		// graficas.add(grafica_2);
		//
		//
		// //////////////////////////////////
		// List<DataSet> datasets_3 = new ArrayList<DataSet>();
		// DataSet dataset_3 = new DataSet();
		// Grafica grafica_3 = new Grafica();
		//
		// dataset_3.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_3.setLabel("Cantidad de platos Comprados");
		// dataset_3.setBackgroundColor(
		// new String[] {
		// Complementos.Color.VERDE
		// });
		// datasets_3.add(dataset_3);
		//
		// grafica_3.setType("bar");
		// grafica_3.setTitle("Cantidad Platos Vendidos");
		// grafica_3.setLabels(
		// new String[]{
		// "Arroz con Pollo",
		// "Ceviche",
		// "Aji de Gallina",
		// "Bistec a lo pobre",
		// "Pollo a la Brasa",
		// "Tallarines Verdes"
		// });
		// grafica_3.setDatasets(datasets_3);
		//
		// graficas.add(grafica_3);
		//
		// ///////////////////////////////////////
		// List<DataSet> datasets_4 = new ArrayList<DataSet>();
		// DataSet dataset_4_1 = new DataSet();
		// Grafica grafica_4 = new Grafica();
		//
		// dataset_4_1.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_4_1.setLabel("En devp");
		// datasets_4.add(dataset_4_1);
		//
		//
		// DataSet dataset_4_2 = new DataSet();
		//
		// dataset_4_2.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_4_2.setBackgroundColor(
		// new String[] {
		// Complementos.Color.NARANJA,
		// });
		// dataset_4_2.setLabel("Edad del Usuario");
		// datasets_4.add(dataset_4_2);
		//
		//
		// grafica_4.setType("bar");
		// grafica_4.setTitle("Consumo según Edades");
		// grafica_4.setLabels(
		// new String[]{
		// "17-20",
		// "21-24",
		// "25-27",
		// "28-31",
		// "32-35",
		// "36-39"
		// });
		// grafica_4.setDatasets(datasets_4);
		//
		// graficas.add(grafica_4);
		//
		// /////////////////////////////////////////////////
		// List<DataSet> datasets_5 = new ArrayList<DataSet>();
		// DataSet dataset_5 = new DataSet();
		// Grafica grafica_5 = new Grafica();
		//
		// dataset_5.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_5.setLabel("Ventas en el Distrito");
		// dataset_5.setBackgroundColor(
		// new String[] {
		// Complementos.Color.VERDE,
		// Complementos.Color.PLOMO,
		// Complementos.Color.ROJO,
		// Complementos.Color.AZUL,
		// Complementos.Color.MORADO,
		// Complementos.Color.NARANJA
		// });
		// datasets_5.add(dataset_5);
		//
		// grafica_5.setType("polarArea");
		// grafica_5.setTitle("Ventas según distrito");
		// grafica_5.setLabels(
		// new String[]{
		// "La Molina",
		// "Surco",
		// "Miraflores",
		// "Comas",
		// "Los Olivos",
		// "Chorrillos"
		// });
		// grafica_5.setDatasets(datasets_5);
		//
		// graficas.add(grafica_5);
		//
		//
		// //////////////////////////////////////////
		// List<DataSet> datasets_6 = new ArrayList<DataSet>();
		// DataSet dataset_6_1 = new DataSet();
		// Grafica grafica_6 = new Grafica();
		//
		// dataset_6_1.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_6_1.setBackgroundColor(
		// new String[] {
		// Complementos.Color.ROJO,
		// });
		// dataset_6_1.setLabel("Femenino");
		// datasets_6.add(dataset_6_1);
		//
		//
		// DataSet dataset_6_2 = new DataSet();
		//
		// dataset_6_2.setData(Complementos.Data.arrayAleatorio(6));
		// dataset_6_2.setBackgroundColor(
		// new String[] {
		// Complementos.Color.AZUL,
		// });
		// dataset_6_2.setLabel("Masculino");
		// datasets_6.add(dataset_6_2);
		//
		//
		// grafica_6.setType("bar");
		// grafica_6.setTitle("Consumo por Plato según Género");
		// grafica_6.setLabels(
		// new String[]{
		// "Arroz con Pollo",
		// "Ceviche",
		// "Aji de Gallina",
		// "Bistec a lo pobre",
		// "Pollo a la Brasa",
		// "Tallarines Verdes"
		// });
		// grafica_6.setDatasets(datasets_6);
		//
		// graficas.add(grafica_6);
		
		
		
		/***************************************************GRAFICA DE COMPRAS DE LOS USUARIOS****************************************************************/
		
		List<Grafica> graficas = new ArrayList<Grafica>();
		Graficador graficador = new Graficador();

		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.ROJO,
						 Complementos.Color.NARANJA,
						 Complementos.Color.AMARILLO,
						 Complementos.Color.VERDE,
						 Complementos.Color.AZUL,
						 Complementos.Color.MORADO });
		graficador.agregarLabelDataSet("Cantidad de compras");
		graficador.agregarDataSetToGrafica();

		graficas.add(
				graficador.generarGrafica(
						"doughnut",
						"Compra de los Usuarios en unidades",
						clientes.toArray(new String[clientes.size()])));
		
		/***************************************************GRAFICA DE PLATOS VENDIDOS POR USUARIO****************************************************************/		
		graficador.resetearPropiedades();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.ROJO
						});
		graficador.agregarLabelDataSet("Ceviche");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.VERDE
						});
		graficador.agregarLabelDataSet("Arroz con Pollo");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.AZUL
						});
		graficador.agregarLabelDataSet("Ají de Gallina");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.MORADO
						});
		graficador.agregarLabelDataSet("Bistec a lo Pobre");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.PLOMO
						});
		graficador.agregarLabelDataSet("Pollo a la Brasa");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.NARANJA
						});
		graficador.agregarLabelDataSet("Tallarines Verdes");
		graficador.agregarDataSetToGrafica();
		
		graficas.add(
				graficador.generarGrafica(
						"bar",
						"Platos vendidos por Cliente",
						clientes.toArray(new String[clientes.size()])));
		
		/***************************************************GRAFICA DE PLATOS VENDIDOS****************************************************************/
		graficador.resetearPropiedades();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.ROJO,
						 Complementos.Color.NARANJA,
						 Complementos.Color.AMARILLO,
						 Complementos.Color.VERDE,
						 Complementos.Color.AZUL,
						 Complementos.Color.MORADO });
		graficador.agregarLabelDataSet("Cantidad de platos vendidos");
		graficador.agregarDataSetToGrafica();

		graficas.add(
				graficador.generarGrafica(
						"bar",
						"Platos Vendidos",
						 new String[]{
							 "Arroz con Pollo",
							 "Ceviche",
							 "Aji de Gallina",
							 "Bistec a lo pobre",
							 "Pollo a la Brasa",
							 "Tallarines Verdes" 
							 }));
		/***************************************************GRAFICA DE CONSUMO SEGUN EDADES****************************************************************/
		graficador.resetearPropiedades();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarLabelDataSet("En Desarrollo");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.NARANJA
						});
		graficador.agregarLabelDataSet("Edad del Usuario");
		graficador.agregarDataSetToGrafica();
		
		graficas.add(
				graficador.generarGrafica(
						"bar",
						"Consumo según Edades",
						 new String[]{
							 "17-20",
							 "21-24",
							 "25-27",
							 "28-31",
							 "32-35",
							 "36-39"
						 }));
		
		/***************************************************GRAFICA DE CONSUMO SEGUN DISTRITO****************************************************************/
		graficador.resetearPropiedades();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] {
					 Complementos.Color.VERDE,
					 Complementos.Color.PLOMO,
					 Complementos.Color.ROJO,
					 Complementos.Color.AZUL,
					 Complementos.Color.MORADO,
					 Complementos.Color.NARANJA
				});
		graficador.agregarLabelDataSet("Ventas en el Distrito");
		graficador.agregarDataSetToGrafica();
		
		graficas.add(
				graficador.generarGrafica(
						"polarArea",
						"Ventas según distrito",
						 new String[] {
							 "La Molina",
							 "Surco",
							 "Miraflores",
							 "Comas",
							 "Los Olivos",
							 "Chorrillos"
						 }));
		/***************************************************GRAFICA DE PLATOS VENDIDOS POR GENERO****************************************************************/
		graficador.resetearPropiedades();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.ROJO
						});
		graficador.agregarLabelDataSet("Femenino");
		graficador.agregarDataSetToGrafica();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(
				new String[] { 
						 Complementos.Color.AZUL
						});
		graficador.agregarLabelDataSet("Masculino");
		graficador.agregarDataSetToGrafica();
		
		graficas.add(
				graficador.generarGrafica(
						"bar",
						"Consumo por Plato según Género",
						 new String[]{
							 "Arroz con Pollo",
							 "Ceviche",
							 "Aji de Gallina",
							 "Bistec a lo pobre",
							 "Pollo a la Brasa",
							 "Tallarines Verdes"
						 }));
		
		return graficas;
	}

	private List<String> consumirAPIClientes_Nombre() {
		RestTemplate restTemplate = new RestTemplate();

		return Arrays.asList(
				restTemplate.getForObject(
						URL_API_CLIENTES,
						Cliente[].class))
				.stream()
				.map(Cliente::getNombre).
				collect(Collectors.toList());
	}

	private Platos[] consumirAPIPlatos() {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(URL_API_PLATOS, Platos[].class);
	}

}
