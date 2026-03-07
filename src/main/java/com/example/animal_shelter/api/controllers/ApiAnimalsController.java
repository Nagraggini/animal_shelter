package com.example.animal_shelter.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.animal_shelter.models.Animal;
import com.example.animal_shelter.service.AnimalService;

/*
Ezekkel az /api/animals és /api/animals/{id} végpontok JSON-t adnak vissza, és POST-tal új állatot lehet létrehozni.
 */
@RestController
@RequestMapping("/api/animals")
public class ApiAnimalsController {

    private final AnimalService animalService;

    @Autowired
    public ApiAnimalsController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> getAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable Integer id) {
        return animalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal saved = animalService.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/animals/view")
    public String viewAnimals(Model model) {
        List<Animal> animals = animalService.findAll();
        model.addAttribute("ani", animals);
        return "animals/showAll"; // a html fájl neve
    }
}
