package controller.service.serviceImp;

import aop.Counter;
import controller.service.EventService;
import dao.EventDao;
import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EventServiceImp implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override @Counter
    public Event getEventByName(String eventName) {
        return this.eventDao.getEventByName(eventName);
    }

    @Override
    public void save(String eventName, String dateTime) {
        this.eventDao.save(eventName, dateTime);
    }

    @Override
    public void remove(String eventName) {
        eventDao.remove(eventName);
    }

    @Override
    public void save(Event obj) {
        this.eventDao.save(obj);
    }

    @Override
    public void remove(Event obj) {
        this.eventDao.remove(obj);
    }

    @Override
    public Event getById(int id) {
        return this.eventDao.getById(id);
    }

    @Override
    public Collection<Event> getAll() {
        return this.eventDao.getAll();
    }
}
