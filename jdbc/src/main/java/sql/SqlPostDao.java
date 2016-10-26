package sql;

import common.ConnectionPool;
import dao.PostDao;
import social.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class SqlPostDao implements PostDao {
    private static ConnectionPool connectionPool;
    private Collection<Post> allPosts = new ArrayList<>();

    public SqlPostDao(String pathToConfig) {
        connectionPool = ConnectionPool.getInstance(pathToConfig);
    }


    @Override
    public Collection<Post> getAll() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM posts.posts;")) {
            while (resultSet.next()) {
                allPosts.add(
                        new Post(
                                resultSet.getInt("user_id"),
                                resultSet.getInt("post_id"),
                                resultSet.getString("post_text")
                        )
                );
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        return allPosts;
    }

    @Override
    public Post getByPostId(int userId, int postId) {
        return null;
    }
}
