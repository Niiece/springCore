package dao.daoImp;

import dao.UserDao;
import model.User;
import model.UserType;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDaoImp implements UserDao {

    private List<User> users = Arrays.asList(
            new User("admin", "admin1", UserType.ADMIN),
            new User("simple", "simple1", UserType.BASIC)
    );

    @Override
    public void save(User obj) {
        users.add(obj);
    }

    @Override
    public void remove(User obj) {
        users.remove(obj);
    }

    @Override
    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return users;
    }

    @Override @NonNull
    public User getByLogin(String login) {
        return users.stream().filter(user -> user.getLogin().contains(login)).findFirst().orElse(null);
    }
}
