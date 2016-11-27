package exceptions;

public class UserAlreadyExistsException extends Exception {
    @Override
    public String toString() {
        return "this user already exists";
    }
}
