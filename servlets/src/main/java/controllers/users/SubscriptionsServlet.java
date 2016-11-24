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
import java.util.LinkedList;

@WebServlet("/subscribeList/")
public class SubscriptionsServlet extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final String SUBSCRIBES = "subscribes";
    private static final String PAGE_ID = "userID";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute(USER_ID);

        Collection<Integer> subIds;
        int pageId = Integer.parseInt(req.getParameter(PAGE_ID));

        if(req.getParameter(PAGE_ID)!=null) {
            subIds = subscriptionDao.getSubIds(pageId);

        }
        else {
            subIds = subscriptionDao.getSubIds(userId);
        }

        req.setAttribute(SUBSCRIBES, subIds);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page/subList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
