package app;

import static app.domain.TicketCategory.STANDARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import app.dao.StorageBean;
import app.domain.Event;
import app.domain.Ticket;
import app.domain.TicketCategory;
import app.domain.User;
import app.service.EventService;
import app.service.TicketService;
import app.service.UserService;
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
        ticketService.bookTicket(new Ticket(101L, 1L, 1L, 100, STANDARD));
        Ticket ticket = ticketService.getTicketById(101L);
        assertEquals(STANDARD, ticket.getCategory());
    }

    @Test
    void testCreateUser() {
        userService.createUser(new User(9999L, "Juan", "juan@epam.com"));
        User user = userService.getUserById(9999L);
        assertEquals("Juan", user.getName());
    }

}