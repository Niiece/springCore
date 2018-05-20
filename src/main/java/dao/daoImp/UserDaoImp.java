package dao.daoImp;

import dao.UserDao;
import model.User;
import model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component(value = "userDao")
public class UserDaoImp implements UserDao {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void save(User obj) {
        db.update("INSERT INTO users (login, password) VALUES (?,?)",
                obj.getLogin(), obj.getPassword());
    }

    @Override
    public void remove(User obj) {
        db.update("DELETE FROM users WHERE id = ?",
                obj.getId());
    }

    @Override
    public User getById(int id) {
        return db.queryForObject("SELECT id, login, password, type FROM users WHERE id = ?",
                new Object[]{id}, new UserMapper());
    }

    @Override
    public Collection<User> getAll() {
        return db.query("SELECT id, login, password, type FROM users", new UserMapper());
    }

    @Override
    @NonNull
    public User getByLogin(String login) {
        return db.queryForObject("SELECT id, login, password, type FROM users WHERE login = ?",
                new Object[]{login}, new UserMapper());
    }

    @Override
    public void save(String login, String password) {
        User user = new User(login, password, UserType.BASIC);
        save(user);
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    UserType.valueOf(resultSet.getString("type")));
            user.setId(Long.valueOf(resultSet.getString("id")));

            return user;
        }
    }

}




