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
    private SubscriptionDao subscriptionDao = SqlSubscribeDao.getInstance();
    private static final String POSTS = "anotherPosts";
    private static final String PAGE_ID = "userID";
    private static final String SUB_BUTTON = "subButton";
    private static final String CURRENT_USER_ID = "userId";
    private static final String OTHER_FOLLOWERS = "otherFollowers";
    private static final String OTHER_SUBSCRIBES = "otherSubscribes";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int pageId = 0;
        int currentUserId = (int) session.getAttribute(CURRENT_USER_ID);
        try {
            pageId = Integer.parseInt(request.getParameter("userHref"));
        }
        catch (NumberFormatException e){
            forward("/page/", request,response);
        }
        String lang = (String) session.getAttribute("lang");

        if (currentUserId == pageId) {
            forward("/page/", request, response);
        } else {
            setPageInfo(pageId, request);
            Collection<Integer> subscribes = subscriptionDao.getSubIds(currentUserId);

            boolean isSubscribed = subscribes.contains(pageId);
            setSubButton(lang, isSubscribed, request);

            forward("/page/otherUserPage.jsp", request, response);
        }
    }
    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void setPageInfo(int pageId, HttpServletRequest request) {
        PostDao postDao = SqlPostDao.getInstance();

        Collection<Integer> otherSubscribes = subscriptionDao.getSubIds(pageId);
        Collection<Integer> otherFollowers = subscriptionDao.getFollowers(pageId);

        Collection<Post> posts = postDao.getPostsByUserId(pageId);

        request.setAttribute(POSTS, posts);
        request.setAttribute(PAGE_ID, pageId);
        request.setAttribute(OTHER_SUBSCRIBES, otherSubscribes);
        request.setAttribute(OTHER_FOLLOWERS, otherFollowers);
    }

    private void setSubButton(String lang, boolean isSubscribed, HttpServletRequest request) {
        if (isSubscribed) {
            if (lang.equals("en_US"))
                request.setAttribute(SUB_BUTTON, "unsubscribe");
            else
                request.setAttribute(SUB_BUTTON, "отписаться");
        } else {
            if (lang.equals("ru_RU"))
                request.setAttribute(SUB_BUTTON, "подписаться");
            else {
                request.setAttribute(SUB_BUTTON, "subscribe");
            }
        }
    }
}
