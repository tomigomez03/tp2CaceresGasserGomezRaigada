package com.tuti.desi.entidades;

import com.tuti.desi.enums.Clima;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ciudades")
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	@Max(9999)
	private int codigoPostal;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "provincia_id", nullable = false)
	private Provincia provincia;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Clima clima;

	public Ciudad() {
	}

	public Ciudad(Long id, @Max(9999) int codigoPostal, String nombre, Provincia provincia, @NotNull Clima clima) {
		super();
		this.id = id;
		this.codigoPostal = codigoPostal;
		this.nombre = nombre;
		this.provincia = provincia;
		this.clima = clima;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Clima getClima() {
		return clima;
	}

	public String getClimaDisplayName() {
		return clima.getDisplayName();
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

}