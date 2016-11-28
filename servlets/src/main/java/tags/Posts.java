package tags;

import social.Post;
import tags.sort.ComparePostsById;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Posts extends TagSupport {

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
        List<Post> listPosts = (List) posts;
        Collections.sort(listPosts, new ComparePostsById());

        StringBuilder out = new StringBuilder();
        for (Post post : listPosts)
            out.append("<tr><td>")
                    .append(post.getPostDate())
                    .append("</a></td><td>")
                    .append(post.getPostText())
                    .append("</td></tr>");

        return out.toString();
    }
}