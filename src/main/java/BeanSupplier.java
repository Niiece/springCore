import controller.GlobalController;
import controller.SessionController;
import controller.SessionControllerImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import utils.TableBuilderUtil;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("mainMenu.properties")
@ComponentScan(value = {"aop", "controller.service.serviceImp", "dao.daoImp"})
public class BeanSupplier {

    @Value("#{'${globalMenu}'.split(';')}")
    private List<String> menuItems;

    @Value("#{'${sessionMenu}'.split(';')}")
    private List<String> sessionMenu;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("org.sqlite.JDBC");
        String path = System.getProperty("user.dir") + "/src/main/resources/coreDB";
        source.setUrl("jdbc:sqlite:" + path);

        return source;
    }

    @Bean
    public JdbcTemplate jdbcTemplate (DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

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
