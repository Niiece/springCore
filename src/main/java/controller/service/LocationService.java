package controller.service;

import model.AbstractDomainService;
import model.Location;
import org.springframework.lang.NonNull;

public interface LocationService extends AbstractDomainService<Location>{
    public Location getLocationByName(@NonNull String name);
}
