package controllers.users;

import dao.PostDao;
import dao.SubscriptionDao;
import social.Post;
import sql.SqlPostDao;
import sql.SqlSubscribeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/GetUserServlet/")
public class GetUserServlet extends HttpServlet {
    private static final String POSTS = "anotherPosts";
    private static final String PAGE_ID = "userID";
    private static final String SUB_BUTTON = "subButton";
    private static final String CURRENT_USER_ID = "userId";
    private static final String OTHER_FOLLOWERS = "otherFollowers";
    private static final String OTHER_SUBSCRIBES = "otherSubscribes";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();

        HttpSession session = request.getSession();

        int currentUserId = (int) session.getAttribute(CURRENT_USER_ID);
        int pageId = Integer.parseInt(request.getParameter("userHref"));

        if (currentUserId == pageId) {
            forward("/page/", request, response);
        } else {
            Collection<Integer> otherSubscribes = subscriptionDao.getSubIds(pageId);
            Collection<Integer> otherFollowers = subscriptionDao.getFollowers(pageId);

            Collection<Post> posts = postDao.getPostsByUserId(pageId);
            Collection<Integer> subscribes = subscriptionDao.getSubIds(currentUserId);

            if (subscribes.contains(pageId)) {
                request.setAttribute(SUB_BUTTON, true);
            } else {
                request.setAttribute(SUB_BUTTON, false);
            }

            request.setAttribute(POSTS, posts);
            request.setAttribute(PAGE_ID, pageId);
            request.setAttribute(OTHER_SUBSCRIBES, otherSubscribes);
            request.setAttribute(OTHER_FOLLOWERS, otherFollowers);

            forward("/page/otherUserPage.jsp", request, response);
        }

    }


    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
