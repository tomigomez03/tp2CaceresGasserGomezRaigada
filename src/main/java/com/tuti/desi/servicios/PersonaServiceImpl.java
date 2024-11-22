package com.tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuti.desi.accesoDatos.PersonaRepository;
import com.tuti.desi.entidades.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> obtenerTodasLasPersonas() {
		return personaRepository.findAll();
	}

	@Override
	public Persona obtenerPersonaPorId(Long id) {
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public Optional<Persona> obtenerPersonaPorDni(int dni) {
		return personaRepository.findByDni(dni);
	}

	@Override
	public void guardarPersona(Persona persona) {
		personaRepository.save(persona);
	}

	@Override
	public void eliminarPersona(Long id) {
		personaRepository.deleteById(id);
	}

	@Override
	public boolean existePersonaPorDni(int dni) {
		return personaRepository.existsByDni(dni);
	}
}
