package controller.service.serviceImp;

import aop.Lucky;
import controller.service.BookingService;
import dao.TicketDao;
import model.Ticket;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingService{

    @Autowired
    private TicketDao ticketDao;

    @Override @Lucky
    public void purchaseTicket(User user, Ticket ticket) {
        ticketDao.setUserToTicket(ticket, user);
    }
}
