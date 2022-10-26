package bookstore.demo;

public class CreditCardNotFoundException extends RuntimeException {
    CreditCardNotFoundException(String u_email) {
        super("Could not find any cards with user email " + u_email);
    }
}
