package bsuir.wt.lab4.controller.command.impl;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.service.BookingService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    private static final String HOME_PAGE = "/home";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", null);
        request.getSession().setAttribute("user", null);
        request.getSession().invalidate();

        response.sendRedirect(HOME_PAGE);
    }
}
