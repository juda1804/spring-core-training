package com.epam.training;

import com.epam.training.dao.EventRepository;
import com.epam.training.dao.TicketRepository;
import com.epam.training.dao.UserRepository;
import com.epam.training.domain.Event;
import com.epam.training.domain.Ticket;
import com.epam.training.domain.User;
import com.epam.training.facade.BookingFacade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataGenerator {
    @Autowired
    private BookingFacade bookingFacade;
    @Autowired
    private UserRepository userRepositoryImpl;
    @Autowired
    private EventRepository eventRepositoryImpl;
    @Autowired
    private TicketRepository ticketRepositoryImpl;

    private final Random random = new Random();

    public void generateData() {
        Map<Long, String> userMap = generateUsers(1000);
        List<Event> events = generateEvents(500);
        generateTickets(userMap, events, 300000);
        log.info("Generated data successfully");


    }

    private Map<Long, String> generateUsers(int count) {
        Map<Long, String> userMap = new HashMap<>();
        for (long i = 1; i <= count; i++) {
            User user = new User();
            bookingFacade.createUser(i, "User" + i, "user" + i + "@example.com");
            userMap.put(i, user.getName());
        }
        return userMap;
    }

    private List<Event> generateEvents(int count) {
        List<Event> events = new ArrayList<>();
        for (long i = 1; i <= count; i++) {
            Event event = bookingFacade.createEvent(i, "Event" + i, LocalDateTime.now().plusDays(random.nextInt(365)));
            eventRepositoryImpl.save(event);
            events.add(event);
        }
        return events;
    }

    private void generateTickets(Map<Long, String> userMap, List<Event> events, int count) {
        List<Long> userIds = new ArrayList<>(userMap.keySet());
        for (long i = 1; i <= count; i++) {
            Long userId = userIds.get(random.nextInt(userIds.size()));
            Event event = events.get(random.nextInt(events.size()));
            Ticket.TicketCategory category = Ticket.TicketCategory.values()[random.nextInt(Ticket.TicketCategory.values().length)];
            Ticket ticket = bookingFacade.bookTicket(i, event.getId(), userId, random.nextInt(100) + 1, category.name());
            ticketRepositoryImpl.save(ticket);
        }
    }
}

