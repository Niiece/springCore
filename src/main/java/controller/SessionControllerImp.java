package controller;

import controller.service.EventService;
import controller.service.TicketService;
import controller.service.UserService;
import model.Event;
import model.User;
import model.UserType;
import org.apache.commons.lang3.StringUtils;
import utils.TableBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SessionControllerImp implements SessionController {
    private static final List<String> menuItems = Arrays.asList(
            "1. add event",
            "2. view events",
            "3. remove event",
            "0. to log out",
            ""
    );

    public SessionControllerImp(UserService userService, TableBuilder tableBuilder, TicketService ticketService, EventService eventService) {
        this.tableBuilder = tableBuilder;
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.userService = userService;
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private TableBuilder tableBuilder;
    private TicketService ticketService;
    private UserService userService;
    private EventService eventService;
    private User user;

    private BufferedReader in;

    public void login(String userName, String password) {
        User user = userService.getByLogin(userName);

        if (StringUtils.equals(user.getPassword(), password)) {
            this.user = user;
            startSession(user);
        } else {
            System.out.println("wrong credentials");
        }


    }

    public void startSession(User user) {
        System.out.println("success");

        String a = StringUtils.EMPTY;

        while (!a.contains("0")) {
            showMenu(menuItems);
            try {
                a = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(a);
            System.out.println("\r\n~~~~~~~~~~~~~~~~~~");

            switch (a) {
                case "1":
                    System.out.println("enter event name and date(yyyy-MM-dd HH:mm)");
                    if (user.getUserType() == UserType.ADMIN) {
                        try {
                            eventService.save(in.readLine(), in.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("wrong permissions. Login as admin");
                    }
                    break;
                case "2":
                    eventService.getAll().stream().map(Event::getName).forEach(System.out::println);
                    break;
                case "3":
                    if (user.getUserType() == UserType.ADMIN) {
                        try {
                            eventService.remove(in.readLine());
                            System.out.println("event has been removed");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("wrong permissions. Login as admin");
                    }
                    break;
                default:
                    break;
            }
        }
        //void showMenu() -> make the choice

        //select option in console

        //void removeEvent(String eventName); -> all tickets for this event must be removed
        //void viewEvents()
        //void viewTickets(String eventName)

        //void buyTicket(Event event, int/float place)
    }


    private void showMenu(List<String> list) {
        tableBuilder.menuTableBuilder(list);
    }


}
