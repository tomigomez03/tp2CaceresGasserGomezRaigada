package com.tuti.desi.entidades;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
	private List<Ciudad> ciudades;
	
	public Provincia() {
	}
	
	public Provincia(Long id, String nombre, List<Ciudad> ciudades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudades = ciudades;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	
	
}