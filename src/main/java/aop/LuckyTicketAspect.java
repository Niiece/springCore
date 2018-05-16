package aop;

import model.Ticket;
import model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Aspect
public class LuckyTicketAspect {

    @Pointcut("@annotation(aop.Lucky)")
    public void luckyTicketPointCut() {
    }


    @Around("luckyTicketPointCut() && args(user, ticket)")
    public void setTicketPrice (ProceedingJoinPoint jp, User user, Ticket ticket) {
        int random = new Random().nextInt(100);

        if (random > 90) {
            System.out.println("HOORAY! YOUR TICKET'S PRICE IS 0$");
            ticket.setPrice(0.0);
        }

        try {
            jp.proceed(new Object[] {user, ticket});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
