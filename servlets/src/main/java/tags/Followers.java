package tags;

import dao.SubscriptionDao;
import social.Post;
import sql.SqlSubscribeDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class Followers extends TagSupport {

    private Collection<Post> posts;

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(countFollowers(posts));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String countFollowers(Collection<Post> subPosts) throws IOException {
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();

        return String.valueOf(subPosts.size());
    }
}