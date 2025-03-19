package br.com.backend.Lumitrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.backend.Lumitrack.models.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>{
}
