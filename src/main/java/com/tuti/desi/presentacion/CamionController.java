package com.tuti.desi.presentacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tuti.desi.entidades.Camion;
import com.tuti.desi.servicios.CamionService;
import com.tuti.desi.servicios.CiudadService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/camiones")
public class CamionController {

    @Autowired
    private CamionService camionService;
    
    @Autowired
    private CiudadService ciudadService;


    @GetMapping
    public String listarYFiltrarCamiones(
            @RequestParam(required = false) String patente,
            @RequestParam(required = false) Integer codigoPostal,
            Model model) {
        try {
            List<Camion> camiones = camionService.filtrarCamiones(patente, codigoPostal);
            model.addAttribute("camiones", camiones);
        } catch (RuntimeException e) {
            model.addAttribute("mensaje", e.getMessage());
        }
        return "camiones";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("camion", new Camion());
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        return "nuevocamion";
    }

    @GetMapping("/editar-ciudad/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Camion camion = camionService.obtenerCamionPorId(id);
        model.addAttribute("camion", camion);
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        return "editarcamion";
    }

    @PostMapping("/guardar")
    public String guardarCamion(@Valid @ModelAttribute("camion") Camion camion, 
        BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
            return "nuevocamion";
        }
        
        try {
            camionService.guardarOActualizarCamion(camion);
            return "redirect:/camiones?exito";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el camión: " + e.getMessage());
            model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
            return "nuevocamion";
        }
    }
    
    @PostMapping("/actualizar-ciudad")
    public String actualizarCiudadCamion(@ModelAttribute("camion") Camion camion, RedirectAttributes redirectAttributes) {
        try {
            camionService.actualizarCiudadCamion(camion.getId(), camion.getCiudadActual());
            redirectAttributes.addFlashAttribute("exito", "La ciudad del camión ha sido actualizada con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la ciudad del camión.");
        }
        return "redirect:/camiones";
        
    }

    }

