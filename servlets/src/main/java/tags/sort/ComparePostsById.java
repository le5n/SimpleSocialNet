package tags.sort;

import social.Post;

import java.util.Comparator;

public class ComparePostsById implements Comparator<Post> {
    @Override
    public int compare(Post post1, Post post2) {
        if (post1.getPostId() < post2.getPostId())
            return 1;
        else if (post1.getPostId() > post2.getPostId())
            return -1;
        else
            return 0;
    }
}
