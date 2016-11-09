package security;

import dao.UserDao;
import sql.SqlUserDao;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/page/userPage.html", "/page/uploadPhoto.html"})
public class SecurityFilter extends HttpFilter {
    private static final String KEY = "key";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (session.getAttribute(KEY) != null) {
            System.out.println("key not null filter");
            chain.doFilter(request, response);
        } else {
            System.out.println("key is null in filter check");
            forward("/getin/login.html", request,response);
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

}

