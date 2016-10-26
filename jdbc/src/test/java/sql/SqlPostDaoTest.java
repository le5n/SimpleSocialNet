package sql;

import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;
import social.Post;

import java.util.Collection;

public class SqlPostDaoTest {
    private SqlPostDao sqlPostDao = new SqlPostDao("" +
            "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\test\\resources\\postData.properties");
    @Test
    public void getAll() throws Exception{
        Post expectedPost = new Post(1,1,"hello world");
        Collection<Post> actualCollection = sqlPostDao.getAll();

        Assert.assertTrue(actualCollection.contains(expectedPost));
    }

    @Test
    public void getByPostId() throws Exception{

    }
}
