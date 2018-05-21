package model;

import java.util.ArrayList;
import java.util.List;

public class User extends DomainObject{
    private String login, password;
    private UserType userType;
    //todo change logic to get list of tickets from DB
    //todo add additional method to dao class
//    private static List<Ticket> tickets = new ArrayList<>();

    public User(String login, String password, UserType userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

//    public List<Ticket> getTickets() {
//        return tickets;
//    }

//    public void addTicket(Ticket ticket) {
//        this.tickets.add(ticket);
//    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
//                ", tickets=" + tickets +
                '}';
    }
}
