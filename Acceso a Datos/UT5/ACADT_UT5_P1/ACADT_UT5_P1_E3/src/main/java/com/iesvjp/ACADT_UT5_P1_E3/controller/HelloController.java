package com.iesvjp.ACADT_UT5_P1_E3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iesvjp.ACADT_UT5_P1_E3.model.Persona;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        Persona persona = new Persona("María", "Fernández Ruiz", 22);
        model.addAttribute("persona", persona);
        return "hello";
    }
}
