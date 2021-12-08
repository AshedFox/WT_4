package bsuir.wt.lab4.controller.command;

import java.util.EnumMap;
import java.util.Map;

import bsuir.wt.lab4.controller.command.impl.*;

public class CommandProvider {
	private static final Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);

	private static final CommandProvider instance = new CommandProvider();

	private CommandProvider() {
		commands.put(CommandType.AUTHORIZE, new AuthorizeCommand());
		commands.put(CommandType.REGISTER, new RegisterCommand());
		commands.put(CommandType.LOGOUT, new LogoutCommand());
		commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandType.BOOK_ROOM, new BookRoomCommand());
	}

	public static CommandProvider getInstance() {
		return instance;
	}
	
	public Command getCommand(String commandName) {
		return commands.get(CommandType.valueOf(commandName));
	}
}
