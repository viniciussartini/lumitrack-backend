package br.com.backend.Lumitrack.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.backend.Lumitrack.models.Building;
import br.com.backend.Lumitrack.services.BuildingService;

@RestController
@RequestMapping(value = "/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<List<Building>> findAll() {
        List<Building> buildingsList = buildingService.findAll();
        return ResponseEntity.ok().body(buildingsList);
    }

    @PostMapping
    public ResponseEntity<Building> create(@RequestBody Building building) {
        building = buildingService.create(building);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(building.getId()).toUri();
        return ResponseEntity.created(uri).body(building);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Building> update(@PathVariable Long id, @RequestBody Building building) {
        building = buildingService.update(id, building);
        return ResponseEntity.ok().body(building);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        buildingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
