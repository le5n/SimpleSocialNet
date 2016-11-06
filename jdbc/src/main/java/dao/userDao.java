package dao;

import social.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAll();
    User getUserById (int id);
    void setRole(String username, String role);
}
