package sql;

import common.ConnectionPool;
import dao.PostDao;
import dao.SubscriptionDao;
import javafx.geometry.Pos;
import social.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class SqlSubscribeDao implements SubscriptionDao {
    private ConnectionPool connectionPool;
    private Collection<Integer> userSubs;
    private final String ADD_SUB = "INSERT INTO `users`.`subscribes` (`user_id`, `subscription`) VALUES (?, ?);";
    private final String GET_IDS = "SELECT * FROM `users`.`subscribes` WHERE user_id=?;";

    public SqlSubscribeDao() {
        if (connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    connectionPool = ConnectionPool.getInstance(
                            "D:\\\\Программы\\\\SimpleSocialNet\\\\jdbc\\\\src\\\\main\\\\resources\\\\userData.properties");
                }
            }
        }
    }

    @Override
    public void addSubscription(int userId, int subId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUB)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, subId);

            preparedStatement.execute();

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Integer> getSubIds(int userId) {
        userSubs = new LinkedList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_IDS)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userSubs.add(resultSet.getInt(2));
                }
            }
            return userSubs;
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
            return userSubs;
        }

    }

    @Override
    public Collection<Post> getSubPosts(int userId) {
        PostDao sqlPostDao = new SqlPostDao();
        userSubs = getSubIds(userId);

        Collection<Post> subPosts = new LinkedList<>();
        for(Integer sub:userSubs){
            Collection<Post> temp = sqlPostDao.getPostsByUserId(sub);
            subPosts.addAll(temp);
        }

        return subPosts;
    }
}
