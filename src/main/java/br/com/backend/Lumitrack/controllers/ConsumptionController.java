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

import br.com.backend.Lumitrack.models.Consumption;
import br.com.backend.Lumitrack.services.ConsumptionService;

@RestController
@RequestMapping(value = "/consumptions")
public class ConsumptionController {
    
    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping
    public ResponseEntity<List<Consumption>> findAll() {
        List<Consumption> consumptionList = consumptionService.findAll();
        return ResponseEntity.ok().body(consumptionList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consumption> findById(@PathVariable Long id) {
        Consumption consumptionById = consumptionService.findById(id);
        return ResponseEntity.ok().body(consumptionById);
    }

    @PostMapping(value = "/device/{deviceId}")
    public ResponseEntity<Consumption> create(@PathVariable Long deviceId, @RequestBody Consumption consumption) {
        consumption = consumptionService.create(deviceId, consumption);
        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/device/{deviceId}")
        .buildAndExpand(consumption.getId()).toUri();
        return ResponseEntity.created(uri).body(consumption);
    }

    @PutMapping(value = "/device/{deviceId}/consumption/{id}")
    public ResponseEntity<Consumption> update(@PathVariable Long deviceId, @PathVariable Long id, @RequestBody Consumption consumption) {
        consumption = consumptionService.update(deviceId, id, consumption);
        return ResponseEntity.ok().body(consumption);
    }

    @DeleteMapping(value = "/device/{deviceId}/consumption/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long deviceId, @PathVariable Long id) {
        consumptionService.delete(deviceId, id);
        return ResponseEntity.noContent().build();
    }
}
