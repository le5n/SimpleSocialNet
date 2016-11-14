package controllers.users;

import dao.PostDao;
import social.Post;
import sql.SqlPostDao;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();

        int userId = Integer.parseInt(request.getParameter("userHref"));

        Collection<Post> posts = postDao.getPostsByUserId(userId);

        request.setAttribute(POSTS, posts);
        request.setAttribute(USER_ID,userId);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/otherUserPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
