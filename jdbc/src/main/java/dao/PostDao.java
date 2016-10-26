package dao;

import social.Post;

import java.util.Collection;

public interface PostDao {
    Collection<Post> getAll();
    Post getPostById(int userId, int postId);
}
