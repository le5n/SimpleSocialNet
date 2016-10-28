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

    public SqlUserDao() {
        connectionPool = ConnectionPool.getInstance(
                "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\test\\resources\\userData.properties");
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

    public void addUser(User user){
        String prepSt = "INSERT INTO `users`.`users` (`name`, `lastname`, `email`, `password`, `username`) " +
                "VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(prepSt)){
            prepStUser.setString(1,user.getName());
            prepStUser.setString(2,user.getLastName());
            prepStUser.setString(3,user.getEmail());
            prepStUser.setString(4,user.getPassword());
            prepStUser.setString(5,user.getUserName());

            prepStUser.execute();

        }
        catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }
}
