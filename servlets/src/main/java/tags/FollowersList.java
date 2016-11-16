package tags;

import dao.UserDao;
import exceptions.UserNotFoundException;
import social.User;
import sql.SqlUserDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class FollowersList extends TagSupport {

    private Collection<Integer> followers;

    public void setFollowers(Collection<Integer> followers) {
        this.followers = followers;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getFollowers(followers));
        } catch (IOException | UserNotFoundException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String getFollowers(Collection<Integer> followers) throws IOException, UserNotFoundException {
        UserDao userDao = new SqlUserDao();

        StringBuilder out = new StringBuilder();

        for (Integer followerId : followers) {
            User user = userDao.getUserById(followerId);
            out.append("<a href=\"/GetUserServlet/?userHref=").append(user.getId()).append("\">").append(user.getUserName()).append("</a>").append("<br/>");
        }

        return out.toString();
    }
}
