package bsuir.wt.lab4.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsuir.wt.lab4.controller.command.Command;

public class ChangeLocaleCommand implements Command {

	private static final String PARAMETER_LOCALE = "locale";

	private static final String CHANGE_LOCALE_ERROR = "change_locale.error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String locale = request.getParameter(PARAMETER_LOCALE);

		if (locale != null) {
			request.getSession().setAttribute("locale", locale);
			response.addCookie(new Cookie("locale", locale));
		}
		else {
			request.getSession().setAttribute("error", CHANGE_LOCALE_ERROR);
		}

		request.getRequestDispatcher(request.getContextPath()).forward(request, response);
	}

}
