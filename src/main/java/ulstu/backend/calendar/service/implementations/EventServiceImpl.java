package ulstu.backend.calendar.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulstu.backend.calendar.dto.EventDTO;
import ulstu.backend.calendar.models.Event;
import ulstu.backend.calendar.repository.EventRepo;
import ulstu.backend.calendar.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepo;

    @Override
    public List<EventDTO> findAll() {
        return eventRepo.findAll()
                .stream()
                .map(event -> new EventDTO(event.getId(), event.getTitle(), event.getDescription(),
                        event.getStart(), event.getFinish()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Event event) {
        eventRepo.save(event);
    }
}
