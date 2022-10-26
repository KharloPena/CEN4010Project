package bookstore.demo;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String u_email) {
        super("Could not find user with user email " + u_email);
    }
}
