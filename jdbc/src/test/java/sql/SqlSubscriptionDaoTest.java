package sql;

import dao.SubscriptionDao;
import org.junit.Assert;
import org.junit.Test;
import social.Post;

import java.util.Collection;

public class SqlSubscriptionDaoTest {
    private SubscriptionDao subscriptionDao = SqlSubscribeDao.getInstance();

    @Test
    public void getSubIds() throws Exception{

        Collection<Integer> actualIds = subscriptionDao.getSubIds(1);

        Assert.assertTrue(actualIds.contains(2));
    }

    @Test
    public void getSubPosts() throws Exception{
        Post expectedPost = new Post(2,2,"test post","10/10/10");
        Collection<Post> subPosts=subscriptionDao.getSubPosts(1);

        Assert.assertTrue(subPosts.contains(expectedPost));
    }
}
