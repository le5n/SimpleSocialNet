package sql;

import common.ConnectionPool;
import dao.SubscribtionDao;
import social.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class SqlSubscribeDao implements SubscribtionDao{
    private ConnectionPool connectionPool;
    private final String ADD_SUB = "INSERT INTO `users`.`subscribes` (`user_id`, `subscription`) VALUES (?, ?);";

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
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUB)){
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2, subId);

            preparedStatement.execute();

        }catch (InterruptedException | SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Integer> getSubIds(int userId) {
        return null;
    }

    @Override
    public Collection<Post> getSubPosts(int userId) {
        return null;
    }
}
