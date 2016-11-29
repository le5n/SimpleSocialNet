package sql;

import org.junit.Assert;
import org.junit.Test;
import social.User;

import java.util.Collection;

public class SqlUserDaoTest {
    private SqlUserDao sqlUserDao = SqlUserDao.getInstance();
    private User expectedUser = new User(1,"Elena","Georgievskaya", "ellenageor@gmail.com", "qwerty123", "le5n");
    //"D:\\Программы\\SimpleSocialNet\\jdbc\\src\\test\\resources\\userData.properties"

    @Test
    public void getAll() throws Exception{
        Collection<User> actualCollection = sqlUserDao.getAll();

        Assert.assertTrue(actualCollection.contains(expectedUser));
    }

    @Test
    public void getById() throws Exception{
        User actualUser = sqlUserDao.getUserById(1);

        Assert.assertEquals(expectedUser,actualUser);
    }
}
