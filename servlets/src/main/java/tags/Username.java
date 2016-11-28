package tags;

import dao.UserDao;
import exceptions.UserNotFoundException;
import sql.SqlUserDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Username extends TagSupport {
    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getUsername(userID));
        } catch (IOException | UserNotFoundException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public static String getUsername(int userID) throws IOException, UserNotFoundException {
        UserDao userDao = SqlUserDao.getInstance();

        return userDao.getUserById(userID).getUserName();
    }
}
