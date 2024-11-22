package com.tuti.desi.presentacion;

import com.tuti.desi.entidades.Camino;
import com.tuti.desi.servicios.CaminoService;
import com.tuti.desi.servicios.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/caminos")
public class CaminoConsultarController {

	@Autowired
	private CaminoService caminoService;

	@Autowired
	private CiudadService ciudadService;

	@GetMapping("/consulta")
	public String mostrarFormularioConsulta(Model model) {
		model.addAttribute("camino", new Camino());
		return "camino_consulta";
	}

	@PostMapping("/consulta")
	public String consultarCaminos(@RequestParam("codigoPostalOrigen") Integer codigoPostal, Model model) {
		if (codigoPostal == null) {
			model.addAttribute("errorMensaje", "Por favor, ingrese un código postal válido.");
			return "camino_consulta";
		}

		List<Camino> caminos = caminoService.obtenerCaminosPorCodigoPostalOrigen(codigoPostal);
		if (caminos.isEmpty()) {
			model.addAttribute("errorMensaje", "No se encontraron caminos para el código postal ingresado.");
			return "camino_consulta";
		}

		// Calcular transitabilidad para cada camino
		for (Camino camino : caminos) {
			camino.setTransitabilidad(caminoService.calcularTransitabilidad(camino));
		}

		model.addAttribute("caminos", caminos);
		return "camino_lista";
	}

}
