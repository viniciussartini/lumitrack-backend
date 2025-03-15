package br.com.backend.Lumitrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.backend.Lumitrack.models.User;
import br.com.backend.Lumitrack.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.orElseThrow(() -> new RuntimeException());  // Criar uma exceção personalizada
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        try {
            User entity = userRepository.getReferenceById(id);
            updateEntity(entity, user);
            return userRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e); // Criar uma exceção personalizada
        }
    }

    private void updateEntity(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(); // Criar uma exceção personalizada
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(); // Criar uma exceção personalizada
        }
    }
}
