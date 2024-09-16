package com.epam.training;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import com.epam.training.domain.Event;
import com.epam.training.domain.Ticket;
import com.epam.training.domain.User;
import com.epam.training.service.EventService;
import com.epam.training.service.TicketService;
import com.epam.training.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCoreAppTest {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Test
    void testMain() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(SpringCoreApp.class, new String[]{})).thenReturn(null);
            SpringCoreApp.main(new String[]{});
            mockedSpringApplication.verify(() -> SpringApplication.run(SpringCoreApp.class, new String[]{}));
        }
    }

    @Test
    void testCreateEvent() {
        eventService.createEvent(new Event(100L,"demo", LocalDateTime.now()));
        Event eventById = eventService.getEventById(100L);
        assertEquals("demo", eventById.getTitle());
    }

    @Test
    void testCreateTicket() {
        ticketService.bookTicket(
                new Ticket(
                        101L,
                        new User(12312L, "test", "email@email.com"),
                        1L,
                        100,
                        Ticket.TicketCategory.STANDARD)
        );
        Ticket ticket = ticketService.getTicketById(101L);
        assertEquals(Ticket.TicketCategory.STANDARD, ticket.getCategory());
    }

    @Test
    void testCreateUser() {
        userService.createUser(new User(102L, "Juan", "juan@epam.com"));
        User user = userService.getUserById(102L);
        assertEquals("Juan", user.getName());
    }

}