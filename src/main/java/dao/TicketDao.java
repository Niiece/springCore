package dao;

import model.AbstractDomainService;
import model.Ticket;
import model.User;

import java.util.List;

public interface TicketDao extends AbstractDomainService<Ticket> {

    List<Ticket> getAllUserTickets(User user);
    public void setUserToTicket(Ticket ticket, User user);
}
