package com.tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tuti.desi.entidades.Provincia;
import com.tuti.desi.excepciones.LogisticaException;
import com.tuti.desi.servicios.ProvinciaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public String listarProvincias(Model model) {
        model.addAttribute("provincias", provinciaService.obtenerTodasLasProvincias());
        return "provincia_lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaProvincia(Model model) {
        model.addAttribute("provincia", new Provincia());
        return "provincia_form";
    }

    @PostMapping("/guardar")
    public String guardarProvincia(@Valid @ModelAttribute Provincia provincia, 
                                   BindingResult result, 
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "provincia_form";
        }
        try {
            provinciaService.guardarProvincia(provincia);
            redirectAttributes.addFlashAttribute("mensaje", "Provincia agregada con éxito.");
            return "redirect:/provincias";
        } catch (LogisticaException e) {
            result.rejectValue("nombre", "error.provincia", e.getMessage());
            return "provincia_form";
        }
    }
}

