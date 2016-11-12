
import exceptions.UserNotFoundException;
import security.StringEncryptUtil;
import social.User;
import sql.SqlUserDao;

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
    private static final String USER_ID = "userId";

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
                session.setAttribute(KEY, new Object());
                session.setAttribute(USER_ID, user.getId());

                forward("/page/successLogin.html",request,response);
            } else {
                forward("/getin/login.html", request,response);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            forward("/getin/login.html", request,response);
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

}

