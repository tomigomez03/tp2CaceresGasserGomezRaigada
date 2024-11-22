package com.tuti.desi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.tuti.desi.entidades.Ciudad;
import com.tuti.desi.servicios.CiudadService;
import com.tuti.desi.servicios.ProvinciaService;
import com.tuti.desi.enums.Clima;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public String listarCiudades(Model model) {
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        return "ciudad_lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCiudad(Model model) {
        model.addAttribute("ciudad", new Ciudad());
        model.addAttribute("provincias", provinciaService.obtenerTodasLasProvincias());
        model.addAttribute("climas", Clima.values());
        return "ciudad_form";
    }

   

    //Guarda la ciudad con los datos.
    @PostMapping("/guardar")
    public String guardarCiudad(@Valid @ModelAttribute Ciudad ciudad, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("provincias", provinciaService.obtenerTodasLasProvincias());
            model.addAttribute("climas", Clima.values());
            return "ciudad_form";
        }
        ciudadService.guardarCiudad(ciudad);
        return "redirect:/ciudades";
    }

    //Intenta eliminar la ciudad, pero no se puede porque hay una restricción
    @GetMapping("/eliminar/{id}")
    public String eliminarCiudad(@PathVariable Long id) {
        ciudadService.eliminarCiudad(id);
        return "redirect:/ciudades";
    }

    //Traigo el formulario para actualizar solamente el clima de la ciudad
    @GetMapping("/actualizar-clima/{id}")
    public String mostrarFormularioActualizarClima(@PathVariable Long id, Model model) {
        Ciudad ciudad = ciudadService.obtenerCiudadPorId(id);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("climas", Clima.values());
        return "ciudad_actualizar_clima";
    }

    //Actualizo el clima de una ciudad (solo puedo modificar el clima)
    @PostMapping("/actualizar-clima/{id}")
    public String actualizarClima(@PathVariable Long id, @RequestParam Clima nuevoClima) {
        ciudadService.actualizarClimaCiudad(id, nuevoClima);
        return "redirect:/ciudades";
    }
}