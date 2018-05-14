import dao.EventDao;
import dao.LocationDao;
import dao.TicketDao;
import dao.UserDao;
import dao.daoImp.EventDaoImp;
import dao.daoImp.LocationDaoImp;
import dao.daoImp.TicketDaoImp;
import dao.daoImp.UserDaoImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoBeanSupplier {

    @Bean
    public UserDao userDao() {
        return new UserDaoImp();
    }

    @Bean
    public EventDao eventDao() {
        return new EventDaoImp();
    }

    @Bean
    public LocationDao locationDao() {
        return new LocationDaoImp();
    }

    @Bean
    public TicketDao ticketDao() {
        return new TicketDaoImp(eventDao(), locationDao());
    }


}
