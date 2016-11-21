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
import java.util.Collection;

@WebServlet("/subscribeList")
public class SubscriptionsServlet extends HttpServlet{
    private static final String USER_ID = "userId";
    private static final String SUBSCRIBES = "subscribes";
   // private static final String FOLLOWERS = "followers";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute(USER_ID);
        Collection<Integer> subIds = subscriptionDao.getSubIds(userId);
        Collection<Integer> followers = subscriptionDao.getFollowers(userId);

        req.setAttribute(SUBSCRIBES, subIds);
        //req.setAttribute(FOLLOWERS, followers);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page/subList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
