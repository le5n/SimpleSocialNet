package controllers.login;

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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    private static final String KEY = "key";
    private static final String USER_ID = "userId";

    private static final Logger log = LogManager.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlUserDao sqlUserDao = SqlUserDao.getInstance();
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

                log.info("user logged in, userId = " +user.getId());

                forward("/login/successLogin.html", request, response);
            } else {
                forward("/login/error.html", request, response);
            }
        } catch (UserNotFoundException e) {
            log.warn("user failed to login", e);
            forward("/login/login.jsp", request, response);
        } catch (NullPointerException e) {
            log.warn("some fields were left empty in login", e);
            forward("/login/error.html", request, response);
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

}

