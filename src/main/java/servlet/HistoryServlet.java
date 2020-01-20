package servlet;

import cntroller.Kernel;
import model.NodeHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Kernel kernel = new Kernel();
        ArrayList<NodeHistory> node = kernel.getHistory();
        req.setAttribute("node",node);
        getServletContext().getRequestDispatcher("/secure/log.jsp").forward(req,resp);
    }
}
