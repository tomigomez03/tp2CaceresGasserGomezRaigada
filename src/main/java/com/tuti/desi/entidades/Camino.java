package com.tuti.desi.entidades;

import com.tuti.desi.enums.Calzada;
import jakarta.persistence.*;

@Entity
@Table(name = "camino")
public class Camino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String identificador;

	@ManyToOne
	@JoinColumn(name = "ciudad_origen_id", nullable = false)
	private Ciudad ciudadOrigen;

	@ManyToOne
	@JoinColumn(name = "ciudad_destino_id", nullable = false)
	private Ciudad ciudadDestino;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Calzada calzada;

	@Transient
	private String transitabilidad;

	public Camino() {
	}

	public Camino(String identificador, Ciudad ciudadOrigen, Ciudad ciudadDestino, Calzada calzada) {
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.calzada = calzada;
		generarIdentificador(); // Genera el identificador al crear la instancia

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Calzada getCalzada() {
		return calzada;
	}

	public void setCalzada(Calzada calzada) {
		this.calzada = calzada;

	}

	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
		generarIdentificador(); // Actualiza el identificador si se cambia la ciudad origen
	}

	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
		generarIdentificador(); // Actualiza el identificador si se cambia la ciudad destino
	}

	public String getTransitabilidad() {
		return transitabilidad;
	}

	public void setTransitabilidad(String transitabilidad) {
		this.transitabilidad = transitabilidad;
	}

	private void generarIdentificador() {
		if (ciudadOrigen != null && ciudadDestino != null) {
			String abreviaturaOrigen = ciudadOrigen.getNombre().substring(0, 2).toLowerCase();
			String abreviaturaDestino = ciudadDestino.getNombre().substring(0, 2).toLowerCase();
			this.identificador = abreviaturaOrigen + abreviaturaDestino + ciudadOrigen.getId() + ciudadDestino.getId();
		} else {
			this.identificador = null;
		}
	}

	@Override
	public String toString() {
		return "Camino{" + "id=" + id + ", identificador='" + identificador + '\'' + ", ciudadOrigen="
				+ ciudadOrigen.getNombre() + ", ciudadDestino=" + ciudadDestino.getNombre() + ", calzada=" + calzada
				+ '}';
	}

}
