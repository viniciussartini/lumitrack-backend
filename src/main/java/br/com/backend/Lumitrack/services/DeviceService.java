package br.com.backend.Lumitrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.Lumitrack.models.Area;
import br.com.backend.Lumitrack.models.Device;
import br.com.backend.Lumitrack.repositories.AreaRepository;
import br.com.backend.Lumitrack.repositories.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AreaRepository areaRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Device findById(Long id) {
        Optional<Device> deviceById = deviceRepository.findById(id);
        return deviceById.orElseThrow(() -> new EntityNotFoundException("Aparelho não encontrado."));
    }

    public Device create(Long areaId, Device device) {
        Area area = areaRepository.findById(areaId)
            .orElseThrow(() -> new EntityNotFoundException("Área não encontrada"));
        device.setArea(area);
        return device = deviceRepository.save(device);
    }

    public Device update(Long areaId, Long id, Device device) {
        Device existingDevice = deviceRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aparelho não encontrado."));
        if(!existingDevice.getArea().getId().equals(areaId)) {
            throw new IllegalStateException("Aparelho não pertence à esta área.");
        }
        updateEntity(existingDevice, device);
        return deviceRepository.save(existingDevice);
    }

    private void updateEntity(Device existingDevice, Device device) {
        existingDevice.setName(device.getName());
        existingDevice.setBrand(device.getBrand());
        existingDevice.setModel(device.getModel());
        existingDevice.setVoltage(device.getVoltage());
        existingDevice.setPower(device.getPower());
    }

    public void delete(Long areaId, Long id) {
        Device device = deviceRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aparelho não encontrado."));
        if(!device.getArea().getId().equals(areaId)) {
            throw new IllegalStateException("Aparelho não à esta área");
        }
        deviceRepository.delete(device);
    }
}
