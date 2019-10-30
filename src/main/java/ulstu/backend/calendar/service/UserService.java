package ulstu.backend.calendar.service;

import org.springframework.stereotype.Service;
import ulstu.backend.calendar.dto.UserDTO;
import ulstu.backend.calendar.models.User;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> findAll();
    void save(User user);
}
