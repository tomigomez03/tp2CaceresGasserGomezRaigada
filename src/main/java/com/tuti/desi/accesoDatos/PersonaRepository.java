package com.tuti.desi.accesoDatos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuti.desi.entidades.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	boolean existsByDni(int dni);

	Optional<Persona> findByDni(int dni);
}
