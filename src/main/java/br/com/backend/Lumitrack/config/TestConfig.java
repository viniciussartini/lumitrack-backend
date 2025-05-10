package br.com.backend.Lumitrack.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.backend.Lumitrack.models.Area;
import br.com.backend.Lumitrack.models.Building;
import br.com.backend.Lumitrack.models.Consumption;
import br.com.backend.Lumitrack.models.Device;
import br.com.backend.Lumitrack.models.User;
import br.com.backend.Lumitrack.models.enums.BuildingType;
import br.com.backend.Lumitrack.repositories.AreaRepository;
import br.com.backend.Lumitrack.repositories.BuildingRepository;
import br.com.backend.Lumitrack.repositories.ConsumptionRepository;
import br.com.backend.Lumitrack.repositories.DeviceRepository;
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

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ConsumptionRepository consumptionRepository;

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
        
        Device device1 = new Device(null, "Televisão", "LG", "Braba", 127.0, 200.0, area1);
        Device device2 = new Device(null, "Lâmpada", "Avant", "Braba", 127.0, 16.0, area1);
        Device device3 = new Device(null, "Televisão", "Samsung", "Braba", 127.0, 200.0, area2);
        Device device4 = new Device(null, "Lâmpada", "Avant", "Braba", 127.0, 12.0, area2);
        deviceRepository.saveAll(Arrays.asList(device1, device2, device3, device4));

        Consumption consumption1 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 42.0, device1);
        Consumption consumption2 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 24.0, device1);
        Consumption consumption3 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 35.0, device2);
        Consumption consumption4 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 10.0, device2);
        Consumption consumption5 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 62.0, device3);
        Consumption consumption6 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 75.0, device3);
        Consumption consumption7 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 21.0, device4);
        Consumption consumption8 = new Consumption(null, Instant.parse("2025-01-01T00:00:00Z"), 39.0, device4);
        consumptionRepository.saveAll(Arrays.asList(consumption1, consumption2, consumption3, consumption4, consumption5, consumption6, consumption7, consumption8));
    }

    
}
