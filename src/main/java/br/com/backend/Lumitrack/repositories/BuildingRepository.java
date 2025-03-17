package br.com.backend.Lumitrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.backend.Lumitrack.models.Building;

public interface BuildingRepository extends JpaRepository<Building, Long>{
}
