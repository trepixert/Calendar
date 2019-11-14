package ulstu.backend.calendar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class File {
    @Id
    @NonNull
    private String id;
    @NonNull
    private String url;
    @NonNull
    private String filename;

    @ManyToOne
    private Event event;
}
