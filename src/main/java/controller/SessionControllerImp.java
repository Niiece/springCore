package controller;

import controller.service.EventService;
import controller.service.LocationService;
import controller.service.TicketService;
import controller.service.UserService;
import model.*;
import org.apache.commons.lang3.StringUtils;
import utils.TableBuilderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SessionControllerImp implements SessionController {
    private static final List<String> menuItems = Arrays.asList(
            "1. add event*",
            "2. view events",
            "3. remove event*",
            "4. purchase a ticket",
            "5. view my tickets",
            "6. view purchased tickets*",
            "",
            "0. to log out",
            "*for admins only",
            ""
    );

    private TableBuilderUtil tableBuilderUtil;

    private TicketService ticketService;
    private UserService userService;
    private EventService eventService;
    private User user;
    private BufferedReader in;
    private Scanner scanner;
    private LocationService locationService;

    public SessionControllerImp(UserService userService, TableBuilderUtil tableBuilderUtil, TicketService ticketService, EventService eventService, LocationService locationService) {
        this.tableBuilderUtil = tableBuilderUtil;
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.userService = userService;
        this.locationService = locationService;
        in = new BufferedReader(new InputStreamReader(System.in));
        scanner = new Scanner(System.in);
        this.ticketService.getById(0).setUser(this.userService.getById(1));
    }


    public void login(String userName, String password) {
        User user = userService.getByLogin(userName);

        if (null != user && StringUtils.equals(user.getPassword(), password)) {
            this.user = user;
            startSession(user);
        } else {
            System.out.println("wrong credentials");
        }


    }

    public void destroy() throws IOException {
        if (null != in) {
            in.close();
        } else if (null != scanner) {
            scanner.close();
        }
    }

    public void startSession(User user) {

        System.out.println("success");

        String a = StringUtils.EMPTY;

        while (!a.contains("0")) {
            showMenu(menuItems, user);
            try {
                a = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(a);
            System.out.println("\r\n~~~~~~~~~~~~~~~~~~");

            switch (a) {
                case "1":
                    System.out.println("enter event name, date(yyyy-MM-dd HH:mm), location(Holl: A/B) and ticket price");
                    if (user.getUserType() == UserType.ADMIN) {
                        try {
                            String eventName = in.readLine();
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime localDateTime = LocalDateTime.parse(in.readLine(), timeFormatter);
                            Location location = locationService.getLocationByName(in.readLine());
                            if (location == null) {
                                System.out.println("Wrong location");
                                break;
                            }
                            double price = Double.parseDouble(in.readLine());

                            Event event = new Event(eventName, localDateTime);
                            eventService.save(event);
                            ticketService.createTicketsForNewEvent(event, location, price);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("wrong permissions. Login as admin");
                    }
                    break;
                case "2":
                    tableBuilderUtil.buildEventTable(eventService.getAll());
                    promptEnterKey();
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
                case "4":
                    try {
                        tableBuilderUtil.buildEventTable(eventService.getAll());
                        System.out.println("select event:");
                        String eventName = in.readLine();
                        System.out.println("select event date(2007-12-03T10:15:30)");
                        LocalDateTime localDateTime = LocalDateTime.parse(in.readLine());
                        Event event = new Event(eventName, localDateTime);
                        tableBuilderUtil.ticketTableBuilder(
                                ticketService.getAvailableTickets()
                                        .stream()
                                        .filter(e -> e.getEvent().equals(event))
                                        .collect(Collectors.toList()));
                        System.out.println("select seat:");
                            long seat = Long.parseLong(in.readLine());
                        Ticket ticket = ticketService.getAvailableTickets()
                                .stream().filter(e -> e.getSeat() == seat)
                                .filter(e -> e.getEvent().equals(event)).findFirst().orElse(null);
                        if (null == ticket) {
                            System.out.println("can not find a ticket");
                            break;
                        }
                        ticket.setUser(this.user);
                        System.out.println("ticket has been purchased");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DateTimeParseException e) {
                        System.out.println("wrong date");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("wrong seat number");
                        break;
                    }
                    break;
                case "5":
                    tableBuilderUtil.ticketTableBuilder(this.user.getTickets());
                    break;
                case "6":
                    if (user.getUserType() == UserType.ADMIN) {
                        tableBuilderUtil.ticketTableBuilder(ticketService.getPurchasedTickets());
                        break;
                    }else {
                        System.out.println("wrong permissions. Login as admin");
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void showMenu(List<String> list, User user) {
        tableBuilderUtil.menuTableBuilder(list, user);
    }

    private void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        scanner = new Scanner(System.in);
        scanner.nextLine();
    }


}
