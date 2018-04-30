package utils;

import model.Ticket;

import java.util.Collection;
import java.util.List;

public class TableBuilder {


    public void menuTableBuilder(List<String> menuItems) {
        System.out.println("+--------------------------------------------+");
        System.out.printf("%s%20s%25s\r\n", "|","MENU","|");
        System.out.println("+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
        String formatOutput = "|  %-40s  |\r\n";
        menuItems.forEach(item -> System.out.printf(formatOutput, item));
        System.out.println("+--------------------------------------------+");
    }

    public void ticketTableBuilder(Collection<Ticket> tickets) {
        String formatOutput = "|  %-20s  |  %-12s  |  %-15s  |  %-2s  |  %-3s  |\r\n";
        tickets.forEach(ticket -> System.out.printf(formatOutput, ticket.getEvent().getName(), ticket.getLocation().getLocationName(), ticket.getDateTime(), ticket.getSeat(), ticket.getPrice()));
        System.out.println();
        System.out.println();

    }
}
