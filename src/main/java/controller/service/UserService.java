package controller.service;

import aop.Discount;
import model.AbstractDomainService;
import model.User;
import org.springframework.lang.NonNull;

public interface UserService extends AbstractDomainService<User> {
    public User getByLogin(@NonNull String login);
    public void save(@NonNull String userName, @NonNull String password);

    @Discount
    public void updateUserType(@NonNull User user);
}
