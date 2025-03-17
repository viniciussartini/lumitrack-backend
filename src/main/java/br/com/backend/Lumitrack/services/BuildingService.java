package br.com.backend.Lumitrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.backend.Lumitrack.models.Building;
import br.com.backend.Lumitrack.repositories.BuildingRepository;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    public Building findById(Long id) {
        Optional<Building> buildingById = buildingRepository.findById(id);
        return buildingById.orElseThrow(() -> new RuntimeException());  // Criar uma exceção personalizada
    }

    public Building create(Building building) {
        return buildingRepository.save(building);
    }

    public Building update(Long id, Building building) {
        try {
            Building entity = buildingRepository.getReferenceById(id);
            updateEntity(entity, building);
            return buildingRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e); // Criar uma exceção personalizada
        }
    }

    private void updateEntity(Building entity, Building building) {
        entity.setBuildingType(building.getBuildingType());
        entity.setName(building.getName());
        entity.setAddress(building.getAddress());
        entity.setCep(building.getCep());
        entity.setCity(building.getCity());
        entity.setState(building.getState());
        entity.setTotalArea(building.getTotalArea());
    }

    public void delete(Long id) {
        try {
            buildingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(); // Criar uma exceção personalizada
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(); // Criar uma exceção personalizada
        }
    }
}
