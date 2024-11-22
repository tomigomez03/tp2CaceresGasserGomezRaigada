package com.tuti.desi.servicios;

import com.tuti.desi.entidades.Camino;
import com.tuti.desi.enums.Clima;
import com.tuti.desi.enums.Calzada;
import com.tuti.desi.accesoDatos.CaminoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaminoService {

	@Autowired
	private CaminoRepository caminoRepository;

	// Guardar o actualizar un camino
	public Camino guardarCamino(Camino camino) {

		if (camino.getCiudadOrigen() == null || camino.getCiudadDestino() == null) {
			throw new IllegalArgumentException("El camino debe tener ciudades de origen y destino definidas.");
		}
		if (camino.getCiudadOrigen().equals(camino.getCiudadDestino())) {
			throw new IllegalArgumentException("El origen y destino no pueden ser la misma ciudad.");
		}
		// Validar que el camino no exista previamente

		boolean existe = caminoRepository.existsByCiudadOrigenAndCiudadDestino(camino.getCiudadOrigen(),
				camino.getCiudadDestino());
		if (existe) {
			throw new IllegalArgumentException("El camino ya existe entre estas ciudades.");
		}

		return caminoRepository.save(camino);
	}

	// Obtener todos los caminos
	public List<Camino> obtenerTodosLosCaminos() {
		return caminoRepository.findAll();
	}

	// Obtener caminos por código postal de ciudad de origen
	public List<Camino> obtenerCaminosPorCodigoPostalOrigen(int codigoPostal) {
		return caminoRepository.findByCiudadOrigenCodigoPostal(codigoPostal);
	}

	// Obtener un camino por su ID
	public Camino obtenerCaminoPorId(Long id) {
		return caminoRepository.findById(id).orElse(null);

	}

	// Eliminar un camino por su ID
	public void eliminarCamino(Long id) {
		if (caminoRepository.existsById(id)) {
			caminoRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("El camino con ID " + id + " no existe.");
		}
	}

	// Calcular la transitabilidad de un camino

	public String calcularTransitabilidad(Camino camino) {

		if (camino == null || camino.getCiudadOrigen() == null || camino.getCiudadDestino() == null) {
			throw new IllegalArgumentException("El camino y sus ciudades deben estar definidos.");
		}

		Clima climaOrigen = camino.getCiudadOrigen().getClima();
		Clima climaDestino = camino.getCiudadDestino().getClima();
		Calzada calzada = camino.getCalzada();

		if (climaOrigen == null || climaDestino == null || calzada == null) {
			return "Intransitable";
		}
		boolean esTransitable;

		switch (calzada) {

		case TIERRA:
			// Transitable sólo si ambas ciudades tienen clima óptimo
			esTransitable = climaOrigen == Clima.OPTIMO && climaDestino == Clima.OPTIMO;
			break;

		case RIPIO:
			// Transitable si ambas ciudades tienen clima óptimo o lluvia
			esTransitable = (climaOrigen == Clima.OPTIMO || climaOrigen == Clima.LLUVIA)
					&& (climaDestino == Clima.OPTIMO || climaDestino == Clima.LLUVIA);
			break;

		case ASFALTO:
			// Intransitable sólo si alguna ciudad tiene clima de nieve intensa
			esTransitable = climaOrigen != Clima.NIEVE_INTENSA && climaDestino != Clima.NIEVE_INTENSA;
			break;

		default:
			esTransitable = false;

		}
		return esTransitable ? "Transitable" : "Intransitable";

	}
}
