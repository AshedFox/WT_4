package bsuir.wt.lab4.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.service.UsersService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.ServiceProvider;

public class AuthorizeCommand implements Command {

	private static final String PARAMETER_EMAIL = "email";
	private static final String PARAMETER_PASSWORD = "password";

	private static final String AUTH_PAGE_JSP = "/WEB-INF/jsp/authPage.jsp";
	private static final String HOME_PAGE = "/home";

	private static final String ANY_ERROR = "error.auth";


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter(PARAMETER_EMAIL);
		String password = request.getParameter(PARAMETER_PASSWORD);

		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("password", password);

		ServiceProvider provider = ServiceProvider.getInstance();
		UsersService service = provider.getClientService();

		try {
			UserDTO user = service.authorize(email, password);
			
			if (user != null) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect(HOME_PAGE);
			} else {
				request.setAttribute("error", ANY_ERROR);
				request.getRequestDispatcher(AUTH_PAGE_JSP).forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute("error", ANY_ERROR);
			request.getRequestDispatcher(AUTH_PAGE_JSP).forward(request, response);
		}
	}
}
