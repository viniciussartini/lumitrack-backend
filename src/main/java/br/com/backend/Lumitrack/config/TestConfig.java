package br.com.backend.Lumitrack.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.backend.Lumitrack.models.User;
import br.com.backend.Lumitrack.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User(null, "Admin", "admin@email.com", "1234", br.com.backend.Lumitrack.models.enums.Profile.ADMIN);
        User user1 = new User(null, "Mario", "mario@email.com", "1234", br.com.backend.Lumitrack.models.enums.Profile.COMMON);
        userRepository.saveAll(Arrays.asList(admin, user1));
    }

    
}
