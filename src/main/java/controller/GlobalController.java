package controller;

import controller.service.EventService;
import controller.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import utils.TableBuilderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class GlobalController implements BaseController {

    private List<String> menuItems;
    private TableBuilderUtil tableBuilderUtil;
    private EventService eventService;
    private BufferedReader in;
    private Scanner scanner;
    private SessionController sessionController;
    private UserService userService;

    public GlobalController(TableBuilderUtil tableBuilderUtil,
                            EventService eventService,
                            SessionController sessionController,
                            UserService userService) {

        this.tableBuilderUtil = tableBuilderUtil;
        this.eventService = eventService;
        this.sessionController = sessionController;
        this.userService = userService;
        in = IOUtils.toBufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void showMenu() {
        tableBuilderUtil.menuTableBuilder(menuItems);
    }


    public void makeTheChoice() {
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
                        String login, password;
                        System.out.println("Enter login: ");
                        login = in.readLine();
                        System.out.println("Enter password: ");
                        password = in.readLine();
                        sessionController.login(login, password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    showAvailableEvents();
                    promptEnterKey();
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
        if (scanner != null) {
            scanner.close();
        }
    }

    public void showAvailableEvents() {
        tableBuilderUtil.buildEventTable(eventService.getAll());
    }


    private void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void setMenuItems(List<String> menuItems) {
        this.menuItems = menuItems;
    }
}
