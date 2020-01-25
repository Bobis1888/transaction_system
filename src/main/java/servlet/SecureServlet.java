package servlet;

import controller.Kernel;
import model.Client;
import security.Encrypt;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/secureServlet")
public class SecureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        Client client = null;
        Kernel kernel = new Kernel();
        ServletContext servletContext = req.getServletContext();
        client = kernel.checkClient(userName,password);
        kernel.close();
        //если в базе есть клиент с таким userName и password
        //save to session
        //задаем ключ авторизации полсе будем проверять его в security.filter.SecurityFilter
        //ключ = шифр Client.id в security.Encrypt
        if (client!=null) {
            String keyAuth = Encrypt.encrypt(client.getId());
            client.setKeyAuth(keyAuth);
            session.setAttribute("client", client);
            servletContext.getRequestDispatcher("/secure/homePage.jsp").forward(req,resp);
        }else {
            servletContext.getRequestDispatcher("/error.jsp").forward(req,resp);
        }
    }
}
