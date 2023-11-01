package com.fzoo.zoomanagementsystem.service;

import com.fzoo.zoomanagementsystem.model.UnidentifiedAnimal;
import com.fzoo.zoomanagementsystem.model.Cage;
import com.fzoo.zoomanagementsystem.repository.UnidentifiedAnimalRepository;
import com.fzoo.zoomanagementsystem.repository.CageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnidentifiedAnimalService {
    private final UnidentifiedAnimalRepository unidentifiedAnimalRepository;
    private final CageRepository cageRepository;

    public List<UnidentifiedAnimal> getAllAnimalSpecies() {
        List<UnidentifiedAnimal> unidentifiedAnimalList = unidentifiedAnimalRepository.findAll(Sort.by(Sort.Direction.ASC, "cageId"));
        if (unidentifiedAnimalList.isEmpty()) throw new IllegalStateException("There are no Animal Species");
        return unidentifiedAnimalList;
    }

    public UnidentifiedAnimal getAnimalSpeciesByID(int id) {
        return unidentifiedAnimalRepository.findById(id).orElseThrow(() -> new IllegalStateException("No result for Animal Species searching!"));
    }

    public List<UnidentifiedAnimal> getAnimalSpeciesByName(String animalSpecieName) {
        List<UnidentifiedAnimal> unidentifiedAnimalList = unidentifiedAnimalRepository.findByName(animalSpecieName);
        if (unidentifiedAnimalList.isEmpty()) throw new IllegalStateException("No result for Animal Species searching");
        return unidentifiedAnimalList;
    }

    public List<UnidentifiedAnimal> getAnimalSpeciesByCageID(int cageID) {
        List<UnidentifiedAnimal> unidentifiedAnimalList = unidentifiedAnimalRepository.findByCageId(cageID);
        if (unidentifiedAnimalList.isEmpty()) throw new IllegalStateException("No result for Animal Species searching");
        return unidentifiedAnimalList;
    }

    public void updateCageQuantity(int cageID) {
        List<UnidentifiedAnimal> unidentifiedAnimalList = unidentifiedAnimalRepository.findByCageId(cageID);
        Cage cage = cageRepository.findCageById(cageID);
        int cageQuantity = 0;
        for (UnidentifiedAnimal unidentifiedAnimal : unidentifiedAnimalList) {
            cageQuantity += unidentifiedAnimal.getQuantity();
        }
        cage.setQuantity(cageQuantity);
        cageRepository.save(cage);
    }

    public void UpdateAnimalSpecies(int animalSpecieID, UnidentifiedAnimal request) {
        UnidentifiedAnimal unidentifiedAnimal = unidentifiedAnimalRepository.findById(animalSpecieID).orElseThrow(() -> new IllegalStateException("Animal specie with " + animalSpecieID + " is not found"));
        if (request.getName() != null) unidentifiedAnimal.setName(request.getName());
        unidentifiedAnimal.setQuantity(request.getQuantity());
        unidentifiedAnimal.setCageId(request.getCageId());
        unidentifiedAnimalRepository.save(unidentifiedAnimal);
        updateCageQuantity(unidentifiedAnimal.getCageId());
    }

    public void CreateAnimalSpecies(UnidentifiedAnimal unidentifiedAnimal) {
        List<UnidentifiedAnimal> unidentifiedAnimalList = unidentifiedAnimalRepository.findByCageId(unidentifiedAnimal.getCageId());
        Cage cage = cageRepository.findCageById(unidentifiedAnimal.getCageId());
        boolean isDuplicated = false;
        for (UnidentifiedAnimal species : unidentifiedAnimalList) {
            if (species.getName().equalsIgnoreCase(unidentifiedAnimal.getName())) isDuplicated = true;
        }
        if (cage != null && !isDuplicated) {
            unidentifiedAnimalRepository.save(unidentifiedAnimal);
        } else if (cage != null && isDuplicated)
            throw new IllegalStateException("This Animal Species is already exists in the cage");
        else throw new IllegalStateException("Cage not found");
        updateCageQuantity(unidentifiedAnimal.getCageId());
    }

    public void deleteAnimalSpecies(int animalSpeicesID) {
        UnidentifiedAnimal unidentifiedAnimal = unidentifiedAnimalRepository.findById(animalSpeicesID).orElseThrow(() -> new IllegalStateException("Can not find Animal Species to delete"));
        unidentifiedAnimalRepository.deleteById(animalSpeicesID);
        updateCageQuantity(unidentifiedAnimal.getCageId());
    }
}