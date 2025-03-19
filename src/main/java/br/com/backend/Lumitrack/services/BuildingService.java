package br.com.backend.Lumitrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.Lumitrack.models.Building;
import br.com.backend.Lumitrack.models.User;
import br.com.backend.Lumitrack.repositories.BuildingRepository;
import br.com.backend.Lumitrack.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    public Building findById(Long id) {
        Optional<Building> buildingById = buildingRepository.findById(id);
        return buildingById.orElseThrow(() -> new EntityNotFoundException("Edificação não encontrada."));
    }

    public Building create(Long userId, Building building) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        building.setUser(user);
        return building = buildingRepository.save(building);
    }

    public Building update(Long userId, Long id, Building building) {
        Building existBuilding = buildingRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Edificação não encontrada."));
        if(!existBuilding.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Edificação não pertence à este usuário.");
        }
        updateEntity(existBuilding, building);
        return buildingRepository.save(existBuilding);
    }

    private void updateEntity(Building existBuilding, Building building) {
        existBuilding.setBuildingType(building.getBuildingType());
        existBuilding.setName(building.getName());
        existBuilding.setAddress(building.getAddress());
        existBuilding.setCep(building.getCep());
        existBuilding.setCity(building.getCity());
        existBuilding.setState(building.getState());
        existBuilding.setTotalArea(building.getTotalArea());
    }

    public void delete(Long userId, Long id) {
        Building building = buildingRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Edificação não encontrada."));
        if(!building.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Edificação não pertence à este usuário.");
        }
        buildingRepository.delete(building);
    }
}
