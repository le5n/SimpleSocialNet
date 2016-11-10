import dao.PostDao;
import social.Post;
import sql.SqlPostDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/catalog/")
public class Catalog extends HttpServlet {

    private static final String POSTS = "posts";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();
        Collection<Post> posts = postDao.getAll();
        req.setAttribute(POSTS, posts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/catalog/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
