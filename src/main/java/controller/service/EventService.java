package controller.service;

import model.AbstractDomainService;
import model.Event;
import org.springframework.lang.NonNull;

public interface EventService extends AbstractDomainService<Event> {

    public Event getEventByName(@NonNull String eventName);
}
