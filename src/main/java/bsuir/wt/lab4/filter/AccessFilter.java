package bsuir.wt.lab4.filter;

import bsuir.wt.lab4.controller.command.CommandType;
import bsuir.wt.lab4.entity.Role;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.utils.AccessManager;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = "*")
public class AccessFilter implements Filter {
    private static final String COMMAND_PARAMETER = "command";

    private static final String CONTROLLER = "/controller";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        String route = servletRequest.getRequestURI();

        UserDTO user = (UserDTO) servletRequest.getSession().getAttribute("user");
        Role role =  user != null ? user.getRole() : Role.NONE;

        if (!AccessManager.getInstance().isRouteAccessible(route, role)) {
            servletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (route.equals(CONTROLLER)) {
            String command = servletRequest.getParameter(COMMAND_PARAMETER);
            CommandType commandType;

            try {
                commandType = CommandType.valueOf(command.toUpperCase());
            } catch (Exception ignored) {
                servletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            if (!AccessManager.getInstance().isCommandAccessible(commandType, role)) {
                servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
