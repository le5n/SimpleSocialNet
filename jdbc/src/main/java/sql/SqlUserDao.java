package sql;

import common.ConnectionPool;
import dao.UserDao;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import security.StringEncryptUtil;
import social.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SqlUserDao implements UserDao {
    private Collection<User> allUsers = new ArrayList<>();
    private static final Logger log = LogManager.getLogger(SqlUserDao.class);
    private static SqlUserDao sqlUserDao;
    private static ConnectionPool connectionPool;

    private static final String GET_ALL_USERS = "SELECT * FROM users.users;";
    private static final String GET_USER_BY_ID = "SELECT * FROM users.users WHERE id=?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users.users WHERE email=?";
    private static final String ADD_USER = "INSERT INTO `users`.`users` (`name`, `lastname`, `email`, `password`, `username`) " +
            "VALUES (?, ?, ?, ?, ?);";
    private final String CHANGE_USERNAME = "UPDATE `users`.`users` SET `username`=? WHERE `email`=?;";

    public static SqlUserDao getInstance() {
        if (sqlUserDao == null) {
            synchronized (SqlPostDao.class) {
                if (sqlUserDao == null) {
                    log.debug("gettting instance of " + SqlUserDao.class);
                    sqlUserDao = new SqlUserDao();
                }
            }
        }
        return sqlUserDao;
    }

    private SqlUserDao() {
        if (connectionPool == null) {
            log.debug(SqlUserDao.class + " constructor inited");
            connectionPool = ConnectionPool.getInstance(
                    "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\main\\resources\\postData.properties");
        }
    }


    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(ADD_USER)) {

            String hash = StringEncryptUtil.encrypt(user.getPassword());

            prepStUser.setString(1, user.getName());
            prepStUser.setString(2, user.getLastName());
            prepStUser.setString(3, user.getEmail());
            prepStUser.setString(4, hash);
            prepStUser.setString(5, user.getUserName());

            prepStUser.execute();

        } catch (InterruptedException | SQLException e) {
            log.error("Cannot addUser", e);
        }
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
            log.error("Cannot getAllUsers", e);
        }

        return allUsers;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_ID)) {

            prepStUser.setInt(1, id);
            try (ResultSet resultSet = prepStUser.executeQuery()) {
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
            }

        } catch (InterruptedException | SQLException e) {
            log.error("Cannot get user by id = " + id, e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            prepStUser.setString(1, email);
            try (ResultSet resultSet = prepStUser.executeQuery()) {
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
            }
        } catch (InterruptedException | SQLException e) {
            log.error("Cannot get user by email, where email = " + email, e);
        }
        return user;
    }

    //// TODO: 28.11.2016 add this ability to the app
    @Override
    public void changeUsername(String email, String newUsername) throws UserNotFoundException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepSt = connection.prepareStatement(CHANGE_USERNAME)) {
            prepSt.setString(1, newUsername);
            prepSt.setString(2, email);
            prepSt.execute();
        } catch (InterruptedException | SQLException e) {
            log.error("Cannot change username where email=" + email, e);
        }
    }
}
