package controller.service;

import aop.Counter;
import model.AbstractDomainService;
import model.Event;
import org.springframework.lang.NonNull;

public interface EventService extends AbstractDomainService<Event> {

    @Counter
    public Event getEventByName(@NonNull String eventName);
    public void save(@NonNull String eventName, @NonNull String dateTime);
    public void remove(@NonNull String eventName);
}
