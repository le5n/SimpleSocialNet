import exceptions.UserNotFoundException;
import social.User;
import sql.SqlUserDao;
import sql.UserNotFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlUserDao sqlUserDao = new SqlUserDao();

        String typedEmail = request.getParameter("email");
        String typedPassword = request.getParameter("password");

        try {
            User user = sqlUserDao.getUserByEmail(typedEmail);
            if (user.getPassword().equals(typedPassword)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("email", typedEmail);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/userPage.html");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.html");
                dispatcher.forward(request, response);

            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.html");
            dispatcher.forward(request, response);
        }
    }


}

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession userSession = request.getSession();
//    }

