package controller.service;

import model.*;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TicketService extends AbstractDomainService<Ticket>  {
    public List<Ticket> getAvailableTickets();
    public void createTicketsForNewEvent(Event event, Location location, double price);
    public List<Ticket> getPurchasedTickets();
}
