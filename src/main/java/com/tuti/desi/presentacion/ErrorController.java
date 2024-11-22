package com.tuti.desi.presentacion;


import com.tuti.desi.excepciones.LogisticaException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(LogisticaException.class)
    public String handleLogisticaException(LogisticaException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("error", "Ha ocurrido un error inesperado: " + ex.getMessage());
        return "error";
    }
}