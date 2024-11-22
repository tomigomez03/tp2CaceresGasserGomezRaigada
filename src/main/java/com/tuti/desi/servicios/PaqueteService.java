package com.tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.tuti.desi.accesoDatos.PaqueteRepository;
import com.tuti.desi.accesoDatos.PersonaRepository;
import com.tuti.desi.entidades.Paquete;
import com.tuti.desi.entidades.Persona;

@Service
public class PaqueteService {

	@Autowired
	private PaqueteRepository repo;

	@Autowired
	private PersonaRepository repoPersona;

	@Transactional
	public Paquete guardarPaquete(Paquete paquete, int dniRemitente, int dniDestinatario) {

		// Verifica que se seleccione una ciudad
		if (paquete.getCiudadDestino() == null || paquete.getCiudadDestino().getId() == null) {
			throw new IllegalArgumentException("Debe seleccionar una ciudad de destino.");
		}

		// validamos remitente
		Optional<Persona> remitente = repoPersona.findByDni(dniRemitente);
		if (!remitente.isPresent()) {
			throw new IllegalArgumentException("El remitente con el DNI " + dniRemitente + " no está registrado.");
		}

		// Validar destinatario
		Optional<Persona> destinatario = repoPersona.findByDni(dniDestinatario);
		if (!destinatario.isPresent()) {
			throw new IllegalArgumentException(
					"El destinatario con el DNI " + dniDestinatario + " no está registrado.");
		}

		// Asignar remitente y destinatario verificados al paquete
		paquete.setRemitente(remitente.get());
		paquete.setDestinatario(destinatario.get());

		// verifica que se ingrese un peso valido
		if (paquete.getPeso() <= 0) {
			throw new IllegalArgumentException("Debe ingresar un peso válido.");
		}

		// Guardar paquete 
		return repo.save(paquete);
	}

	public List<Paquete> obtenerTodosLosPaquetes() {
		return repo.findAll();

	}

	public Optional<Paquete> obtenerPaquetePorId(Paquete paquete) {
		return repo.findById(paquete.getId());

	}
}