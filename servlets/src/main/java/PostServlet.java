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

@WebServlet("/page/")
public class PostServlet extends HttpServlet {

    private static final String POSTS = "posts";
    private static final String USER_ID = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();
        HttpSession session = req.getSession();

        int userId = (int)session.getAttribute(USER_ID);
        Collection<Post> posts = postDao.getPostsByUserId(userId);

        req.setAttribute(POSTS, posts);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/page/userPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
