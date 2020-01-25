package security.filter;

import model.Client;
import security.Encrypt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
//проверка доступа
@WebFilter("/secure/*")
public class SecureFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Client client = (Client) request.getSession().getAttribute("client");
        if (Encrypt.validateAuth(client)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            servletRequest.getServletContext().getRequestDispatcher("/error.jsp").forward(servletRequest, servletResponse);
        }
    }
}
