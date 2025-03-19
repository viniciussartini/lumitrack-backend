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

import br.com.backend.Lumitrack.models.Device;
import br.com.backend.Lumitrack.services.DeviceService;

@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<Device>> findAll() {
        List<Device> devicesList = deviceService.findAll();
        return ResponseEntity.ok().body(devicesList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Device> findById(@PathVariable Long id) {
        Device deviceById = deviceService.findById(id);
        return ResponseEntity.ok().body(deviceById);
    }

    @PostMapping(value = "/area/{areaId}")
    public ResponseEntity<Device> create(@PathVariable Long areaId, @RequestBody Device device) {
        device = deviceService.create(areaId, device);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/area/{areaId}")
            .buildAndExpand(device.getId()).toUri();
        return ResponseEntity.created(uri).body(device);
    }

    @PutMapping(value = "/area/{areaId}/device/{id}")
    public ResponseEntity<Device> update(@PathVariable Long areaId, @PathVariable Long id, @RequestBody Device device) {
        device = deviceService.update(areaId, id, device);
        return ResponseEntity.ok().body(device);
    }

    @DeleteMapping(value = "/area/{areaId}/device/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long areaId, @PathVariable Long id) {
        deviceService.delete(areaId, id);
        return ResponseEntity.noContent().build();
    }

}
