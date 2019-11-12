package ulstu.backend.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ulstu.backend.calendar.models.File;
import ulstu.backend.calendar.models.User;

import java.util.List;

public interface FileRepo extends JpaRepository<File, String> {
    List<File> findAllByUser(User user);
}
