package sql;

import common.ConnectionPool;
import dao.PostDao;
import dao.SubscriptionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import social.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class SqlSubscribeDao implements SubscriptionDao {
    private static final Logger log = LogManager.getLogger(SqlSubscribeDao.class);
    private ConnectionPool connectionPool;
    private Collection<Integer> userSubs;
    private final String ADD_SUB = "INSERT INTO `users`.`subscribes` (`user_id`, `subscription`) VALUES (?, ?);";
    private final String FIND_SUB_ID = "SELECT * FROM users.subscribes WHERE user_id=? AND subscription=?;";
    private final String GET_SUB_IDS = "SELECT subscription FROM `users`.`subscribes` WHERE user_id=?;";
    private final String REMOVE_SUB = "DELETE FROM `users`.`subscribes` WHERE `sub_id`=?;";
    private final String GET_FOLLOWERS = "SELECT user_id FROM `users`.`subscribes` WHERE subscription=?;";

    public SqlSubscribeDao() {
        if (connectionPool == null) {
            synchronized (ConnectionPool.class) {
                if (connectionPool == null) {
                    log.debug("Initialize of con pool from " + SqlSubscribeDao.class);
                    connectionPool = ConnectionPool.getInstance(
                            "D:\\\\Программы\\\\SimpleSocialNet\\\\jdbc\\\\src\\\\main\\\\resources\\\\userData.properties");
                }
            }
        }
    }

    @Override
    public void unsubscribe(int userId, int subId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUB_ID);
             PreparedStatement delete = connection.prepareStatement(REMOVE_SUB)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, subId);
            int sub_id = 0;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    sub_id = resultSet.getInt(1);
                }
            }
            delete.setInt(1, sub_id);
            delete.execute();

        } catch (InterruptedException | SQLException e) {
            log.error("Cannot unsubscribe from " +subId + " by " + userId, e);
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
            log.error("Cannot addSubscription from userId = " + userId + " to " + subId, e);
        }
    }

    @Override
    public Collection<Post> getSubPosts(int userId) {
        PostDao sqlPostDao = new SqlPostDao();
        userSubs = getSubIds(userId);

        Collection<Post> subPosts = new LinkedList<>();
        for (Integer sub : userSubs) {
            Collection<Post> temp = sqlPostDao.getPostsByUserId(sub);
            subPosts.addAll(temp);
        }

        return subPosts;
    }

    @Override
    public Collection<Integer> getSubIds(int userId) {
        return count("subscribes", userId);
    }

    @Override
    public Collection<Integer> getFollowers(int userId) {
        return count("followers", userId);
    }

    private Collection<Integer> count(String what, int userId) {
        String prepSt;
        if (what.equals("followers")) {
            prepSt = GET_FOLLOWERS;
        } else {
            prepSt = GET_SUB_IDS;
        }
        userSubs = new LinkedList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(prepSt)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userSubs.add(resultSet.getInt(1));
                }
            }
            return userSubs;
        } catch (InterruptedException | SQLException e) {
            log.error("cannot count followers from userId " + userId, e);
            return userSubs;
        }
    }
}
