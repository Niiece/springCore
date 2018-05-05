import controller.GlobalController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        GlobalController globalController = applicationContext.getBean(GlobalController.class);
        globalController.makeTheChoice();
        applicationContext.close();

    }
}
