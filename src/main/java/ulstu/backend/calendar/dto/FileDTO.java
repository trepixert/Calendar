package ulstu.backend.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDTO {
    private String url;
    private String filename;
    private Long eventId;
}
