package tags;

import dao.UserDao;
import exceptions.UserNotFoundException;
import social.User;
import sql.SqlUserDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class SubscribesList extends TagSupport {

    private Collection<Integer> subscribes;

    public void setSubscribes(Collection<Integer> subscribes) {
        this.subscribes = subscribes;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getSubscribes(subscribes));
        } catch (IOException | UserNotFoundException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String getSubscribes(Collection<Integer> subscribes) throws IOException, UserNotFoundException {
        UserDao userDao = new SqlUserDao();

        StringBuilder out = new StringBuilder();

        for (Integer followerId : subscribes) {
            User user = userDao.getUserById(followerId);
            out.append("<a href=\"/GetUserServlet/?userHref=").append(user.getId()).append("\">").append(user.getUserName()).append("</a>").append("<br/>");
        }

        return out.toString();
    }
}
