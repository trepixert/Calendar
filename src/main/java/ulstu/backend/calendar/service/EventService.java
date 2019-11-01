package ulstu.backend.calendar.service;

import org.springframework.stereotype.Service;
import ulstu.backend.calendar.dto.EventDTO;

import java.util.List;

@Service
public interface EventService {
    List<EventDTO> findAll();
    void save(EventDTO event);
}
