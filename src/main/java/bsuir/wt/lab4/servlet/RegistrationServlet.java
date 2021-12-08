package bsuir.wt.lab4.servlet;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.controller.command.CommandProvider;
import bsuir.wt.lab4.controller.command.CommandType;
import bsuir.wt.lab4.controller.command.impl.RegisterCommand;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final String REG_PAGE_JSP = "/WEB-INF/jsp/registrationPage.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("email") == null) {
            request.getSession().setAttribute("email", "");
        }

        if (request.getSession().getAttribute("password") == null) {
            request.getSession().setAttribute("password", "");
        }

        request.getRequestDispatcher(REG_PAGE_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().getCommand(CommandType.REGISTER.toString());
        command.execute(request, response);
    }
}
