package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import app.dao.StorageBean;
import app.domain.Event;
import app.domain.Ticket;
import app.domain.User;
import app.service.EventService;
import app.service.TicketService;
import app.service.UserService;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringInitDataTest {
    @Autowired
    private StorageBean storageBean;

    @Test
    void testDataInitialized() {
        Map<Long, Object> events = storageBean.getStorage("event");
        Map<Long, Object> users = storageBean.getStorage("user");
        Map<Long, Object> tickets = storageBean.getStorage("ticket");
        assertEquals(4, events.size());
        assertEquals(4, users.size());
        assertEquals(4, tickets.size());
    }
}