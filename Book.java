package bookstore.demo;

import java.sql.*;
import java.util.ArrayList;

public class Book {

    private String isbn;

    private String bname;

    private String bdesc;

    private String price;

    private String a_fname;

    private String a_lname;

    private String genre;

    private String pub_name;

    private String yr_pub;

    private String copies_sold;

    private String rating;



    //CONSTRUCTORS
    //base constructor
    public Book() {}



    //constructor with all fields
    public Book(String isbn, String bname, String bdesc, String price, String a_fname, String a_lname, String genre, String pub_name, String yr_pub, String copies_sold, String rating) {
        this.isbn = isbn;
        this.bname = bname;
        this.bdesc = bdesc;
        this.price = price;
        this.a_fname = a_fname;
        this.a_lname = a_lname;
        this.genre = genre;
        this.pub_name = pub_name;
        this.yr_pub = yr_pub;
        this.copies_sold = copies_sold;
        this.rating = rating;
    }



    //GETTERS
    public String getIsbn() {
        return isbn;
    }

    public String getBname() {
        return bname;
    }

    public String getBdesc() {
        return bdesc;
    }

    public String getPrice() {
        return price;
    }

    public String getA_fname() {
        return a_fname;
    }

    public String getA_lname() {
        return a_lname;
    }

    public String getGenre() {
        return genre;
    }

    public String getPub_name() {
        return pub_name;
    }

    public String getYr_pub() {
        return yr_pub;
    }

    public String getCopies_sold() {
        return copies_sold;
    }

    public String getRating() {
        return rating;
    }


    //SETTERS
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setA_fname(String a_fname) {
        this.a_fname = a_fname;
    }

    public void setA_lname(String a_lname) {
        this.a_lname = a_lname;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public void setYr_pub(String yr_pub) {
        this.yr_pub = yr_pub;
    }

    public void setCopies_sold(String copies_sold) {
        this.copies_sold = copies_sold;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



    public static ArrayList<Book> getAllBooks() {
        Connection connection;
        String user = "root";
        String password = "JumpM@n!";
        String database = "jdbc:mysql://localhost:3306/bookstore";

        ArrayList<Book> books = new ArrayList<Book>();

        try {
            connection = DriverManager.getConnection(database, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT isbn\r\n"
                            + "    , bname\r\n"
                            + "    , bdesc\r\n"
                            + "    , price\r\n"
                            + "    , a_fname\r\n"
                            + "    , a_lname\r\n"
                            + "    , genre\r\n"
                            + "    , pub_name\r\n"
                            + "    , yr_pub\r\n"
                            + "    , copies_sold\r\n"
                            + "    , rating\r\n"
                            + "FROM BOOK");

            while (resultSet.next()) {
                Book temp = new Book (resultSet.getString("isbn"), resultSet.getString("bname"),
                        resultSet.getString("bdesc"), resultSet.getString("price"),
                        resultSet.getString("a_fname"), resultSet.getString("a_lname"),
                        resultSet.getString("genre"),
                        resultSet.getString("pub_name"), resultSet.getString("yr_pub"),
                        resultSet.getString("copies_sold"), resultSet.getString("rating"))
                        ;
                books.add(temp);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not open database.");
            System.exit(1);
        }
        return books;
    }


    public static ArrayList<Book> getWishlistByUser(String usern) {
        Connection connection;
        String user = "root";
        String password = "JumpM@n!";
        String database = "jdbc:mysql://localhost:3306/bookstore";

        ArrayList<Book> wishBooks = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(database, user, password);
            Statement statement = connection.createStatement();
            String query = String.format("select b_isbn from contains_wish where wish_name = (select wl_name from wishlist where wl_username = '%s')",usern);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                wishBooks.add(BookSortController.getBookByISBN(resultSet.getString("b_isbn")).get(0));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not open database.");
            System.exit(1);
        }

        return wishBooks;
    }

    public static ArrayList<Book> getShopCartByUser(String usern) {
        Connection connection;
        String user = "root";
        String password = "JumpM@n!";
        String database = "jdbc:mysql://localhost:3306/bookstore";

        ArrayList<Book> wishBooks = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(database, user, password);
            Statement statement = connection.createStatement();
            String query = String.format("select b_isbn from contains_cart where shop_name = (select cart_name from shopcart where cart_username = '%s')",usern);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                wishBooks.add(BookSortController.getBookByISBN(resultSet.getString("b_isbn")).get(0));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not open database.");
            System.exit(1);
        }

        return wishBooks;
    }
}
