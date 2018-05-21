package dao.daoImp;

import dao.EventDao;
import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Repository
public class EventDaoImp implements EventDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(String eventName, String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse(dateTime, format);
        Event event = new Event(eventName, formattedDate);
        save(event);
    }

    @Override
    public void remove(String eventName) {
        Event event = new Event(eventName, null);
        remove(event);
    }

    @Override
    public void save(Event obj) {
        String sql = "INSERT INTO events (event_name, event_date) VALUES (:event_name, :event_date)";
        jdbcTemplate.update(sql, getSource(obj));
    }

    @Override
    public void remove(Event obj) {
        String sql = "DELETE FROM events WHERE event_name = :event_name";
        jdbcTemplate.update(sql, getSource(obj));
    }

    @Override
    public Event getById(int id) {
        String sql = "SELECT id, event_name, event_date FROM events WHERE id = :id";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), new EventMapper());
    }

    @Override
    public Collection<Event> getAll() {
        String sql = "SELECT id, event_name, event_date FROM events";
        return jdbcTemplate.query(sql, new EventMapper());
    }

    @Override
    public Event getEventByName(String eventName) {
        String sql = "SELECT id, event_name, event_date FROM events WHERE event_name=:name";
        return jdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource("name", eventName),
                new EventMapper());
    }

    private static final class EventMapper implements RowMapper<Event> {

        @Override
        public Event mapRow(ResultSet resultSet, int i) throws SQLException {
            Event event = new Event(
                    resultSet.getString("event_name"),
                    resultSet.getTimestamp("event_date").toLocalDateTime()
            );
            event.setId(resultSet.getLong("id"));

            return event;
        }
    }

    private MapSqlParameterSource getSource(Event event) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", event.getId());
        source.addValue("event_name", event.getName());
        source.addValue("event_date", Timestamp.valueOf(event.getDateTime()));

        return source;
    }
}
