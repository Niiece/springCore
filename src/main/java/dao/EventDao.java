package dao;

import model.AbstractDomainService;
import model.Event;
import org.springframework.lang.NonNull;

public interface EventDao extends AbstractDomainService<Event> {
    public Event getEventByName(@NonNull String eventName);
}
