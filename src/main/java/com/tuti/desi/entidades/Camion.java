package com.tuti.desi.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "camiones")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @NotNull(message = "La patente es obligatoria")
    private String patente;
    
    @Column(nullable = false)
    @NotNull(message = "La marca es obligatoria")
    private String marca;

    @ManyToOne
    @JoinColumn(name = "ciudad_actual", nullable = false)
    @NotNull(message = "La ciudad actual es obligatoria")
    private Ciudad ciudadActual;
    
    @Column(nullable = false)
    @NotNull(message = "El modelo es obligatorio")
    @Min(value = 1990, message = "El modelo debe ser igual o posterior a 1990")
    @Max(value = 2025, message = "El modelo debe ser igual o anterior a 2025")
    private int modelo;

	
    public Camion() {
	}

	public Camion(Long id, String patente, String marca, Ciudad ciudadActual, int modelo) {
		super();
		this.id = id;
		this.patente = patente;
		this.marca = marca;
		this.ciudadActual = ciudadActual;
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public void setCiudadActual(Ciudad ciudadActual) {
		this.ciudadActual = ciudadActual;
	}

	public int getModelo() {
		return modelo;
	}

	public void setModelo(int modelo) {
		this.modelo = modelo;
	}

	   
    
}

