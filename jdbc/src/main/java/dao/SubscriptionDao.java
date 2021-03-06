package dao;

import social.Post;

import java.util.Collection;

public interface SubscriptionDao {
    void addSubscription(int userId, int subId);
    void unsubscribe(int userId, int subId);
    Collection<Integer> getSubIds(int userId);
    Collection<Post> getSubPosts(int userId);
    Collection<Integer> getFollowers(int userId);
}
