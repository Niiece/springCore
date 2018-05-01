import controller.GlobalController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        GlobalController globalController = applicationContext.getBean(GlobalController.class);
        globalController.makeTheChoice();

    }
}
