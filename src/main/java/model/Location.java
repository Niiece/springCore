package model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Location extends DomainObject {

    private String locationName;
    private long seats;

    public Location(String locationName, long seats) {
        this.locationName = locationName;
        this.seats = seats;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long getSeats() {
        return seats;
    }

    public void setSeats(long seats) {
        this.seats = seats;
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, seats+1).boxed().collect(Collectors.toSet());
    }
}
