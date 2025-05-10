package br.com.backend.Lumitrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.Lumitrack.models.Consumption;
import br.com.backend.Lumitrack.models.Device;
import br.com.backend.Lumitrack.repositories.ConsumptionRepository;
import br.com.backend.Lumitrack.repositories.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Consumption> findAll() {
        return consumptionRepository.findAll();
    }

    public Consumption findById(Long id) {
        Optional<Consumption> consumptionById = consumptionRepository.findById(id);
        return consumptionById.orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado."));
    }

    public Consumption create (Long deviceId, Consumption consumption) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new EntityNotFoundException("Aparelho não encontrado"));
        consumption.setDevice(device);
        return consumption = consumptionRepository.save(consumption);
    }

    public Consumption update(Long deviceId, Long id, Consumption consumption) {
        Consumption existingConsumption = consumptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado"));
        if (!existingConsumption.getDevice().getId().equals(deviceId)) {
            throw new IllegalStateException("Consumo não pertence à esse aparelho.");
        }
        updateEntity(existingConsumption, consumption);
        return consumptionRepository.save(existingConsumption);
    }

    private void updateEntity(Consumption existingConsumption, Consumption consumption) {
        existingConsumption.setData(consumption.getData());
        existingConsumption.setUsageTime(consumption.getUsageTime());
        //existingConsumption.setDailyConsumption(consumption.getDailyConsumption());
    }

    public void delete(Long deviceId, Long id) {
        Consumption consumption = consumptionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado."));
        if (!consumption.getDevice().getId().equals(deviceId)) {
            throw new IllegalStateException("Consumo não pertence à esse aparelho");
        }
        consumptionRepository.delete(consumption);
    }
}
