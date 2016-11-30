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

@WebServlet("/followersList/")
public class FollowersServlet extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final String FOLLOWERS = "followers";
    private static final String PAGE_ID = "userID";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = SqlSubscribeDao.getInstance();
        HttpSession session = req.getSession();
        Collection<Integer> followers;

        if (req.getParameter("userID") != null) {
            int pageId = Integer.parseInt(req.getParameter(PAGE_ID));
            followers = subscriptionDao.getFollowers(pageId);

        } else {
            int userId = (int) session.getAttribute(USER_ID);
            followers = subscriptionDao.getFollowers(userId);
        }
        req.setAttribute(FOLLOWERS, followers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page/followersList.jsp");
        requestDispatcher.forward(req, resp);
    }
}