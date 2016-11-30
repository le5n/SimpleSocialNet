package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ButtonWithId extends TagSupport {
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getUserId(userId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public static String getUserId(String userId) throws IOException {
        return userId;
    }
}
