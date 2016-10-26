package sql;

import common.ConnectionPool;
import dao.PostDao;
import social.Post;

import java.sql.*;
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
    public Post getByPostId(int postId) {
        String prepStatementSearch = "SELECT post_id FROM posts.posts;";
        String prepStatementPost = "SELECT * FROM posts.posts WHERE post_id=?";
        int foundId = 0;
        Post post = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStPostId = connection.prepareStatement(prepStatementSearch);
             PreparedStatement prepStPostData = connection.prepareStatement(prepStatementPost)) {

            ResultSet resultSet = prepStPostId.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("post_id") == postId) {
                    foundId = resultSet.getInt("post_id");
                }
            }

            prepStPostData.setInt(1, foundId);
            resultSet = prepStPostData.executeQuery();
            while (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("post_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("post_text")
                );
            }
            resultSet.close();

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Collection<Post> getPostsByUserId(int userId) {
        String prepStatementPost = "SELECT * FROM posts.posts WHERE user_id=?";
        Collection<Post> UserPosts = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUserPosts = connection.prepareStatement(prepStatementPost)) {

            prepStUserPosts.setInt(1, userId);
            ResultSet resultSet = prepStUserPosts.executeQuery();
            while (resultSet.next()) {
                UserPosts.add(new Post(
                        resultSet.getInt("post_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("post_text")
                ));
            }

            resultSet.close();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
        return UserPosts;
    }

}
