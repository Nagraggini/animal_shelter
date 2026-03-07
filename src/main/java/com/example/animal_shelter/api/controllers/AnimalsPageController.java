package com.example.animal_shelter.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animals") // mindkét mappinghez prefix, alul a két getmappinghez csak a / kell , nem kell
                            // kiírni, hogy /animals/view és /animals/add, mert a class szintű mapping miatt
                            // már benne van a prefix.
public class AnimalsPageController {

    @GetMapping("/view")
    public String showAnimalsPage() {
        return "animals/showAll";// Thymeleaf a templates/animals/showAll.html-t keresi
    }

    @GetMapping("/add")
    public String showAddAnimalForm() {
        return "animals/add"; // Thymeleaf a templates/animals/add.html-t keresi
    }
}