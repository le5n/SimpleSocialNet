package dao;

import exceptions.UserAlreadyExistsException;
import social.User;
import exceptions.UserNotFoundException;

import java.util.Collection;

public interface UserDao {
    void addUser(User user) throws UserAlreadyExistsException;
    Collection<User> getAll();
    User getUserById (int id) throws UserNotFoundException;
    User getUserByEmail (String email) throws UserNotFoundException;
    void changeUsername(String email, String newUsername) throws UserNotFoundException;
}
