package trash;

import controller.service.EventService;
import org.apache.commons.lang3.StringUtils;

public class GlobalLoginService implements LoginService {

    private EventService eventService;

    public GlobalLoginService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void login(String login, String password) {
        if (eventService.getAll().stream()
                .anyMatch(e -> StringUtils.equals(e.getLogin(), login) && StringUtils.equals(e.getPassword(), password))
                ) {
            System.out.println("logged in");
        } else {
            System.out.println("login or password is not correct");
        }
    }
}
