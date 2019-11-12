package ulstu.backend.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private Date start;
    private Date end;
}
