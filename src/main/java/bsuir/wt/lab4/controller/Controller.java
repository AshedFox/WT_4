package bsuir.wt.lab4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.controller.command.CommandProvider;
import bsuir.wt.lab4.controller.command.CommandType;


@WebServlet(name = "Controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
	private static final String PARAMETER_COMMAND = "command";
	
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(PARAMETER_COMMAND);
		Command command = CommandProvider.getInstance().getCommand(commandName.toUpperCase());

		command.execute(request, response);
	}
}
