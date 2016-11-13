package sql;

import common.ConnectionPool;
import dao.PostDao;
import social.Post;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class SqlPostDao implements PostDao {

    private static ConnectionPool connectionPool;
    private Collection<Post> allPosts = new ArrayList<>();

    private final String GET_ALL_POSTS = "SELECT * FROM posts.posts;";
    private final String GET_BY_POST_ID = "SELECT * FROM posts.posts WHERE post_id=?";
    private final String GET_POSTS_BY_USER_ID = "SELECT * FROM posts.posts WHERE user_id=?";
    private static final String ADD_POST = "INSERT INTO `posts`.`posts` (`user_id`, `post_date`, `post_text`) VALUES (?,?,?);";

    public SqlPostDao() {
        if (connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    connectionPool = ConnectionPool.getInstance(
                            "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\main\\resources\\postData.properties");
                }
            }
        }
    }

    @Override
    public Collection<Post> getAll() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_POSTS)) {
            while (resultSet.next()) {
                allPosts.add(new Post(
                                resultSet.getInt("post_id"),
                                resultSet.getInt("user_id"),
                                resultSet.getString("post_text"),
                                resultSet.getString("post_date")
                        )
                );
            }
            resultSet.close();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        return allPosts;
    }

    @Override
    public Post getByPostId(int postId) {
        Post post = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStPostData = connection.prepareStatement(GET_BY_POST_ID)) {

            prepStPostData.setInt(1, postId);
            // TODO: 13.11.2016 try
            ResultSet resultSet = prepStPostData.executeQuery();
            while (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("post_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("post_text"),
                        resultSet.getString("post_date")
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
        Collection<Post> UserPosts = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUserPosts = connection.prepareStatement(GET_POSTS_BY_USER_ID)) {

            prepStUserPosts.setInt(1, userId);
            ResultSet resultSet = prepStUserPosts.executeQuery();
            while (resultSet.next()) {
                UserPosts.add(new Post(
                        resultSet.getInt("post_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("post_text"),
                        resultSet.getString("post_date")
                ));
            }

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
        return UserPosts;
    }

    @Override
    public void addPost(String postText, int userId) {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement prepSt = connection.prepareStatement(ADD_POST)) {
            LocalDateTime localDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());


            prepSt.setInt(1, userId);
            prepSt.setString(2,localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy : hh - mm")));
            prepSt.setString(3, postText);

            prepSt.execute();

        }catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

}
