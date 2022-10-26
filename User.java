package bookstore.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class User {

    //FIELDS
    private String ufname;
    private String umname;
    private String ulname;
    private String u_email;
    private String u_pw;
    private String u_extra_email;
    private String u_street;
    private String u_city;
    private String u_zip;
    private String u_state;


    //CONSTRUCTORS
    //base constructor
    public User() {}

    //constructor with only mandatory fields
    User(String u_email, String u_pw) {
        this.u_email = u_email;
        this.u_pw = u_pw;
    }

    //constructor with all fields
    User(String ufname, String umname, String ulname, String u_email, String u_pw, String u_extra_email,
         String u_street, String u_city, String u_zip, String u_state) {
        this.ufname = ufname;
        this.umname = umname;
        this.ulname = ulname;
        this.u_email = u_email;
        this.u_pw = u_pw;
        this.u_extra_email = u_extra_email;
        this.u_street = u_street;
        this.u_city = u_city;
        this.u_zip = u_zip;
        this.u_state = u_state;
    }


    //GETTERS
    //getter for full name
    public String getName() {
        return this.ufname + " " + this.ulname;
    }

    //getter for first name
    public String getFirstName() { return this.ufname; }

    //getter for middle name
    public String getMiddleName() { return this.umname; }

    //getter for last name
    public String getLastName() { return this.ulname; }

    //getter for user email
    public String getUserEmail() { return this.u_email; }

    //getter for user password
    public String getUserPw() { return this.u_pw; }

    //getter for user extra email
    public String getUserExtraEmail() { return this.u_extra_email; }

    //getter for user street
    public String getUserStreet() { return this.u_street; }

    //getter for user city
    public String getUserCity() { return this.u_city; }

    //getter for user zip code
    public String getUserZip() { return this.u_zip; }

    //getter for user state
    public String getUserState() { return this.u_state; }

    //getter for full home address
    public String getUserAddress() { return getUserStreet() + ", " + getUserCity() + ", " + getUserState() + ", " + getUserZip();}

    //getter for user email


    //SETTERS
    //setter for first name
    public void setFirstName (String fname) {this.ufname=fname;}

    //setter for middle name
    public void setMiddleName (String mname) {this.umname=mname;}

    //setter for last name
    public void setLastName (String lname) {this.ulname=lname;}

    //setter for user email
    public void setUserEmail (String email) {this.u_email=email;}

    //setter for user_password
    public void setUserPW (String pw) {this.u_pw=pw;}

    //setter for user extra email
    public void setExtraEmail (String extra) {this.u_extra_email=extra;}

    //setter for user street
    public void setUserStreet (String street) {this.u_street=street;}

    //setter for user city
    public void setUserCity (String city) {this.u_city=city;}

    //setter for user zip code
    public void setUserZip (String zip) {this.u_zip=zip;}

    //setter for user state
    public void setUserState (String state) {this.u_state=state;}



    public static ArrayList<User> getAllUsers() {
        Connection connection;
        String user = "root";
        String password = "JumpM@n!";
        String database = "jdbc:mysql://localhost:3306/bookstore";

        ArrayList<User> users = new ArrayList<User>();

        try {
            connection = DriverManager.getConnection(database, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT ufname\r\n"
                    + "    , umname\r\n"
                    + "    , ulname\r\n"
                    + "    , u_email\r\n"
                    + "    , u_pw\r\n"
                    + "    , u_extra_email\r\n"
                    + "    , u_street\r\n"
                    + "    , u_city\r\n"
                    + "    , u_zip\r\n"
                    + "    , u_state\r\n"
                    + "FROM USER");

            while (resultSet.next()) {
                User temp = new User(resultSet.getString("ufname"), resultSet.getString("umname"),
                        resultSet.getString("ulname"), resultSet.getString("u_email"),
                        resultSet.getString("u_pw"), resultSet.getString("u_extra_email"),
                        resultSet.getString("u_street"), resultSet.getString("u_city"),
                        resultSet.getString("u_zip"), resultSet.getString("u_state"));
                users.add(temp);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not open database.");
            System.exit(1);
        }
        return users;
    }


}
