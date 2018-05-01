package controller;

import controller.service.TicketService;
import controller.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import utils.TableBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GlobalController implements BaseController {

    private static final List<String> menuItems = Arrays.asList(
            "1. login",
            "2. view events",
            "3. register",
            "",
            "0. to exit",
            "**please login to buy a ticket"
    );

    private TableBuilder tableBuilder;
    private TicketService ticketService;
    private BufferedReader in;
    private SessionController sessionController;
    private UserService userService;

    public GlobalController(TableBuilder tableBuilder, TicketService ticketService, SessionController sessionController, UserService userService) {
        this.tableBuilder = tableBuilder;
        this.ticketService = ticketService;
        this.sessionController = sessionController;
        this.userService = userService;
        in = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void showMenu() {
        tableBuilder.menuTableBuilder(menuItems);
    }


    public void makeTheChoise() {
        String a = StringUtils.EMPTY;

        while (!a.contains("0")) {
            showMenu();
            try {
                a = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(a);
            System.out.println("\r\n~~~~~~~~~~~~~~~~~~");

            switch (a) {
                case "1":
                    try {
                        sessionController.login(in.readLine(), in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    showAvailableEvents();
                    break;
                case "3":

                    try {
                        System.out.println("enter login: ");
                        String userName = in.readLine();
                        System.out.println("enter password: ");
                        String password = in.readLine();
                        userService.save(userName, password);
                        System.out.println("user created successfully");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "0":
                    System.out.println("Hope to see you soon. Bye");
                    break;
                default:
                    System.out.println("Wrong choice. Please repeat");
                    break;
            }
        }

    }

    private void destroy() throws IOException {
        if (in != null) {
            in.close();
        }
    }

    @Override
    public void showAvailableEvents() {
        tableBuilder.ticketTableBuilder(ticketService.getAll());
    }
}
