package bsuir.wt.lab4.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.entity.User;
import bsuir.wt.lab4.service.UsersService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.ServiceProvider;

public class RegisterCommand implements Command {

	private static final String PARAMETER_PASSWORD = "password";
	private static final String PARAMETER_EMAIL = "email";

	private static final String REGISTRATION_PAGE_JSP = "/WEB-INF/jsp/registrationPage.jsp";
	private static final String REGISTRATION_PAGE = "/registration";

	private static final String ANY_ERROR = "error.registration";
	private static final String REG_SUCCESS = "message.registration_success";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter(PARAMETER_EMAIL);
		String password = request.getParameter(PARAMETER_PASSWORD);

		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("password", password);

		ServiceProvider provider = ServiceProvider.getInstance();
		UsersService service = provider.getClientService();

		User userData = new User();

		userData.setEmail(email);
		userData.setPassword(password);
		
		try {
			service.register(userData);
			request.setAttribute("message", REG_SUCCESS);
		} catch (ServiceException e) {
			request.setAttribute("error", ANY_ERROR);
		}

		request.getRequestDispatcher(REGISTRATION_PAGE_JSP).forward(request, response);
	}

}
