package com.umg.primerappspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umg.primerappspringboot.model.Persona;
import com.umg.primerappspringboot.service.PersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public String listarPersonas(Model model) {
        model.addAttribute("personas", personaService.listarPersonas());
        return "personas";
    }

    @GetMapping("/create")
    public String crearPersonaForm(Model model) {
        model.addAttribute("persona", new Persona());
        return "createPersona";
    }

    @PostMapping
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaService.agregarPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/edit/{id}")
    public String editarPersonaForm(@PathVariable Integer id, Model model) {
        Persona persona = personaService.buscarPersonaPorId(id).orElse(null);
        model.addAttribute("persona", persona);
        return "editPersona";
    }

    @PostMapping("/update")
    public String actualizarPersona(@ModelAttribute Persona persona) {
        personaService.actualizarPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/delete/{id}")
    public String eliminarPersona(@PathVariable Integer id) {
        personaService.eliminarPersona(id);
        return "redirect:/personas";
    }
}
