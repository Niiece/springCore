package dao.daoImp;


import dao.EventDao;
import dao.LocationDao;
import dao.TicketDao;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TicketDaoImp implements TicketDao {

    private List<Ticket> tickets;

    private EventDao eventDao;
    private LocationDao locationDao;

    public TicketDaoImp(EventDao eventDao, LocationDao locationDao) {
        this.locationDao = locationDao;
        this.eventDao = eventDao;
        tickets = new ArrayList<>(Arrays.asList(
                new Ticket(this.eventDao.getById(0), this.locationDao.getById(0), 8.5, 10),
                new Ticket(this.eventDao.getById(1), this.locationDao.getById(1), 9.0, 10)
        ));
    }


    @Override
    public void save(Ticket obj) {
        this.tickets.add(obj);
    }

    @Override
    public void remove(Ticket obj) {
        tickets.remove(obj);
    }

    @Override
    public Ticket getById(int id) {
        return tickets.get(id);
    }

    @Override
    public Collection<Ticket> getAll() {
        return tickets;
    }

}
