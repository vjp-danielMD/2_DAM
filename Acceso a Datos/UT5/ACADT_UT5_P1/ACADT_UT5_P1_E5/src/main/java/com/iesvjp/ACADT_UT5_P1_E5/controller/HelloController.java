package com.iesvjp.ACADT_UT5_P1_E5.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iesvjp.ACADT_UT5_P1_E5.model.Persona;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        Persona persona = new Persona("Juan", "Pérez Gómez", 19);
        model.addAttribute("persona", persona);

        List<String> modulos = Arrays.asList(
                "Acceso a Datos (ACADT)",
                "Programación Multimedia y Dispositivos Móviles (PMDM)",
                "Programación de Servicios y Procesos (PSP)",
                "Sistemas de Gestión Empresarial (SGE)",
                "Desarrollo de Interfaces (DI)",
                "Inglés Técnico");

        model.addAttribute("modulos", modulos);
        return "hello";
    }
}
