package dao.daoImp;

import dao.LocationDao;
import model.Location;
import org.springframework.beans.factory.annotation.Autowired;
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

@Repository
public class LocationDaoImp implements LocationDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void save(Location obj) {
        String sql = "INSERT INTO locations (location_name, seats) VALUES (:location, :seats)";
        jdbcTemplate.update(sql, getSource(obj));
    }

    @Override
    public void remove(Location obj) {
        jdbcTemplate.update("DELETE FROM locations WHERE location_name = :location", getSource(obj));
    }

    @Override
    public Location getById(int id) {
        String sql = "SELECT id, location_name, seats FROM locations WHERE id = :id";
        MapSqlParameterSource source = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, source, new LocationMapper());
    }

    @Override
    public Collection<Location> getAll() {
        String sql = "SELECT id, location_name, seats FROM locations";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location(
                    rs.getString("location_name"),
                    rs.getLong("seats")
            );
            location.setId(rs.getLong("id"));

            return location;
        }
    }

    private MapSqlParameterSource getSource(Location location) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", location.getId());
        source.addValue("location", location.getLocationName());
        source.addValue("seats", location.getSeats());

        return source;

    }
}
