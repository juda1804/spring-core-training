package config;

// src/main/java/com/example/config/StorageInitializer.java
package com.example.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Event;
import domain.Ticket;
import domain.User;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@Component
public class StorageInitializer implements InitializingBean {

    private final String filePath;

    private final Map<String, User> userStorage;
    private final Map<String, Event> eventStorage;
    private final Map<String, Ticket> ticketStorage;

    public StorageInitializer(@Value("${data.file.path}") String filePath,
                              Map<String, User> userStorage,
                              Map<String, Event> eventStorage,
                              Map<String, Ticket> ticketStorage) {
        this.filePath = filePath;
        this.userStorage = userStorage;
        this.eventStorage = eventStorage;
        this.ticketStorage = ticketStorage;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Leer el archivo y cargar los mapas en memoria

        List<String> strings = Files.readAllLines(Paths.get(filePath));
        ObjectMapper mapper = new ObjectMapper();

        for (String stringObjects : strings) {
            JsonNode node = mapper.readValue(stringObjects, JsonNode.class);
            String type = node.get("type").asText();
            String data = node.toString();

            switch (type) {
                case "event":
                    Event event = Event.fromString(data, mapper);
                    eventStorage.put(event.getTitle(), event);
                    // Procesa el evento
                    break;
                case "ticket":
                    Ticket ticket = Ticket.fromString(data);
                    // Procesa el ticket
                    break;
                case "user":
                    User user = User.fromString(data);
                    // Procesa el usuario
                    break;
            }
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(filePath)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    if (key.startsWith("user:")) {
                        userStorage.put(key, new User(value)); // Aquí podrías convertir el value a un objeto User
                    } else if (key.startsWith("event:")) {
                        eventStorage.put(key, new Event(value)); // Aquí podrías convertir el value a un objeto Event
                    } else if (key.startsWith("ticket:")) {
                        ticketStorage.put(key, new Ticket(value)); // Aquí podrías convertir el value a un objeto Ticket
                    }
                }
            }
        }
    }
}

