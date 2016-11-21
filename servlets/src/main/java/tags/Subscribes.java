package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class Subscribes extends TagSupport {

    private Collection<Integer> subscribes;

    public void setSubscribes(Collection<Integer> subscribes) {
        this.subscribes = subscribes;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(countSubscribes(subscribes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String countSubscribes(Collection<Integer> subscribes) throws IOException {
        return String.valueOf(subscribes.size());
    }
}