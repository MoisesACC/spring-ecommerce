package com.curso.ecommerce.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministradoController {

    @GetMapping("")
    public String home() {
        return "admin/home";
    }
}
