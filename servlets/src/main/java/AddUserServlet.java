import social.User;
import sql.SqlUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    private SqlUserDao sqlUserDao = new SqlUserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userName = request.getParameter("username");
        String checkPas = request.getParameter("repeatPassword");

        if(check(password, checkPas) || correctEmail(email)){
            User user = new User(1,name,lastName, email, password, userName);
            sqlUserDao.addUser(user);
        }
        else {
            System.out.println("incorrect data");
        }

    }

    private boolean check(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

    private boolean correctEmail(String email){
        return email.matches("\\w{1,25}[@]\\w{1,10}\\.\\w{2,3}");
    }
}
