package sql;

import common.ConnectionPool;
import dao.UserDao;
import social.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SqlUserDao implements UserDao {
    private Collection<User> allUsers = new ArrayList<>();

    private static ConnectionPool connectionPool;

    public SqlUserDao(String pathToConfig) {
        connectionPool = ConnectionPool.getInstance(pathToConfig);
    }

    @Override
    public Collection<User> getAll() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users.users;")) {
            while (resultSet.next()) {
                allUsers.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("username")
                        )
                );
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public User getUserById(int id) {
        String prepStatementUser = "SELECT * FROM users.users WHERE id=?";
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(prepStatementUser)) {

            prepStUser.setInt(1, id);
            ResultSet resultSet = prepStUser.executeQuery();
            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("username")
                );
            }

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
