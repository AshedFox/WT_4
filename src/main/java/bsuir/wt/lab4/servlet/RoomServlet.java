package bsuir.wt.lab4.servlet;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.controller.command.CommandProvider;
import bsuir.wt.lab4.controller.command.CommandType;
import bsuir.wt.lab4.entity.Room;
import bsuir.wt.lab4.service.RoomsService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.ServiceProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RoomServlet", value = "/room")
public class RoomServlet extends HttpServlet {

    private static final String ROOM_PAGE_JSP = "/WEB-INF/jsp/roomPage.jsp";

    private static final String GET_ROOM_ERROR = "error.get_room";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");

        if (idString == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        int id = Integer.parseInt(idString);

        ServiceProvider provider = ServiceProvider.getInstance();
        RoomsService service = provider.getRoomsService();

        try {
            Room room = service.getRoom(id);
            request.setAttribute("room", room);
            //request.getSession().setAttribute("room", room);
            request.getRequestDispatcher(ROOM_PAGE_JSP).forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute("error", GET_ROOM_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().getCommand(CommandType.BOOK_ROOM.toString());
        command.execute(request, response);
    }
}
