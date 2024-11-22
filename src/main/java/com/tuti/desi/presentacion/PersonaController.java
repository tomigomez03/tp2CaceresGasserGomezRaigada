package com.tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tuti.desi.entidades.Persona;
import com.tuti.desi.servicios.PersonaService;

import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@GetMapping
	public String listarPersonas(Model model) {
		model.addAttribute("personas", personaService.obtenerTodasLasPersonas());
		return "persona_lista";
	}

	@GetMapping("/nueva")
	public String mostrarFormularioNuevaPersona(@RequestParam(required = false) Integer dni,
			@RequestParam(required = false) String returnUrl, Model model) {
		Persona persona = new Persona();
		if (dni != null) {
			persona.setDni(dni);
		}
		model.addAttribute("persona", persona);
		model.addAttribute("returnUrl", returnUrl);
		return "persona_form";
	}

	@PostMapping("/guardar")
	public String guardarPersona(@Valid @ModelAttribute Persona persona, BindingResult result,
			@RequestParam(required = false) String returnUrl, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "persona_form";
		}
		if (personaService.existePersonaPorDni(persona.getDni())) {
			result.rejectValue("dni", "error.persona", "Ya existe una persona con este DNI");
			return "persona_form";
		}
		personaService.guardarPersona(persona);
		redirectAttributes.addFlashAttribute("mensaje", "Persona guardada con éxito");
		if (returnUrl != null && !returnUrl.isEmpty()) {
			return "redirect:" + returnUrl;
		}
		return "redirect:/personas";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditarPersona(@PathVariable Long id, Model model) {
		Persona persona = personaService.obtenerPersonaPorId(id);
		if (persona == null) {
			return "redirect:/personas";
		}
		model.addAttribute("persona", persona);
		return "persona_form";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarPersona(@PathVariable Long id) {
		personaService.eliminarPersona(id);
		return "redirect:/personas";
	}
}
