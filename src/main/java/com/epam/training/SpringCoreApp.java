package com.epam.training;

import com.epam.training.dao.impl.UserRepositoryImpl;
import com.epam.training.domain.User;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCoreApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringCoreApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DataGenerator dataGenerator, UserRepositoryImpl userRepositoryImpl) {
        return args -> {
            dataGenerator.generateData();
            List<User> users = userRepositoryImpl.findAllWithMoreThan100EventsAnd100TicketsInMarch2025();
            users.forEach(user -> System.out.println(user.getName() + " - " + user.getEmail()));
        };
    }
}
