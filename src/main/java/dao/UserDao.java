package dao;

import model.AbstractDomainService;
import model.User;
import org.springframework.lang.NonNull;

public interface UserDao extends AbstractDomainService<User> {
    public User getByLogin(@NonNull String login);
    public void save(@NonNull String login, @NonNull String password);
}
