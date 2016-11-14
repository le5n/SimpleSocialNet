package dao;

import social.Post;

import java.util.Collection;

public interface SubscribtionDao{
    void addSubscription(int userId, int subId);
    Collection<Integer> getSubIds(int userId);
    Collection<Post> getSubPosts(int userId);
}
