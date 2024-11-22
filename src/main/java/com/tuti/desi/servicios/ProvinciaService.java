package com.tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.tuti.desi.entidades.Provincia;
import com.tuti.desi.excepciones.LogisticaException;
import com.tuti.desi.accesoDatos.ProvinciaRepository;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<Provincia> obtenerTodasLasProvincias() {
        return provinciaRepository.findAll();
    }

    public void guardarProvincia(Provincia provincia) throws LogisticaException {
        Optional<Provincia> provinciaExistente = provinciaRepository.findByNombre(provincia.getNombre());
        if (provinciaExistente.isPresent()) {
            throw new LogisticaException("Ya existe una provincia con el nombre: " + provincia.getNombre());
        }
        provinciaRepository.save(provincia);
    }
}

