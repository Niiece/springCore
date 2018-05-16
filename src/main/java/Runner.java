import controller.GlobalController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanSupplier.class);

        GlobalController globalController = applicationContext.getBean(GlobalController.class);
        globalController.makeTheChoice();


        applicationContext.close();

    }
}
