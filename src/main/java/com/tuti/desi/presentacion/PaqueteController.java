package com.tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tuti.desi.entidades.Paquete;
import com.tuti.desi.servicios.CiudadService;
import com.tuti.desi.servicios.PaqueteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/paquetes")
public class PaqueteController {

	@Autowired
	private PaqueteService paqueteService;
	@Autowired
	private CiudadService ciudadService;

	@GetMapping("/form") // http:localhost:8080/paquetes/registroPaquete
	public String crear(Model model) {
		model.addAttribute("paquete", new Paquete());
		model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
		return "paquete_form";
	}

	@PostMapping("/guardar")
	public String guardarPaquete(@Valid @ModelAttribute Paquete paquete,
			@RequestParam(required = false) Integer dniRemitente,
			@RequestParam(required = false) Integer dniDestinatario, Model model, BindingResult result) {

		if (result.hasErrors()) {
			// Agrega un mensaje de error al modelo para mostrar en el formulario
			model.addAttribute("errorMessage", "Error al registrar el paquete. Por favor verifica los datos.");
			model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
			return "paquete_form";
		}

		try {

			// Lógica para asignar ciudades y validar DNIs
			paqueteService.guardarPaquete(paquete, dniRemitente, dniDestinatario);
			model.addAttribute("successMessage", "El paquete se registro correctamente.");
		} catch (IllegalArgumentException e) {

			// Captura y maneja las excepciones específicas, agregando un mensaje de error
			// al modelo
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
			return "paquete_form";
		}

		model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
		return "redirect:/paquetes/lista";

	}

	@GetMapping("/lista")
	public String listarPaquetes(Model model) {
		model.addAttribute("paquetes", paqueteService.obtenerTodosLosPaquetes());
		return "paquete_lista";
	}

}
