import controller.GlobalController;
import controller.SessionController;
import controller.SessionControllerImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import utils.TableBuilderUtil;

import java.util.List;

@Configuration
@PropertySource("mainMenu.properties")
public class BeanSupplier {

    @Value("#{'${globalMenu}'.split(';')}")
    private List<String> menuItems;

    @Value("#{'${sessionMenu}'.split(';')}")
    private List<String> sessionMenu;

    @Bean(destroyMethod = "destroy")
    public GlobalController globalController() {
        return new GlobalController(menuItems);
    }

    @Bean(destroyMethod = "destroy")
    public SessionController sessionController() {
        return new SessionControllerImp(sessionMenu);
    }

    @Bean
    public TableBuilderUtil tableBuilderUtil() {
        return new TableBuilderUtil();
    }





}