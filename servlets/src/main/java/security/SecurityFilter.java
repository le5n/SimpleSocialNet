package security;

import dao.UserDao;
import exceptions.UserNotFoundException;
import social.User;
import sql.SqlUserDao;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter(urlPatterns = {"/page/userPage.html", "/index.jsp", "/page/uploadPhoto.html"})
public class SecurityFilter extends HttpFilter {
    private static final String KEY = "key";
    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = new SqlUserDao();
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute(KEY) != null) {
            chain.doFilter(request, response);
        } else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/getin/login.html");
            requestDispatcher.forward(request, response);
        }


        Map<String, String[]> parameterMap = request.getParameterMap();

        if (parameterMap.containsKey("password") && parameterMap.containsKey("email")) {
            try {
                User user = authorize(parameterMap);
                if (user != null) {
                    session.setAttribute(KEY, new Object());
                    System.out.println("attribute set");
                    chain.doFilter(request, response);
                } else
                    request.getRequestDispatcher("/getin/error.html").forward(request, response);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/getin/login.html");
            requestDispatcher.forward(request, response);
        }
    }

    private User authorize(Map<String, String[]> parameterMap) throws UserNotFoundException {
        String email = parameterMap.get("email")[0];
        String password = parameterMap.get("password")[0];
        String hash = StringEncryptUtil.encrypt(password);

        User user = userDao.getUserByEmail(email);
        if (user.getPassword().equals(hash)) {
            return user;
        } else {
            return null;
        }
    }
}

