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
        String prepStatementSearch = "SELECT id FROM users.users;";
        String prepStatementUser = "SELECT * FROM users.users WHERE id=?";
        int foundId = 0;
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStSearchId = connection.prepareStatement(prepStatementSearch);
             PreparedStatement prepStUser = connection.prepareStatement(prepStatementUser)) {

            ResultSet resultSet = prepStSearchId.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt(1) == id) {
                    foundId = resultSet.getInt(1);
                }
            }

            prepStUser.setInt(1, foundId);
            resultSet = prepStUser.executeQuery();
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

            return user;

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
