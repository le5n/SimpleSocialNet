package tags;

import dao.UserDao;
import exceptions.UserNotFoundException;
import social.Post;
import social.User;
import sql.SqlUserDao;
import tags.sort.ComparePostsById;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubPosts extends TagSupport {

    private Collection<Post> posts;

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(getPostList(posts));
        } catch (IOException | UserNotFoundException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public static String getPostList(Collection<Post> posts) throws IOException, UserNotFoundException {
        UserDao userDao = new SqlUserDao();
        StringBuilder out = new StringBuilder();

        List<Post> listPosts = (List) posts;
        Collections.sort(listPosts, new ComparePostsById());

        for (Post post : listPosts) {
            User user = userDao.getUserById(post.getUserId());
            out.append("<tr><td>")
                    .append(user.getUserName())
                    .append("</td><td>")
                    .append(post.getPostDate())
                    .append("</td><td>")
                    .append(post.getPostText())
                    .append("</td></tr>");
        }

        return out.toString();
    }
}
