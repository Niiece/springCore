import controller.GlobalController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanSupplier.class);

        GlobalController globalController = applicationContext.getBean(GlobalController.class);
        globalController.makeTheChoice();

        applicationContext.close();

    }
}
