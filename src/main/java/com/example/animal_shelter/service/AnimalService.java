package com.example.animal_shelter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.animal_shelter.api.models.Animal;
import com.example.animal_shelter.api.models.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepo;

    public List<Animal> findAll() {
        return animalRepo.findAll();
    }

    public Optional<Animal> findById(Integer id) {
        return animalRepo.findById(id);
    }

    public Animal save(Animal animal) {
        return animalRepo.save(animal);
    }
}