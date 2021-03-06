package servlet;

import controller.Kernel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numberSender = req.getParameter("sender");
        String numberRecipient = req.getParameter("recipient");
        int sum = Integer.parseInt(req.getParameter("amount"));
        Kernel kernel = new Kernel();
        boolean transaction = kernel.transaction(numberSender,numberRecipient,sum);
        if (transaction){
            req.setAttribute("transactionBoolean", true);
        }
        kernel.close();
        getServletContext().getRequestDispatcher("/secure/homePage.jsp").forward(req,resp);
    }
}
