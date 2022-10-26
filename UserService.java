package bookstore.demo;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserService {
    public void createUser(User u) {
        Connection connection;
        String user = "root";
        String password = "JumpM@n!";
        String database = "jdbc:mysql://localhost:3306/bookstore";

        try {
            connection = DriverManager.getConnection(database, user, password);
            String test = "INSERT INTO user(ufname,umname,ulname,u_email,u_pw,u_extra_email,u_street,u_city,u_zip,u_state)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prep = connection.prepareStatement(test);

            prep.setString(1,u.getFirstName());
            prep.setString(2,u.getMiddleName());
            prep.setString(3,u.getLastName());
            prep.setString(4,u.getUserEmail());
            prep.setString(5,u.getUserPw());
            prep.setString(6,u.getUserExtraEmail());
            prep.setString(7,u.getUserStreet());
            prep.setString(8,u.getUserCity());
            prep.setString(9,u.getUserZip());
            prep.setString(10,u.getUserState());

            prep.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not open database.");
            System.exit(1);
        }
    }
}