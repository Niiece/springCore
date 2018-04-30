package controller;

import controller.service.TicketService;
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
            "**please login to buy a ticket"
    );

    private TableBuilder tableBuilder;
    private TicketService ticketService;
    private BufferedReader in;

    public GlobalController(TableBuilder tableBuilder, TicketService ticketService) {
        this.tableBuilder = tableBuilder;
        this.ticketService = ticketService;
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void showMenu() {
        tableBuilder.menuTableBuilder(menuItems);
    }


    public void makeTheChoise() {
        String a = StringUtils.EMPTY;

        while (!a.contains("exit")) {
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
                    break;
                case "2":
                    showAvailableTickets();
                    break;
                default:
                    break;
            }
        }

    }


    @Override
    public void showAvailableTickets() {
        tableBuilder.ticketTableBuilder(ticketService.getAll());
    }
}
