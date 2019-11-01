package ulstu.backend.calendar.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDTO {
    @NonNull
    private String username;
    private String password;
    @NonNull
    private List<EventDTO> events;
}

