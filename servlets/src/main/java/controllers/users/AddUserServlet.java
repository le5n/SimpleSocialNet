package controllers.users;

import social.User;
import sql.SqlUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    private SqlUserDao sqlUserDao = new SqlUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userName = request.getParameter("username");
        String checkPas = request.getParameter("repeatPassword");
        String[] args = new String[]{name, lastName, password, checkPas, email, userName};

        for (String arg : args) {
            if (arg.equals("")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration/error.html");
                dispatcher.forward(request, response);
            }
        }
        if (checkPas.equals(password) && correctEmail(email)) {
            User user = new User(1, name, lastName, email, password, userName);
            sqlUserDao.addUser(user);
            //sqlUserDao.setRole(userName,"user");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration/success.html");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration/error.html");
            dispatcher.forward(request, response);

        }

    }

    private boolean correctEmail(String email) {
        return email.matches("\\w{1,25}[@]\\w{1,10}\\.\\w{2,3}");
    }
}
