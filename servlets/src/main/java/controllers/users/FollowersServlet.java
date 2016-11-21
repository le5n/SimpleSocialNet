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

@WebServlet("/followersList")
public class FollowersServlet extends HttpServlet{
    private static final String USER_ID = "userId";
    private static final String SUBSCRIBES = "subscribes";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute(USER_ID);
        Collection<Integer> subIds = subscriptionDao.getSubIds(userId);

        req.setAttribute(SUBSCRIBES, subIds);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page/followersList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
