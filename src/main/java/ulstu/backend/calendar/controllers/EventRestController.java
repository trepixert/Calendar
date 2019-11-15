package ulstu.backend.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ulstu.backend.calendar.dto.EventDTO;
import ulstu.backend.calendar.models.User;
import ulstu.backend.calendar.service.EventService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventRestController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventDTO> getAllEvents(@AuthenticationPrincipal User user) {
        return eventService.findAllByUser(user);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody EventDTO event, @AuthenticationPrincipal User user) {
        eventService.save(event, user);
        return ResponseEntity.ok("Saved");
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        eventService.delete(id, user);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/search")
    public List<EventDTO> searchByTitle(@RequestParam("title") String title, @AuthenticationPrincipal User user) {
        return eventService.findByTitleContainingIgnoreCaseAndUser(title, user);
    }
}
