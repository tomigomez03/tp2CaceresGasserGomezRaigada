package com.tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import com.tuti.desi.entidades.Persona;

public interface PersonaService {
	List<Persona> obtenerTodasLasPersonas();

	Persona obtenerPersonaPorId(Long id);

	Optional<Persona> obtenerPersonaPorDni(int dni);

	void guardarPersona(Persona persona);

	void eliminarPersona(Long id);

	boolean existePersonaPorDni(int dni);
}
