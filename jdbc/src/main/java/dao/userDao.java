package dao;

import Exceptions.UserNotFoundException;
import social.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAll();
    User getUserById (int id) throws UserNotFoundException;
    User getUserByEmail (String email) throws UserNotFoundException;
    void setRole(String email, String role);
}
