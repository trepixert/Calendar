package ulstu.backend.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ulstu.backend.calendar.models.User;

public interface UserRepo extends JpaRepository<User,Long> {
}
