package br.com.backend.Lumitrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.backend.Lumitrack.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
