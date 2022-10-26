package bookstore.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
    private UserService user_serv;

    @Autowired
    public UserController(UserService user_serv) {
        this.user_serv=user_serv;
    }
    @GetMapping ("/users/{u_email}")
    public static User getUserByUserEmail(@PathVariable String u_email) {
        List <User> users = User.getAllUsers();
        for (User u : users) {
            if (u.getUserEmail().toLowerCase().equals(u_email.toLowerCase()))
                return u;
        }
        throw new UserNotFoundException(u_email);
    }

    @PostMapping ("/users/create")
    public void createUser(@RequestBody User u) {
        user_serv.createUser(u);
    }
}
