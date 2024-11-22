package com.tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tuti.desi.entidades.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    List<Provincia> findAll();
    Optional<Provincia> findByNombre(String nombre);
}
