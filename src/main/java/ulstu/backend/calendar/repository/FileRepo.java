package ulstu.backend.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ulstu.backend.calendar.models.File;

public interface FileRepo extends JpaRepository<File, String> {
}