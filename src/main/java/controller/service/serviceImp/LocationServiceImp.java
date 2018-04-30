package controller.service.serviceImp;

import controller.service.LocationService;
import dao.LocationDao;
import model.Location;

import java.util.Collection;

public class LocationServiceImp implements LocationService {

    private LocationDao locationDao;

    public LocationServiceImp(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

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
}
