package controllers.users;

import dao.SubscriptionDao;
import sql.SqlSubscribeDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
    private static final String USER_ID = "userId";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        int pageId = Integer.parseInt(request.getParameter("button"));

        SubscriptionDao subscriptionDao = new SqlSubscribeDao();

        subscriptionDao.unsubscribe(userId, pageId);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/GetUserServlet/?userHref=" + pageId);
        requestDispatcher.forward(request, response);
    }
}
