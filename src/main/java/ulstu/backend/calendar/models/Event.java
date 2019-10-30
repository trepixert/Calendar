package ulstu.backend.calendar.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date start;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date finish;

    @ManyToOne
    private User user;
}
