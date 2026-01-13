package com.iesvjp.ACADT_UT5_P1_E2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("nombre", "Carlos");
        model.addAttribute("apellidos", "García López");
        model.addAttribute("edad", 20);
        return "hello";
    }
}
