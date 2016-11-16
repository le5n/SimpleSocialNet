package controllers.users;

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

@WebServlet("/GetUserServlet/")
public class GetUserServlet extends HttpServlet {
    private static final String POSTS = "anotherPosts";
    private static final String USER_ID = "userID";
    private static final String SUB_BUTTON = "subButton";
    private static final String CURRENT_USER_ID = "userId";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();
        HttpSession session = request.getSession();
        int currentUserId = (int) session.getAttribute("userId");

        int userId = Integer.parseInt(request.getParameter("userHref"));

        Collection<Post> posts = postDao.getPostsByUserId(userId);

        Collection<Integer> subscribes = subscriptionDao.getSubIds(currentUserId);

        if(subscribes.contains(userId)){
            request.setAttribute(SUB_BUTTON, true);
        }
        else {
            request.setAttribute(SUB_BUTTON, false);
        }


        request.setAttribute(POSTS, posts);
        request.setAttribute(USER_ID,userId);



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/otherUserPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
