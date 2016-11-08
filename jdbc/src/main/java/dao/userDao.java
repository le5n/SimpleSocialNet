package dao;

import social.User;
import exceptions.UserNotFoundException;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAll();
    User getUserById (int id) throws UserNotFoundException;
    User getUserByEmail (String email) throws UserNotFoundException;
   // void setRole(String email, String role);
}
