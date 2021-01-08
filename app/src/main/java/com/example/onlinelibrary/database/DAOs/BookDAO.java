package com.example.onlinelibrary.database.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.Sale;

import java.util.List;

@Dao
public interface BookDAO {
    @Query("SELECT * FROM books")
    List<Book> getBooks();

    @Insert
    long addBook(Book book);

}
