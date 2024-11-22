package com.tuti.desi.presentacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tuti.desi.entidades.Camino;
import com.tuti.desi.entidades.Ciudad;
import com.tuti.desi.enums.Calzada;
import com.tuti.desi.servicios.CaminoService;
import com.tuti.desi.servicios.CiudadService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/caminos")
public class CaminoAddController {

	@Autowired
	private CiudadService ciudadService;
	@Autowired
	private CaminoService caminoService;

	// Mostrar formulario para agregar un camino

	@GetMapping("/agregar")
	public String FormularioAgregarCamino(Model model) {

		List<Ciudad> ciudades = ciudadService.obtenerTodasLasCiudades();
		model.addAttribute("ciudades", ciudades);
		model.addAttribute("tiposCalzada", Calzada.values());
		model.addAttribute("camino", new Camino());
		return "camino_add";
	}

	@PostMapping("/agregar")
	public String guardarCamino(@Valid @ModelAttribute Camino camino, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Ciudad> ciudades = ciudadService.obtenerTodasLasCiudades();
			model.addAttribute("ciudades", ciudades);
			model.addAttribute("tiposCalzada", Calzada.values());
			return "camino_add";
		}
		try {
			caminoService.guardarCamino(camino);
			return "redirect:/caminos/consulta";
		} catch (IllegalArgumentException e) {

			model.addAttribute("error", e.getMessage());
			model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
			model.addAttribute("tiposCalzada", Calzada.values());
			return "camino_add";
		}
	}
}
