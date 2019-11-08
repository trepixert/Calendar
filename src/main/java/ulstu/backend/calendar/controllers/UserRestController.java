package ulstu.backend.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ulstu.backend.calendar.dto.UserDTO;
import ulstu.backend.calendar.models.User;
import ulstu.backend.calendar.service.UserService;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return userService.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<String> create(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity.ok("Saved");
    }
}
