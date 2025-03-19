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

import br.com.backend.Lumitrack.models.Area;
import br.com.backend.Lumitrack.services.AreaService;

@RestController
@RequestMapping(value = "/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> findAll() {
        List<Area> areasList = areaService.findAll();
        return ResponseEntity.ok().body(areasList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Area> findById(@PathVariable Long id) {
        Area areaById = areaService.findById(id);
        return ResponseEntity.ok().body(areaById);
    }

    @PostMapping(value = "/building/{buildingId}")
    public ResponseEntity<Area> create(@PathVariable Long buildingId, @RequestBody Area area) {
        area = areaService.create(buildingId, area);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/building/{buildingId}")
                .buildAndExpand(area.getId()).toUri();
        return ResponseEntity.created(uri).body(area);
    }

    @PutMapping(value = "/building/{buildingId}/area/{id}")
    public ResponseEntity<Area> update(@PathVariable Long buildingId, @PathVariable Long id, @RequestBody Area area) {
        area = areaService.update(buildingId, id, area);
        return ResponseEntity.ok().body(area);
    }

    @DeleteMapping(value = "/building/{buildingId}/area/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long buildingId, @PathVariable Long id) {
        areaService.delete(buildingId, id);
        return ResponseEntity.noContent().build();
    }

}
