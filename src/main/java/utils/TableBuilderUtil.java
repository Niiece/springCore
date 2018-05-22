package utils;

import model.Event;
import model.Ticket;
import model.User;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class TableBuilderUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");


    public void menuTableBuilder(List<String> menuItems, User user) {
        System.out.println("+--------------------------------------------+");
        String menu = (user == null)
                ? "MENU"
                : "MENU: " + user.getLogin() + "(" + user.getUserType() + ")";
        System.out.printf("%s%30s%15s\r\n", "|",menu,"|");
        System.out.println("+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
        String formatOutput = "|  %-40s  |\r\n";
        menuItems.forEach(item -> System.out.printf(formatOutput, item));
        System.out.println("+--------------------------------------------+");
    }

    public void menuTableBuilder(List<String> menuItems) {
        menuTableBuilder(menuItems, null);
    }

    public void ticketTableBuilder(Collection<Ticket> tickets) {
        String formatOutput = "|  %-20s  |  %-12s  |  %-15s  |  %-2s  |  %-3s  |\r\n";
        tickets.forEach(ticket -> System.out.printf(formatOutput, ticket.getEvent().getName(), ticket.getLocation().getLocationName(), ticket.getDateTime().format(DATE_TIME_FORMATTER), ticket.getSeat(), ticket.getPrice()));
        System.out.println();
        System.out.println();

    }

    public void buildEventTable(Collection<Event> events) {
        System.out.println("+-------------------------------------------+");
        System.out.printf("%s%20s%24s\r\n", "|","Events","|");
        System.out.println("+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
        String formatOutput = "| %-20s | %-7s |\r\n";
        events.forEach(event -> System.out.printf(formatOutput, event.getName(), event.getDateTime().format(DATE_TIME_FORMATTER)));
        System.out.println("+-------------------------------------------+");
    }

}
