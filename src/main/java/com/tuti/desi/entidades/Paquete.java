package com.tuti.desi.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "paquetes")
public class Paquete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ciudad_origen_id")
	private Ciudad ciudadOrigen;

	@ManyToOne
	@JoinColumn(name = "ciudad_destino_id")
	@NotNull(message = "Debe seleccionar una ciudad de destino")
	private Ciudad ciudadDestino;

	@Column
	@NotNull(message = "Debe ingresar el peso del paquete.")
	private double peso;

	@ManyToOne
	@JoinColumn(name = "dni_remitente", referencedColumnName = "id")
	private Persona remitente;

	@ManyToOne
	@JoinColumn(name = "dni_destinatario", referencedColumnName = "id")
	private Persona destinatario;

	@Column
	private boolean esFragil;

	public Paquete() {
	}

	public Paquete(Long id, Ciudad ciudadOrigen, Ciudad ciudadDestino, double peso, Persona remitente,
			Persona destinatario, boolean esFragil) {
		super();
		this.id = id;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.peso = peso;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.esFragil = esFragil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Persona getRemitente() {
		return remitente;
	}

	public void setRemitente(Persona remitente) {
		this.remitente = remitente;
	}

	public Persona getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Persona destinatario) {
		this.destinatario = destinatario;
	}

	public boolean getEsFragil() {
		return esFragil;
	}

	public void setEsFragil(boolean esFragil) {
		this.esFragil = esFragil;
	}

}
