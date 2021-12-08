package bsuir.wt.lab4.servlet;

import bsuir.wt.lab4.entity.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IndexServlet", value = {"/", "/index"}, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

    private static final String LOCALE = "locale";

    private static final String HOME_PAGE = "/home";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = "en";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies){
                if (cookie.getName().equalsIgnoreCase(LOCALE)) {
                    locale = cookie.getValue();
                }
            }
        }
        request.getSession().setAttribute(LOCALE, locale);
        response.addCookie(new Cookie("locale", locale));

        response.sendRedirect(HOME_PAGE);
    }
}
