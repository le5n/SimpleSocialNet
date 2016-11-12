import dao.PostDao;
import dao.UserDao;
import sql.SqlPostDao;
import sql.SqlUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddPostServlet")

public class AddPostServlet extends HttpServlet {
    private static final String USER_ID = "userId";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();

        String postText = request.getParameter("newPost");
        System.out.println(postText);
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute(USER_ID);
        System.out.println(userId);
        postDao.addPost(postText,userId);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/");
        dispatcher.forward(request, response);

    }
}
