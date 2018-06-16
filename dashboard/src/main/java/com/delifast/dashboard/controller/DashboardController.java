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

import com.delifast.dashboard.graphic.Complementos;
import com.delifast.dashboard.graphic.DataSet;
import com.delifast.dashboard.graphic.Grafica;
import com.delifast.dashboard.graphic.Graficador;
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

		//clientes.stream().forEach(nombre -> System.out.println("nombre : " + nombre));

		return new ResponseEntity<List<Grafica>>(graficas, HttpStatus.OK);
	}

	public List<Grafica> crearGraficas(List<String> clientes) {
		
		/***************************************************GRAFICA DE COMPRAS DE LOS USUARIOS****************************************************************/
		
		List<Grafica> graficas = new ArrayList<Grafica>();
		Graficador graficador = new Graficador();
		int sizeClientes = clientes.size();

		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(sizeClientes));
		graficador.agregarBackgroundColorDataSet(Complementos.Color.arrayColores(sizeClientes));
		graficador.agregarLabelDataSet("Cantidad de compras");
		graficador.agregarDataSetToGrafica();

		graficas.add(
				graficador.generarGrafica(
						"doughnut",
						"Compra de los Usuarios en unidades",
						clientes.toArray(new String[sizeClientes])));
		
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
						clientes.toArray(new String[sizeClientes])));
		
		/***************************************************GRAFICA DE PLATOS VENDIDOS****************************************************************/
		graficador.resetearPropiedades();
		
		graficador.agregarDataDataSet(Complementos.Data.arrayAleatorio(6));
		graficador.agregarBackgroundColorDataSet(Complementos.Color.arrayColores(6));
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
						 Complementos.Color.RANDOM
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
		graficador.agregarBackgroundColorDataSet(Complementos.Color.arrayColores(6));
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
		//return (new RestTemplate()).getForObject(URL_API_PLATOS, Platos[].class);
	}

}
