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

@WebServlet("/SubscribeServlet/")
public class SubscribeServlet extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final String PAGE_ID = "userID";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();

        HttpSession session = request.getSession();
        String isSubscribed = request.getParameter("subButton");
        System.out.println(isSubscribed);
        int userId = (int) session.getAttribute(USER_ID);
        System.out.println(userId);
        int pageId = Integer.parseInt(request.getParameter(PAGE_ID));
        System.out.println(pageId+"this is current user id");



        if (isSubscribed.equals("subscribe")) {
            subscriptionDao.addSubscription(userId,pageId);
        } else {
            subscriptionDao.unsubscribe(userId, pageId);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/GetUserServlet/?userHref=" + pageId);
        requestDispatcher.forward(request, response);
    }
}
