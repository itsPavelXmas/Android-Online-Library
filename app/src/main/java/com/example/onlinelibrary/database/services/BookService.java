package com.example.onlinelibrary.database.services;

import com.example.onlinelibrary.database.DAOs.BookDAO;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class BookService implements BookDAO {
    private final BookDAO bookDAO;
    public ArrayList<Book>books=new ArrayList<>();

    Book harryPotter=new Book("Harry Potter","J.K Rowling",213141,234);
    Book LordOfTheRings=new Book(" Lord Of TheRings","J.R Tolkien",213141,234);
    Book StarWars=new Book("Star Wars ","George Lucas",213141,234);



    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    public long addBook(Book book) {
        return bookDAO.addBook(book);
    }
}
