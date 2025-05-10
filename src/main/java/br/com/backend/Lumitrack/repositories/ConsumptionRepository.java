package br.com.backend.Lumitrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.backend.Lumitrack.models.Consumption;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
}
