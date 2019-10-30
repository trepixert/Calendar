package ulstu.backend.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private List<EventDTO> events;
}

