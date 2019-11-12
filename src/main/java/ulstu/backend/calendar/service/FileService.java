package ulstu.backend.calendar.service;

import org.springframework.web.multipart.MultipartFile;
import ulstu.backend.calendar.dto.FileDTO;
import ulstu.backend.calendar.models.User;

import java.util.List;
import java.util.Optional;

public interface FileService {
    List<FileDTO> findAllByUser(User user);
    Optional<FileDTO> save(MultipartFile file, User user);
}
