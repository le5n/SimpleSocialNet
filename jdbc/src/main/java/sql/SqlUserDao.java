package sql;

import exceptions.UserNotFoundException;
import common.ConnectionPool;
import dao.UserDao;
import social.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SqlUserDao implements UserDao {

    private Collection<User> allUsers = new ArrayList<>();

    private static ConnectionPool connectionPool;
    private final String GET_ALL_USERS = "SELECT * FROM users.users;";
    private final String GET_USER_BY_ID = "SELECT * FROM users.users WHERE id=?";
    private final String GET_USER_BY_EMAIL = "SELECT * FROM users.users WHERE email=?";
    private final String ADD_USER = "INSERT INTO `users`.`users` (`name`, `lastname`, `email`, `password`, `username`) " +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String SET_ROLE = "INSERT INTO `users`.`roles` (`email`, `role`) VALUES (?,?);";

    public SqlUserDao() {
        connectionPool = ConnectionPool.getInstance(
                "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\main\\resources\\userData.properties");
    }

    @Override
    public Collection<User> getAll() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS)) {
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
    public User getUserById(int id) throws UserNotFoundException {
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_ID)) {

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

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            prepStUser.setString(1, email);
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

    @Override
    public void setRole(String email, String role) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepSt = connection.prepareStatement(SET_ROLE)) {
            prepSt.setString(1, email);
            prepSt.setString(2, role);

            prepSt.execute();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(ADD_USER)) {

            prepStUser.setString(1, user.getName());
            prepStUser.setString(2, user.getLastName());
            prepStUser.setString(3, user.getEmail());
            prepStUser.setString(4, user.getPassword());
            prepStUser.setString(5, user.getUserName());

            prepStUser.execute();

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

}
