package controllers.posts;

import dao.PostDao;
import dao.SubscriptionDao;
import social.Post;
import sql.SqlPostDao;
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

@WebServlet(urlPatterns = {"/page/", "/"})
public class UserPageServlet extends HttpServlet {

    private static final String POSTS = "posts";
    private static final String USER_ID = "userId";
    private static final String SUB_POSTS = "subPosts";
    private static final String SUBSCRIBES = "subscribes";
    private static final String FOLLOWERS = "followers";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDao postDao = SqlPostDao.getInstance();
        SubscriptionDao subscriptionDao = SqlSubscribeDao.getInstance();

        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute(USER_ID);
        Collection<Post> posts = postDao.getPostsByUserId(userId);
        Collection<Post> subPosts = subscriptionDao.getSubPosts(userId);
        Collection<Integer> subIds = subscriptionDao.getSubIds(userId);
        Collection<Integer> followers = subscriptionDao.getFollowers(userId);

        req.setAttribute(POSTS, posts);
        req.setAttribute(SUB_POSTS, subPosts);
        req.setAttribute(SUBSCRIBES, subIds);
        req.setAttribute(FOLLOWERS, followers);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page/userPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
