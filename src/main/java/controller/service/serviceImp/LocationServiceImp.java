package controller.service.serviceImp;

import controller.service.LocationService;
import dao.LocationDao;
import model.Location;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public void save(Location obj) {
        this.locationDao.save(obj);
    }

    @Override
    public void remove(Location obj) {
        this.locationDao.remove(obj);
    }

    @Override
    public Location getById(int id) {
        return this.locationDao.getById(id);
    }

    @Override
    public Collection<Location> getAll() {
        return this.locationDao.getAll();
    }

    public Location getLocationByName(String name) {
        return locationDao.getAll().stream()
                .filter(location -> StringUtils.equalsIgnoreCase(location.getLocationName(), name))
                .findFirst()
                .orElse(null);
    }
}
