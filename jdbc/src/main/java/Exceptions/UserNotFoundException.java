package Exceptions;

public class UserNotFoundException extends Exception {
    @Override
    public String toString() {
        return "User with this email was not founded";
    }
}
