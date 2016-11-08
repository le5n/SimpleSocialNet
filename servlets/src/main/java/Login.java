
import exceptions.UserNotFoundException;
import security.StringEncryptUtil;
import social.User;
import sql.SqlUserDao;

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
    private static final String KEY = "key";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlUserDao sqlUserDao = new SqlUserDao();
        HttpSession session;

        String typedEmail = request.getParameter("email");
        String typedPassword = request.getParameter("password");
        String hash = StringEncryptUtil.encrypt(typedPassword);

        try {
            User user = sqlUserDao.getUserByEmail(typedEmail);
            if (user.getPassword().equals(hash)) {

                session = request.getSession(true);
                session.setAttribute(KEY,new Object());
                System.out.println("session load");


                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/page/userPage.html");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getin/error.html");
                dispatcher.forward(request, response);

            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getin/error.html");
            dispatcher.forward(request, response);
        }
    }


}

