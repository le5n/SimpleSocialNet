package dao;

import social.Post;

import java.util.Collection;

public interface PostDao {
    Collection<Post> getAll();
    Post getByPostId(int postId);
    Collection<Post> getPostsByUserId(int userId);
    void addPost(String postText, int userId);
}
