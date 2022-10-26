package bookstore.demo;

import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BookSortController {


    @GetMapping ("/books/{genre}")
    List <Book> getGenre(@PathVariable String genre) {
        List <Book> books = Book.getAllBooks();
        List <Book> genreFound = new ArrayList <Book> ();
        for (Book b : books) {
            if (b.getGenre().toLowerCase().equals(genre.toLowerCase())) {
                genreFound.add(b);//Will add book with x genre to array named book genre
            }
        }
        return genreFound;
    }

    @GetMapping("/books/wishlist/{user}")
    public static List<Book> getWishByUser(@PathVariable String user) {
        List <Book> books = Book.getWishlistByUser(user);
        return books;
    }

    @GetMapping ("/books/ISBN/{isbn}")
    static List <Book> getBookByISBN(@PathVariable String isbn) {
        List <Book> books = Book.getAllBooks();
        List <Book> isbnFound = new ArrayList <Book> ();
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                isbnFound.add(b);//Will add book with x genre to array named book isbn
            }
        }
        return isbnFound;
    }

    @GetMapping("/books/shopcart/{usern}")
    public static List<Book> getCartByUser(@PathVariable String user) {
        List <Book> books = Book.getShopCartByUser(user);
        return books;
    }

    @GetMapping ("/books/author/{author}")
    List <Book> getBooksByAuthor(@PathVariable String author) {
        List <Book> books = Book.getAllBooks();
        List <Book> authorBooks = new ArrayList<Book>();
        for (Book b : books) {
            if ((b.getA_fname()+b.getA_lname()).toLowerCase().equals(author.toLowerCase())) {
                authorBooks.add(b);//Will add book with x genre to array named book genre
            }
        }
        return authorBooks;
    }

}