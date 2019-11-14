package ulstu.backend.calendar.service;

import org.springframework.stereotype.Service;
import ulstu.backend.calendar.dto.EventDTO;
import ulstu.backend.calendar.models.User;

import java.util.List;

@Service
public interface EventService {
    List<EventDTO> findAll();

    void save(EventDTO event, User user);

    void delete(Long id, User user);

    List<EventDTO> findAllByUser(User user);

    List<EventDTO> findByTitleContainingIgnoreCaseAndUser(String title, User user);
}
