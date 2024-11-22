package com.tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuti.desi.entidades.Ciudad;
import com.tuti.desi.entidades.Provincia;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

	List<Ciudad> findAll();

	Optional<Ciudad> findByCodigoPostal(int codigoPostalCiudad);

	Optional<Ciudad> findByNombreAndProvincia(String nombre, Provincia provincia);

}
