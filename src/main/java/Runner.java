import controller.GlobalController;
import dao.daoImp.UserDaoImp;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanSupplier.class);

        GlobalController globalController = applicationContext.getBean(GlobalController.class);
        globalController.makeTheChoice();

        applicationContext.close();

    }
}
