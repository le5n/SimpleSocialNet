package exceptions;

public class InvalidPasswordException extends Exception{
    @Override
    public String toString() {
        return "The password which was entered is incorrect";
    }
}
