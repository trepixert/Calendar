package ulstu.backend.calendar.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ulstu.backend.calendar.dto.UserDTO;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();
    void save(UserDTO user);
}
