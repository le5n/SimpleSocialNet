package dao;

import social.Post;

import java.util.Collection;

public interface PostDao {
    Collection<Post> getAll();
    Post getByPostId(int userId, int postId);
}
