package controller.service.serviceImp;

import aop.Discount;
import controller.service.UserService;
import dao.UserDao;
import model.User;
import model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getByLogin(String login) {
        return this.userDao.getByLogin(login);
    }

    @Override
    public void save(String userName, String password) throws UncategorizedSQLException {
        this.userDao.save(userName, password);
    }

    @Override
    public void save(User obj) {
        this.userDao.save(obj);
    }

    @Override
    public void remove(User obj) {
        this.userDao.remove(obj);
    }

    @Override
    public User getById(int id) {
        return this.userDao.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return this.userDao.getAll();
    }

    @Override
    @Discount
    public void updateUserType(User user) {
        if (user.getUserType().equals(UserType.BASIC)
                && user.getTickets().size() >= 4) {
            user.setUserType(UserType.PREMIUM);
        }
    }
}
