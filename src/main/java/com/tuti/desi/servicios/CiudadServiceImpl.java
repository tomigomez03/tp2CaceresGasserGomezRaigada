package com.tuti.desi.servicios;

import com.tuti.desi.accesoDatos.CiudadRepository;
import com.tuti.desi.entidades.Ciudad;
import com.tuti.desi.enums.Clima;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements CiudadService {

	private final CiudadRepository ciudadRepository;

	public CiudadServiceImpl(CiudadRepository ciudadRepository) {
		this.ciudadRepository = ciudadRepository;
	}

	//Traigo toda la lista de ciudades que tengo en la BD
	@Override
	public List<Ciudad> obtenerTodasLasCiudades() {
		return ciudadRepository.findAll();
	}

	//Traigo la ciudad por el ID
	@Override
	public Ciudad obtenerCiudadPorId(Long id) {
		Optional<Ciudad> ciudadOptional = ciudadRepository.findById(id);
		if (ciudadOptional.isPresent()) {
			return ciudadOptional.get();
		} else {
			throw new RuntimeException("Ciudad no encontrada con id: " + id);
		}
	}

	@Override
	@Transactional
	public void guardarCiudad(Ciudad ciudad) {
		// Verifico si existe una ciudad con el mismo código postal
		Optional<Ciudad> ciudadConMismoCodigoPostal = ciudadRepository.findByCodigoPostal(ciudad.getCodigoPostal());
		if (ciudadConMismoCodigoPostal.isPresent()) {
			throw new IllegalArgumentException("Ya existe una ciudad con el mismo código postal.");
		}

		// Verifico si existe una ciudad con el mismo nombre en la misma provincia
		Optional<Ciudad> ciudadConMismoNombreYProvincia = ciudadRepository.findByNombreAndProvincia(ciudad.getNombre(),
				ciudad.getProvincia());
		if (ciudadConMismoNombreYProvincia.isPresent()) {
			throw new IllegalArgumentException("Ya existe una ciudad con el mismo nombre en esta provincia.");
		}

		// Si está todo bien guardo la ciudad
		ciudadRepository.save(ciudad);
	}

	//Intenta eliminar la ciudad pero no se puede
	@Override
	@Transactional
	public void eliminarCiudad(Long id) {
		ciudadRepository.deleteById(id);
	}

	//Actualiza el clima de la ciudad
	@Override
	@Transactional
	public void actualizarClimaCiudad(Long id, Clima nuevoClima) {
		Ciudad ciudad = obtenerCiudadPorId(id);
		ciudad.setClima(nuevoClima);
		ciudadRepository.save(ciudad);
	}
}