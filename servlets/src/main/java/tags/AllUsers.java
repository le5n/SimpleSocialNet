package tags;

import social.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class AllUsers extends TagSupport {

    private Collection<User> users;

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getAllUsers(users));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String getAllUsers(Collection<User> users) throws IOException {
        StringBuilder out = new StringBuilder();
        for (User user : users)
            out.append(user).append("<br/>");
        return out.toString();
    }
}