package ulstu.backend.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ulstu.backend.calendar.dto.EventDTO;
import ulstu.backend.calendar.models.Event;
import ulstu.backend.calendar.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventRestController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventDTO> getAllEvents(){
        return eventService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Event event){
        eventService.save(event);
        return ResponseEntity.ok("Saved");
    }
}
