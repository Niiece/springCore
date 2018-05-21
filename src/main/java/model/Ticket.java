package model;

import java.time.LocalDateTime;

public class Ticket extends DomainObject{

    private Event event;
    private User user;
    private Location location;
    private LocalDateTime dateTime;
    private long seat;
    private double price;

    public Ticket(Event event, Location location, double price, long seat) {
        this.event = event;
        this.location = location;
        this.price = price;
        this.seat = seat;
        setDateTime(event.getDateTime());
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
//        user.addTicket(this);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "event=" + event.getName() +
                ", user=" + user +
                ", location=" + location.getLocationName() +
                ", dateTime=" + dateTime +
                ", seat=" + seat +
                ", price=" + price +
                '}';
    }
}
