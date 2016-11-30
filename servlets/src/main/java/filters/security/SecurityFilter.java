package filters.security;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/page/successLogin.html", "/page/allUsers.jsp","/page/uploadPhoto.html", "/page/", "/page", "/page/userPage.jsp","/page/otherUserPage.jsp" ,"/"})
public class SecurityFilter extends HttpFilter {
    private static final String KEY = "key";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (session.getAttribute(KEY) != null) {
            chain.doFilter(request, response);
        } else {
            forward("/login/login.jsp", request,response);
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void destroy() {}
}

