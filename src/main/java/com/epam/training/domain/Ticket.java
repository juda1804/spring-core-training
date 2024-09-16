package com.epam.training.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private User user;
    private Long eventId;
    private int place;
    private TicketCategory category;

    public enum TicketCategory { STANDARD, PREMIUM, VIP }
}