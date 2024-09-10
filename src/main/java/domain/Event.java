package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String title;
    private LocalDateTime dateTime;

    public Event(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static Event fromString(String s, ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(s,Event.class);
    }

    // Getters and Setters
}
