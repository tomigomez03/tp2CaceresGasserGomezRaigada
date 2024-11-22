package com.tuti.desi.accesoDatos;

import com.tuti.desi.entidades.Camino;
import com.tuti.desi.entidades.Ciudad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CaminoRepository extends JpaRepository<Camino, Long> {

	List<Camino> findByCiudadOrigenCodigoPostal(int codigoPostal);

	boolean existsByIdentificador(String identificador); // o el de abajo

	boolean existsByCiudadOrigenAndCiudadDestino(Ciudad ciudadOrigen, Ciudad ciudadDestino);
}
