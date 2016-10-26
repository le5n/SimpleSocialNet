package sql;

import common.ConnectionPool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import social.User;

import java.util.Collection;

public class SqlUserDaoTest {

    @Test
    public void getAll() throws Exception{
        SqlUserDao sqlUserDao = new SqlUserDao("" +
                "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\test\\resources\\userData.properties");
        Collection<User> actualCollection = sqlUserDao.getAll();

        User expectedUser = new User(1,"Elena","Georgievskaya", "ellenageor@gmail.com", "qwerty123", "le5n");

        Assert.assertTrue(actualCollection.contains(expectedUser));
    }
}
