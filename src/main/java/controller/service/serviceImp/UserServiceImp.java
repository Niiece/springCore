package controller.service.serviceImp;

import controller.service.UserService;
import dao.UserDao;
import model.User;

import java.util.Collection;

public class UserServiceImp implements UserService {

    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByLogin(String login) {
        return this.userDao.getByLogin(login);
    }

    @Override
    public void save(String userName, String password) {
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
        return this.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return this.userDao.getAll();
    }
}
