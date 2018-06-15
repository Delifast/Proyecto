package com.delifast.dashboard.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class Cliente {
	@Id
	private String id;

	@Size(max = 8)
	private String dni;

	private String nombre;
	private String apellidopat;
	private String apellidomat;
	private String correo;
	private Date createdAt = new Date();
	// private Boolean completed = false;
	// public Boolean getCompleted() {
	// return completed;
	// }
	// public void setCompleted(Boolean completed) {
	// this.completed = completed;
	// }

	@Min(1)
	private double salario;

	private String relacion_laboral;

	@Min(850)
	private long uit;

	private double total_neto;
	private double total_impuesto;
	private double total_bruto;

	public Cliente() {
		super();
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getRelacion_laboral() {
		return relacion_laboral;
	}

	public void setRelacion_laboral(String relacion_laboral) {
		this.relacion_laboral = relacion_laboral;
	}

	public long getUit() {
		return uit;
	}

	public void setUit(long uit) {
		this.uit = uit;
	}

	public double getTotal_neto() {
		return total_neto;
	}

	public void setTotal_neto(double total_neto) {
		this.total_neto = total_neto;
	}

	public double getTotal_impuesto() {
		return total_impuesto;
	}

	public void setTotal_impuesto(double total_impuesto) {
		this.total_impuesto = total_impuesto;
	}

	public double getTotal_bruto() {
		return total_bruto;
	}

	public void setTotal_bruto(double total_bruto) {
		this.total_bruto = total_bruto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidopat() {
		return apellidopat;
	}

	public void setApellidopat(String apellidopat) {
		this.apellidopat = apellidopat;
	}

	public String getApellidomat() {
		return apellidomat;
	}

	public void setApellidomat(String apellidomat) {
		this.apellidomat = apellidomat;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
