package bsuir.wt.lab4.servlet;

import bsuir.wt.lab4.entity.Room;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.service.RoomsService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.ServiceProvider;
import bsuir.wt.lab4.service.UsersService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = {"/home"})
public class HomeServlet extends HttpServlet {

    private static final String HOME_PAGE_JSP = "/WEB-INF/jsp/homePage.jsp";

    private static final String GET_ROOMS_ERROR = "error.get_rooms";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        RoomsService service = provider.getRoomsService();

        try {
            List<Room> rooms = service.getRooms();

            request.setAttribute("rooms", rooms);
            //request.getSession().setAttribute("rooms", rooms);
        } catch (ServiceException e) {
            request.setAttribute("error", GET_ROOMS_ERROR);
        }

        request.getRequestDispatcher(HOME_PAGE_JSP).forward(request, response);
    }
}
