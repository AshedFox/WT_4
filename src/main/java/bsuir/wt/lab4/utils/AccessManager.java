package bsuir.wt.lab4.utils;

import bsuir.wt.lab4.controller.command.CommandType;
import bsuir.wt.lab4.entity.Role;

import java.util.*;

public class AccessManager {
    private static final Map<Role, List<String>> routes = new EnumMap<>(Role.class);
    private static final Map<Role, List<CommandType>> commands = new EnumMap<>(Role.class);

    private static final String INDEX1_PAGE = "/";
    private static final String INDEX2_PAGE = "/index";
    private static final String HOME_PAGE = "/home";
    private static final String AUTH_PAGE = "/auth";
    private static final String REG_PAGE = "/registration";
    private static final String ROOM_PAGE = "/room";
    private static final String CONTROLLER = "/controller";

    private static final AccessManager instance = new AccessManager();

    private AccessManager() {
        routes.put(Role.NONE, Arrays.asList(INDEX1_PAGE,INDEX2_PAGE, HOME_PAGE, ROOM_PAGE,
                AUTH_PAGE, REG_PAGE, CONTROLLER));
        routes.put(Role.USER, Arrays.asList(INDEX1_PAGE,INDEX2_PAGE, HOME_PAGE, ROOM_PAGE, CONTROLLER));
        routes.put(Role.ADMIN, Arrays.asList(INDEX1_PAGE,INDEX2_PAGE, HOME_PAGE, ROOM_PAGE, CONTROLLER));

        commands.put(Role.NONE, Arrays.asList(CommandType.AUTHORIZE, CommandType.REGISTER,
                CommandType.CHANGE_LOCALE));
        commands.put(Role.USER, Arrays.asList(CommandType.LOGOUT, CommandType.BOOK_ROOM,
                CommandType.CHANGE_LOCALE));
        commands.put(Role.ADMIN, Arrays.asList(CommandType.LOGOUT, CommandType.BOOK_ROOM,
                CommandType.CHANGE_LOCALE));
    }

    public static AccessManager getInstance() {
        return instance;
    }

    public Map<Role, List<String>> getRoutes() {
        return routes;
    }

    public Map<Role, List<CommandType>> getCommands() {
        return commands;
    }

    public boolean isRouteAccessible(String route, Role role) {
        try {
            return routes.get(role).contains(route);
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean isCommandAccessible(CommandType commandType, Role role) {
        try {
            return commands.get(role).contains(commandType);
        } catch (Exception ignored) {
            return false;
        }
    }
}
