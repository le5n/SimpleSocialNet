package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SubButton extends TagSupport {
    private String subButton;

    public void setSubButton(String subButton) {
        this.subButton = subButton;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(showButton(subButton));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public static String showButton(String subButton) throws IOException {
        return subButton;
    }
}
