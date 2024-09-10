package domain;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private Long userId;
    private Long eventId;
    private int place;
    private TicketCategory category;

    public enum TicketCategory { STANDARD, PREMIUM, VIP }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public void setCategory(TicketCategory category) {
        this.category = category;
    }


    // Getters and Setters
}