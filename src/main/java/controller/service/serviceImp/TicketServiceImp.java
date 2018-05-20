package controller.service.serviceImp;

import aop.Counter;
import controller.service.TicketService;
import dao.TicketDao;
import model.Event;
import model.Location;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TicketDao ticketDao;

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


    @Override
    @Counter
    public List<Ticket> getAvailableTickets() {
        return ticketDao.getAll().stream().filter(ticket -> Objects.isNull(ticket.getUser())).collect(Collectors.toList());
    }

    public void createTicketsForNewEvent(Event event, Location location, double price) {
        location.getAllSeats().forEach(seat -> ticketDao.save(new Ticket(event, location, price, seat)));
    }

    @Override
    public List<Ticket> getPurchasedTickets() {
        return ticketDao.getAll().stream().filter(ticket -> Objects.nonNull(ticket.getUser())).collect(Collectors.toList());
    }
}
