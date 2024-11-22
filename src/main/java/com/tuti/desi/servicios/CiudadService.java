package com.tuti.desi.servicios;

import com.tuti.desi.entidades.Ciudad;
import com.tuti.desi.enums.Clima;
import java.util.List;

public interface CiudadService {
    List<Ciudad> obtenerTodasLasCiudades();
    Ciudad obtenerCiudadPorId(Long id);
    void guardarCiudad(Ciudad ciudad);
    void eliminarCiudad(Long id);
    void actualizarClimaCiudad(Long id, Clima nuevoClima);
}
