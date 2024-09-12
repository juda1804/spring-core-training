package app.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String title;
    private LocalDateTime dateTime;

    public static Event fromString(String s, ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(s,Event.class);
    }
}
