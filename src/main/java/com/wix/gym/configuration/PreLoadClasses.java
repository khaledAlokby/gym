package com.wix.gym.configuration;

import com.wix.gym.repository.ClassRepository;
import com.wix.gym.model.Class;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreLoadClasses {

    @Bean
    CommandLineRunner initDatabase(ClassRepository classRepository){
        return args -> {
            classRepository.save(new Class("Zumba", "Zumba is an exercise fitness program", 22.5,60, 20, "Hadas Gelbert"));
            classRepository.save(new Class("RPM", "Indoor spinning and cycling class", 15.99,90, 30, "Yuval Shahar"));
            classRepository.save(new Class("Yoga", "a group of physical, mental and spiritual practices", 25,60, 8, "Roytal Harosh"));
            classRepository.save(new Class("Pilates", "a physical fitness system", 20,30, 15, "Shahar Golov"));
        };
    }
}
