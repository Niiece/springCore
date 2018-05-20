package controller.service.serviceImp;

import aop.Lucky;
import controller.service.BookingService;
import model.Ticket;
import model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingService{


    @Override @Lucky
    public void purchaseTicket(User user, Ticket ticket) {
        ticket.setUser(user);
    }
}
