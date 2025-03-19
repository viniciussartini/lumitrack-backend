package br.com.backend.Lumitrack.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.backend.Lumitrack.models.Area;
import br.com.backend.Lumitrack.models.Building;
import br.com.backend.Lumitrack.models.User;
import br.com.backend.Lumitrack.models.enums.BuildingType;
import br.com.backend.Lumitrack.repositories.AreaRepository;
import br.com.backend.Lumitrack.repositories.BuildingRepository;
import br.com.backend.Lumitrack.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User(null, "Admin", "admin@email.com", "1234", br.com.backend.Lumitrack.models.enums.Profile.ADMIN);
        User user1 = new User(null, "Mario", "mario@email.com", "1234", br.com.backend.Lumitrack.models.enums.Profile.COMMON);
        userRepository.saveAll(Arrays.asList(admin, user1));

        Building building1 = new Building(null, BuildingType.APARTMENT, user1, "Apartamento do Mario", "Rua do Mario", "00000-000", "Marioland", "MarioEstado", 80.0);
        Building building2 = new Building(null, BuildingType.HOUSE, user1, "Casa do Mario", "Rua do Mario", "00000-000", "Marioland", "MarioEstado", 100.0);
        buildingRepository.saveAll(Arrays.asList(building1, building2));

        Area area1 = new Area(null, "Sala", 12.0, building2);
        Area area2= new Area(null, "Quarto", 16.0, building2);
        areaRepository.saveAll(Arrays.asList(area1,area2));
    }

    
}
