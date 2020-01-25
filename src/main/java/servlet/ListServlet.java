package servlet;

import model.BankAccount;
import controller.Kernel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Kernel kernel = new Kernel();
        ArrayList<BankAccount> bankAccounts = kernel.getListBankAccount();
        kernel.close();
        req.setAttribute("list",bankAccounts);
        getServletContext().getRequestDispatcher("/secure/list.jsp").forward(req,resp);
    }
}
