package dao.daoImp;

import dao.EventDao;
import model.Event;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EventDaoImp implements EventDao {

    private static List<Event> events = Arrays.asList(
            new Event("Star Wars 7", LocalDateTime.parse("2018-05-12T10:00:00")),
            new Event("Star Wars 8", LocalDateTime.parse("2018-05-12T12:00:00"))
    );

    @Override
    public void save(Event obj) {
        events.add(obj);
    }

    @Override
    public void remove(Event obj) {
        events.remove(obj);
    }

    @Override
    public Event getById(int id) {
        return events.get(id);
    }

    @Override
    public Collection<Event> getAll() {
        return events;
    }

    @Override
    public Event getEventByName(String eventName) {
        return events.stream().filter(event -> event.getName().contains(eventName)).findFirst().orElse(null);
    }
}
