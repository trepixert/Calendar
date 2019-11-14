package ulstu.backend.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ulstu.backend.calendar.models.Event;
import ulstu.backend.calendar.models.User;

import java.util.List;

public interface EventRepo extends JpaRepository<Event,Long> {
    List<Event> findAllByUser(User user);
    List<Event> findByTitleContainingIgnoreCaseAndUser(String title, User user);
}
