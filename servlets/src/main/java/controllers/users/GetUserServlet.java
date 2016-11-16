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
    private static final String SUB_BUTTON = "subButton";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDao postDao = new SqlPostDao();

        int userId = Integer.parseInt(request.getParameter("userHref"));
        System.out.println("getUserServlet can type smt woah");
        Collection<Post> posts = postDao.getPostsByUserId(userId);
        if(request.getParameter("sub").equals("true")){
            request.setAttribute(SUB_BUTTON, true);
            System.out.println("sub is not null");
        }
        else{
            request.setAttribute(SUB_BUTTON, false);
            System.out.println("sub is null");
        }

        request.setAttribute(POSTS, posts);
        request.setAttribute(USER_ID,userId);



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/otherUserPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
