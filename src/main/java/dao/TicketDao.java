package dao;

import model.AbstractDomainService;
import model.Event;
import model.Ticket;
import model.User;

import java.util.List;

public interface TicketDao extends AbstractDomainService<Ticket> {

    List<Ticket> getAllUserTickets(User user);
    public void setUserToTicket(Ticket ticket, User user);
    public void removeTicketsForSpecifiedEvent (Event event);
    public List<Ticket> getAllTicketsForEvent(Event event);
}
