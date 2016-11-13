//package sql;
//
//import javafx.geometry.Pos;
//import org.junit.Assert;
//import org.junit.Test;
//import social.Post;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class SqlPostDaoTest {
// //   private Post expectedPost = new Post(1,1,"hello world");
//    private SqlPostDao sqlPostDao = new SqlPostDao();
//
//    @Test
//    public void getAll() throws Exception{
//        Collection<Post> actualCollection = sqlPostDao.getAll();
//
//        Assert.assertTrue(actualCollection.contains(expectedPost));
//    }
//
//    @Test
//    public void getByPostId() throws Exception{
//        Post actualPost = sqlPostDao.getByPostId(1);
//
//        Assert.assertEquals(expectedPost,actualPost);
//    }
//
//    @Test
//    public void getPostsByUserId() throws Exception{
//        Collection<Post> actualCollection = sqlPostDao.getPostsByUserId(1);
//
//        Assert.assertTrue(actualCollection.contains(expectedPost));
//    }
//}
