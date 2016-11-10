package tags;

import social.Post;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

public class Catalog extends TagSupport {

    private Collection<Post> posts;

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getPostList(posts));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public static String getPostList(Collection<Post> posts) throws IOException {
        StringBuilder out = new StringBuilder();
        for (Post post : posts)
            out.append(post.getUserId())
                    .append("</a></td><td>")
                    .append(post.getPostText())
                    .append("</td></tr>");

        return out.toString();
    }
}