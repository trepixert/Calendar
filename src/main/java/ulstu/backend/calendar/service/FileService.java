package ulstu.backend.calendar.service;

import org.springframework.web.multipart.MultipartFile;
import ulstu.backend.calendar.dto.FileDTO;

import java.util.List;
import java.util.Optional;

public interface FileService {
    List<FileDTO> findAll();
    Optional<FileDTO> save(MultipartFile file, Long user);
}
