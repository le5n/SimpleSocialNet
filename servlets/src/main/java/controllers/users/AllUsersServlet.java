package controllers.users;

import dao.UserDao;
import social.User;
import sql.SqlUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/GetAllUsers")
public class AllUsersServlet extends HttpServlet {
    private static final String USERS = "users";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = SqlUserDao.getInstance();

        Collection<User> users = userDao.getAll();

        request.setAttribute(USERS, users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/allUsers.jsp");
        requestDispatcher.forward(request, response);
    }
}
