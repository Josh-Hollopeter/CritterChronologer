package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet){
       return petRepository.saveAndFlush(pet);

    }

    public List<Pet> getPetsByOwnerId(long id){

        return petRepository.getAllByOwnerId(id);
    }

    public Pet getPetById(long id){
        return petRepository.getPetById(id);
    }
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }
}
