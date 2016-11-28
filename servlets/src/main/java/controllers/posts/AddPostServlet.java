package controllers.posts;

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
import java.io.PrintWriter;

@WebServlet("/AddPostServlet")

public class AddPostServlet extends HttpServlet {
    private static final String USER_ID = "userId";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = SqlPostDao.getInstance();

        String postText = request.getParameter("newPost");
        if(postText.length()>140){
            PrintWriter out = response.getWriter();
            out.write("Post is too long, press back arrow to go back to your page");
        } else {
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute(USER_ID);
            postDao.addPost(postText, userId);

            forward("/page/", request, response);
        }

    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
