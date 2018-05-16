package controller.service;

import aop.Lucky;
import model.Ticket;
import model.User;

public interface BookingService {

    @Lucky
    public void purchaseTicket(User user, Ticket ticket);

}
