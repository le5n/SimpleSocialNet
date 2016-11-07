package exceptions;

public class UserNotFoundException extends Exception {
    @Override
    public String toString() {
        return "Such user was not found in database";
    }
}
