package bsuir.wt.lab4.controller.command.impl;

import bsuir.wt.lab4.controller.command.Command;
import bsuir.wt.lab4.entity.Room;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.service.BookingService;
import bsuir.wt.lab4.service.RoomsService;
import bsuir.wt.lab4.service.ServiceException;
import bsuir.wt.lab4.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookRoomCommand implements Command {
    private static final String ROOM_PAGE = "/room";
    private static final String ROOM_PAGE_JSP = "/WEB-INF/jsp/roomPage.jsp";

    private static final String BOOK_ROOM_ERROR = "error.book_room";
    private static final String NO_AUTH_ERROR = "error.no_auth";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        ServiceProvider provider = ServiceProvider.getInstance();
        BookingService service = provider.getBookingService();

        if (user != null) {
            try {
                service.BookRoom(user.getId(), roomId);
            } catch (ServiceException e) {
                request.setAttribute("error", BOOK_ROOM_ERROR);
            }
        }
        else {
            request.setAttribute("error", NO_AUTH_ERROR);
        }
        response.sendRedirect(ROOM_PAGE + "?id=" + roomId);
        //request.getRequestDispatcher(ROOM_PAGE_JSP).forward(request, response);
    }
}
