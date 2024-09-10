package config;

import dao.EventDao;
import dao.TicketDao;
import dao.UserDao;
import domain.Event;
import domain.Ticket;
import domain.User;
import facade.BookingFacade;
import facade.BookingFacadeImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.EventService;
import service.TicketService;
import service.UserService;

@Configuration
public class AppConfig {

    @Value("${data.file.path}")
    private String filePath;

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public EventDao eventDao() {
        return new EventDao();
    }

    @Bean
    public TicketDao ticketDao() {
        return new TicketDao();
    }

    @Bean
    public UserService userService(UserDao userDao) {
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        return userService;
    }

    @Bean
    public EventService eventService(EventDao eventDao) {
        EventService eventService = new EventService();
        eventService.setEventDao(eventDao);
        return eventService;
    }

    @Bean
    public TicketService ticketService(TicketDao ticketDao) {
        TicketService ticketService = new TicketService();
        ticketService.setTicketDao(ticketDao);
        return ticketService;
    }

    @Bean
    public BookingFacade bookingFacade(UserService userService, EventService eventService, TicketService ticketService) {
        return new BookingFacadeImpl(userService, eventService, ticketService);
    }


    @Bean
    public Map<String, Event> createEventInitDataStore() {
        return new HashMap<>();
    }
    @Bean
    public Map<String, User> createUserInitDataStore() {
        return new HashMap<>();
    }
    @Bean
    public Map<String, Ticket> createTicketInitDataStore() {
        return new HashMap<>();
    }

    @Bean
    public StorageInitializer storageInitializer(
            Map<String, Event> initialEvents,
            Map<String, User> initialUsers,
            Map<String, Ticket> initialTickets
            ) {
        StorageInitializer initializer = new StorageInitializer(filePath, initialUsers,initialEvents, initialTickets);
        initializer.setFilePath(filePath);
        return initializer;
    }
}
