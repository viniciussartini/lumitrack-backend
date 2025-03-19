package br.com.backend.Lumitrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.Lumitrack.models.Area;
import br.com.backend.Lumitrack.models.Building;
import br.com.backend.Lumitrack.repositories.AreaRepository;
import br.com.backend.Lumitrack.repositories.BuildingRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    public Area findById(Long id) {
        Optional<Area> areaById = areaRepository.findById(id);
        return areaById.orElseThrow(() -> new EntityNotFoundException("Área não encontrada"));
    }

    public Area create(Long buildingId, Area area) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Edificação não encontrada."));
        area.setBuilding(building);
        return area = areaRepository.save(area);
    }

    public Area update(Long buildingId, Long id, Area area) {
        Area existingArea = areaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área não encontrada."));
        if(!existingArea.getBuilding().getId().equals(buildingId)) {
            throw new IllegalStateException("Área não pertence à esta edificação.");
        }
        updateEntity(existingArea, area);
        return areaRepository.save(existingArea);
    }

    private void updateEntity(Area existingArea, Area area) {
        existingArea.setName(area.getName());
        existingArea.setSquareArea(area.getSquareArea());
    }

    public void delete(Long buildingId, Long id) {
        Area area = areaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Área não encontrada."));
        if(!area.getBuilding().getId().equals(buildingId)) {
            throw new IllegalStateException("Área não pertence à esta edificação.");
        }
        areaRepository.delete(area);
    }

}
