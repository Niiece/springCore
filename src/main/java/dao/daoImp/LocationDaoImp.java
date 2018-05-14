package dao.daoImp;

import dao.LocationDao;
import model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LocationDaoImp implements LocationDao {

    private List<Location> locations = new ArrayList<>(Arrays.asList(
            new Location("Holl: A", 20L),
            new Location("Holl: B", 20L)
    ));

    @Override
    public void save(Location obj) {
        locations.add(obj);
    }

    @Override
    public void remove(Location obj) {
        locations.remove(obj);
    }

    @Override
    public Location getById(int id) {
        return locations.get(id);
    }

    public Location getLocation(int id) {
        return this.locations.get(id);
    }

    @Override
    public Collection<Location> getAll() {
        return locations;
    }
}
