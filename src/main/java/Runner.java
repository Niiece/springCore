import controller.GlobalController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        GlobalController globalController = applicationContext.getBean(GlobalController.class);
        globalController.makeTheChoise();

//        System.out.println("----------");
//
//        ticketDao.save(new Ticket(new Event(
//                "test", LocalDateTime.now()), new Location("hall of fame", 10), 8.5, 10));
//
//        ticketDao.getAll().stream().forEach(System.out::println);

//         List<Ticket> tickets = Arrays.asList(
//                new Ticket(eventDao.getById(0), locationDao.getById(0), 8.5, 10)
////            new Ticket(eventDao.getById(1), locationDao.getById(1), 9.0, 10)
//        );
//        System.out.println(tickets.get(0).toString());
    }
}
