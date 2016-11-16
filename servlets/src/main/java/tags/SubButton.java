package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SubButton extends TagSupport {
    private boolean isSubscribed;

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(showButton(isSubscribed));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String showButton(boolean isSubscribed) throws IOException {
        if (!isSubscribed)
            return "subscribe";
        else if (isSubscribed)
            return "unsubscribe";
        else
            return "unknown";

    }
}
