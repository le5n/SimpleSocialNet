package sql;

import dao.SubscriptionDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class SqlSubscriptionDaoTest {

    @Test
    public void getSubIds() throws Exception{
        SubscriptionDao subscriptionDao = new SqlSubscribeDao();
        Collection<Integer> actualIds = subscriptionDao.getSubIds(6);
        System.out.println(actualIds);

        Assert.assertTrue(actualIds.contains(3));
    }
}
