import dao.UserDao;
import sql.SqlUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetAllUsers")
public class AllUsersServlet extends HttpServlet {
    private static final String USERS = "users";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new SqlUserDao();

        request.setAttribute(USERS, userDao.getAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/allUsers.jsp");
        requestDispatcher.forward(request, response);
    }
}
