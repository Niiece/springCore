package dao.daoImp;


import dao.EventDao;
import dao.LocationDao;
import dao.TicketDao;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository(value = "ticketDao")
public class TicketDaoImp implements TicketDao {


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private EventDao eventDao;
    @Autowired
    private LocationDao locationDao;

    @Override
    public void save(Ticket obj) {

        String sql = "INSERT INTO tickets (seat, price, event_name, location_name) " +
                " VALUES (:seat, :price, :event_name, :location_name)";
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("seat", obj.getSeat());
        ps.addValue("price", obj.getPrice());
        ps.addValue("event_name", obj.getEvent().getName());
        ps.addValue("location_name", obj.getLocation().getLocationName());

        jdbcTemplate.update(sql, ps);
//        this.tickets.add(obj);
    }

    @Override
    public void remove(Ticket obj) {
//        tickets.remove(obj);
    }

    @Override
    public Ticket getById(int id) {
        String sql = SQL + "WHERE id = :tickets.id";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("tickets.id", id), new TicketMapper());
    }

    @Override
    public Collection<Ticket> getAll() {
        return jdbcTemplate.query(SQL, new TicketMapper());
    }

    private static final String SQL = "SELECT tickets.id,  tickets.seat, tickets.price," +
            " events.id AS 'events_id', events.event_date AS 'events_event_date', events.event_name AS 'events_event_name'," +
            " users.id AS 'users_id', users.login AS 'users_login', users.password AS 'users_password', users.type AS 'users_type'," +
            " locations.id AS 'locations_id', locations.location_name AS 'locations_location_name', locations.seats AS 'locations_location_seats' " +
            " FROM tickets" +
            " LEFT JOIN events ON tickets.event_name = events.event_name" +
            " LEFT JOIN users ON tickets.user_login = login " +
            " LEFT JOIN locations ON tickets.location_name = locations.location_name ";

    @Override
    public List<Ticket> getAllUserTickets(User user) {
        String query = SQL + "WHERE users.login = :login";
        return jdbcTemplate.query(query, new MapSqlParameterSource("login", user.getLogin()), new TicketMapper());
    }

    @Override
    public void setUserToTicket(Ticket ticket, User user) {

        String sql = "UPDATE tickets SET user_login=:login WHERE id=:id";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", ticket.getId());
        source.addValue("login", user.getLogin());
        jdbcTemplate.update(sql, source);
    }

    private static final class TicketMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int i) throws SQLException {

            UserType userType;

            try {
                userType = UserType.valueOf(rs.getString("users_type"));
            } catch (NullPointerException e) {
                userType = null;
//                userType = UserType.BASIC;
            }

            User user = new User(
                    rs.getString("users_login"),
                    rs.getString("users_password"),
                    userType
            );
            user.setId(rs.getLong("users_id"));
            Event event = new Event(
                    rs.getString("events_event_name"),
                    rs.getTimestamp("events_event_date").toLocalDateTime()
            );
            event.setId(rs.getLong("events_id"));



            Location location = new Location(
                    rs.getString("locations_location_name"),
                    rs.getLong("locations_location_seats")
            );
            location.setId(rs.getLong("locations_id"));
            Ticket ticket = new Ticket(
                    event,
                    location,
                    rs.getDouble("price"),
                    rs.getLong("seat")
            );

            ticket.setId(rs.getLong("id"));
            if (user.getLogin() != null) {
                ticket.setUser(user);
            }


            return ticket;
        }
    }


}
