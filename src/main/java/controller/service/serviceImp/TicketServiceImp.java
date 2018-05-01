package controller.service.serviceImp;

import controller.service.TicketService;
import dao.TicketDao;
import model.Event;
import model.Location;
import model.Ticket;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    @Override
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
