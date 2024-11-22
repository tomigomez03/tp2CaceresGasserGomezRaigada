package com.tuti.desi.accesoDatos;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tuti.desi.entidades.Camion;


@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> { 

	List<Camion> findAll();
	
	//Metodo para realizar filtros en la flota:

    @Query("SELECT c FROM Camion c WHERE " +
           "(:patente IS NULL OR c.patente LIKE %:patente%) AND " +
           "(:codigoPostal IS NULL OR c.ciudadActual.codigoPostal = :codigoPostal)")
    List<Camion> filtrarCamiones(
            @Param("patente") String patente,
            @Param("codigoPostal") Integer codigoPostal);
}



