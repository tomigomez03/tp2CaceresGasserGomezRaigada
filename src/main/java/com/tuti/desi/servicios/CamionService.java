package com.tuti.desi.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tuti.desi.accesoDatos.CamionRepository;
import com.tuti.desi.entidades.Camion;
import com.tuti.desi.entidades.Ciudad;

import jakarta.transaction.Transactional;

@Service
public class CamionService {

    @Autowired
    private CamionRepository camionRepository;

    @Transactional
    public Camion guardarOActualizarCamion(Camion camion) {
        validarModeloCamion(camion);
        return camionRepository.save(camion);
    }
    
    private void validarModeloCamion(Camion camion) {
        if (camion.getModelo() < 1990 || camion.getModelo() > 2025) {
            throw new IllegalArgumentException("El modelo del camión debe estar entre 1990 y 2025");
        }
    }

    public List<Camion> obtenerTodosLosCamiones() {
        return camionRepository.findAll();
    }

    public Camion obtenerCamionPorId(Long id) {
        return camionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Camión no encontrado"));
    }
    
    public void actualizarCiudadCamion(Long id, Ciudad nuevaCiudad) {
        Camion camion = obtenerCamionPorId(id);
        camion.setCiudadActual(nuevaCiudad);
        camionRepository.save(camion);
    }
    

    public List<Camion> filtrarCamiones(String patente, Integer codigoPostal) {
    	
    	List<Camion> camiones = camionRepository.filtrarCamiones(patente, codigoPostal);
        
        if (camiones.isEmpty()) {
            throw new RuntimeException("No se encontraron resultados con los filtros ingresados");
        }
        
        return camiones;
        
    }
}