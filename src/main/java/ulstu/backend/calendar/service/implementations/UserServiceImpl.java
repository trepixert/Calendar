package ulstu.backend.calendar.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ulstu.backend.calendar.dto.EventDTO;
import ulstu.backend.calendar.dto.UserDTO;
import ulstu.backend.calendar.models.User;
import ulstu.backend.calendar.repository.UserRepo;
import ulstu.backend.calendar.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<UserDTO> findAll() {
        return userRepo.findAll()
                .stream()
                .map(user -> new UserDTO(user.getUsername(),
                        user.getEvents().stream()
                                .map(event -> new EventDTO(event.getId(), event.getTitle(),
                                        event.getStart(), event.getFinish()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UserDTO user) {
        User newUser =
                User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build();
        userRepo.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username);
    }
}
