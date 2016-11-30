package controllers.users;

import exceptions.UserAlreadyExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import social.User;
import sql.SqlUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddUserServlet.class);
    private SqlUserDao sqlUserDao = SqlUserDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userName = request.getParameter("username");
        String checkPas = request.getParameter("repeatPassword");

        String[] args = new String[]{name, lastName, password, checkPas, email, userName};

        for (String arg : args) {
            if (arg.equals(""))
                forward("/registration/error.html", request, response);
        }
        if (isValid(email, password, checkPas)) {
            User user = new User(1, name, lastName, email, password, userName);
            try {
                sqlUserDao.addUser(user);
                log.info("new user added");
            } catch (UserAlreadyExistsException e) {
                log.warn("user cannot register again", e);
            }
            forward("/registration/success.html", request, response);
        } else {
            log.error("user cannot register beacuse of incorrect email or password repeat");
            forward("/registration/error.html", request, response);
        }

    }

    private boolean isValid(String email, String password, String checkPas) {
        return checkPas.equals(password) && correctEmail(email);
    }

    private boolean correctEmail(String email) {
        return email.matches("\\w{1,25}[@]\\w{1,10}\\.\\w{2,3}");
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
