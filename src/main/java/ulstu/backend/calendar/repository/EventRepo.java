package ulstu.backend.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ulstu.backend.calendar.models.Event;

public interface EventRepo extends JpaRepository<Event,Long> {
}
