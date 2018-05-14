import controller.service.EventService;
import controller.service.LocationService;
import controller.service.TicketService;
import controller.service.UserService;
import controller.service.serviceImp.EventServiceImp;
import controller.service.serviceImp.LocationServiceImp;
import controller.service.serviceImp.TicketServiceImp;
import controller.service.serviceImp.UserServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanSupplier {

    @Bean
    public EventService eventService() {
        return new EventServiceImp();
    }

    @Bean
    public LocationService locationService() {
        return new LocationServiceImp();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketServiceImp();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImp();
    }

}
