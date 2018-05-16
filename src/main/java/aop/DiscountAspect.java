package aop;

import model.User;
import model.UserType;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Just to notify user if he purchased premium account
 */

@Component
@Aspect
public class DiscountAspect {


    @After("@annotation(aop.Discount) && args(user)")
    public void discountNotifier(User user) {
        if(user.getUserType().equals(UserType.PREMIUM)) {
            System.out.println("HOORAY! YOU ARE PREMIUM USER NOW! YOU HAVE 10% DISCOUNT!!");
        }
    }

}
