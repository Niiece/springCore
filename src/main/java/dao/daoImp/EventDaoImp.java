package dao.daoImp;

import dao.EventDao;
import model.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class EventDaoImp implements EventDao {

    private List<Event> events = new ArrayList<>(Arrays.asList(
            new Event("Star Wars 7", LocalDateTime.parse("2018-05-12T10:00:00")),
            new Event("Star Wars 8", LocalDateTime.parse("2018-05-12T12:00:00"))
    ));

    public void save(String eventName, String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse(dateTime, format);
        Event event = new Event(eventName, formattedDate);
        save(event);
    }

    @Override
    public void remove(String eventName) {
        remove(events.stream().filter(e -> StringUtils.equalsIgnoreCase(e.getName(), eventName)).findFirst().orElse(null));
    }

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
