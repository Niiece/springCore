package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Event extends DomainObject {
    private String name;
    private LocalDateTime dateTime;

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
