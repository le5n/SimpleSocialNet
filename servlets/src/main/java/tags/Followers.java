package tags;

import dao.SubscriptionDao;
import social.Post;
import sql.SqlSubscribeDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class Followers extends TagSupport {

    private Collection<Integer> followers;

    public void setFollowers(Collection<Integer> followers) {
        this.followers = followers;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(countFollowers(followers));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String countFollowers(Collection<Integer> subPosts) throws IOException {
        return String.valueOf(subPosts.size());
    }
}