package com.tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuti.desi.entidades.Paquete;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> { 
	
}
