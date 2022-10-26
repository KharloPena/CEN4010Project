package bookstore.demo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CreditCard {
    private String cnum;
    private String cvv2;
    private String cfname;
    private String cm_init;
    private String clname;
    private String c_street;
    private String c_city;
    private String c_zip;
    private String c_state;
    private String c_user;



    public CreditCard() {}

    public CreditCard(String num, String sec, String fname, String m_init, String lname, String street, String city,
                      String zip, String state, String user) {
        this.cnum=num;
        this.cvv2=sec;
        this.cfname=fname;
        this.cm_init=m_init;
        this.clname=lname;
        this.c_street=street;
        this.c_city=city;
        this.c_zip=zip;
        this.c_state=state;
        this.c_user=user;
    }



    //getter for credit card number
    public String getCardNum() {return this.cnum; }

    //getter for CVV2
    public String getCVV2() { return this.cvv2; }

    //getter for first name
    public String getFirstName() { return this.cfname; }

    //getter for middle initial
    public String getMiddleInitial() { return this.cm_init; }

    //getter for last name
    public String getLastName() { return this.clname; }

    //getter for card street
    public String getCardStreet() { return this.c_street; }

    //getter for card city
    public String getCardCity() { return this.c_city; }

    //getter for card zip code
    public String getCardZip() { return this.c_zip; }

    //getter for card state
    public String getCardState() { return this.c_state; }

    //getter for card user
    public String getCardUser() { return this.c_user; }



    //setter for credit card number
    public void setCardNum(String num) {this.cnum=num; }

    //setter for CVV2
    public void setCVV2() {this.cvv2=cvv2; }

    //setter for first name
    public void setFirstName (String fname) {this.cfname=fname;}

    //setter for middle name
    public void setMiddleName (String mname) {this.cm_init=mname;}

    //setter for last name
    public void setLastName (String lname) {this.clname=lname;}

    //setter for card street
    public void setCardStreet (String street) {this.c_street=street;}

    //setter for card city
    public void setCardCity (String city) {this.c_city=city;}

    //setter for card zip code
    public void setCardZip (String zip) {this.c_zip=zip;}

    //setter for card state
    public void setCardState (String state) {this.c_state=state;}

    //setter for card user
    public void setCardUser (String user) {this.c_user=user;}



    public static ArrayList<CreditCard> getAllCards() {
        Connection connection;
        String user = "root";
        String password = "JumpM@n!";
        String database = "jdbc:mysql://localhost:3306/bookstore";

        ArrayList<CreditCard> cards = new ArrayList<CreditCard>();

        try {
            connection = DriverManager.getConnection(database, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT card_num\r\n"
                            + "    , CVV2\r\n"
                            + "    , c_fname\r\n"
                            + "    , c_minit\r\n"
                            + "    , c_lname\r\n"
                            + "    , c_street\r\n"
                            + "    , c_city\r\n"
                            + "    , c_zip\r\n"
                            + "    , c_state\r\n"
                            + "    , c_user\r\n"
                    + "FROM CARD INNER JOIN REGISTERS WHERE card_num = c_num");

            while (resultSet.next()) {
                CreditCard temp = new CreditCard(resultSet.getString("card_num"),
                        resultSet.getString("CVV2"), resultSet.getString("c_fname"),
                        resultSet.getString("c_minit"), resultSet.getString("c_lname"),
                        resultSet.getString("c_street"), resultSet.getString("c_city"),
                        resultSet.getString("c_zip"), resultSet.getString("c_state"),
                        resultSet.getString("c_user"));
                cards.add(temp);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not open database.");
            System.exit(1);
        }
        return cards;
    }
}
