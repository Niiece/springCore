package controller.service.serviceImp;

import controller.service.TicketService;
import dao.TicketDao;
import model.Ticket;

import java.util.Collection;

public class TicketServiceImp implements TicketService {

    private TicketDao ticketDao;

    public TicketServiceImp(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void save(Ticket obj) {
        this.ticketDao.save(obj);
    }

    @Override
    public void remove(Ticket obj) {
        this.ticketDao.remove(obj);
    }

    @Override
    public Ticket getById(int id) {
        return this.ticketDao.getById(id);
    }

    @Override
    public Collection<Ticket> getAll() {
        return this.ticketDao.getAll();
    }
}
