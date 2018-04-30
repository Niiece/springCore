package controller.service;

import model.AbstractDomainService;
import model.User;
import org.springframework.lang.NonNull;

public interface UserService extends AbstractDomainService<User> {
    public User getByLogin(@NonNull String login);
}
