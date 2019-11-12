package ulstu.backend.calendar.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ulstu.backend.calendar.dto.EventDTO;
import ulstu.backend.calendar.models.Event;
import ulstu.backend.calendar.models.User;
import ulstu.backend.calendar.repository.EventRepo;
import ulstu.backend.calendar.repository.UserRepo;
import ulstu.backend.calendar.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<EventDTO> findAll() {
        return eventRepo.findAll()
                .stream()
                .map(event -> new EventDTO(event.getId(), event.getTitle(),
                        event.getStart(), event.getFinish()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(EventDTO event, User user) {
        Event newEvent =
                Event.builder()
                        .title(event.getTitle())
                        .start(event.getStart())
                        .finish(event.getEnd())
                        .user(user)
                        .build();
        eventRepo.save(newEvent);
        user.getEvents().add(newEvent);
        userRepo.save(user);
    }

    @Override
    public void delete(Long id, User user) {
        eventRepo.deleteById(id);
    }

    @Override
    public List<EventDTO> findAllByUser(User user) {
        return eventRepo.findAllByUser(user)
                .stream()
                .map(event -> new EventDTO(event.getId(), event.getTitle(), event.getStart(), event.getFinish()))
                .collect(Collectors.toList());
    }
}
