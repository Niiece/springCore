package controller.service.serviceImp;

import controller.service.EventService;
import dao.EventDao;
import model.Event;

import java.util.Collection;

public class EventServiceImp implements EventService {

    private EventDao eventDao;

    public EventServiceImp(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event getEventByName(String eventName) {
        return this.eventDao.getEventByName(eventName);
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
