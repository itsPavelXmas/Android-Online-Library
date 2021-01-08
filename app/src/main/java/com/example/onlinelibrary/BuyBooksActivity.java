package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.onlinelibrary.Adapters.BookAdapter;
import com.example.onlinelibrary.booksToRead.HarryPotter;
import com.example.onlinelibrary.booksToRead.LordOfTheRings;
import com.example.onlinelibrary.booksToRead.SherlockHolmes;
import com.example.onlinelibrary.database.DAOs.BookDAO;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.BookService;
import com.example.onlinelibrary.model.Book;

import java.util.List;

public class BuyBooksActivity extends AppCompatActivity {
    private ListView listView;
    private BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_books);

        listView=findViewById(R.id.lvBuyBooks);
        Book hp = new Book("Harry potter","JK Rowling",7266332,12732);
        Book lp = new Book("Lord of The Rings","JRR Tolkien",7466352,10732);
        Book sh = new Book("The Adventures of Sherlock Holmes","Arthur Conan Doyle",74266332,1232);


        DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(BuyBooksActivity.this);
        final BookDAO bookDAO = databaseInstance.getBooks();
        final BookService bookService = new BookService(bookDAO);
        List<Book> bookList = bookDAO.getBooks();
        if(bookList.isEmpty() || bookList == null){
            bookDAO.addBook(lp);
            bookDAO.addBook(sh);
            bookDAO.addBook(hp);
        }


        bookAdapter = new BookAdapter(getApplicationContext(),bookList);
        listView.setAdapter(bookAdapter);

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    Intent intent=new Intent(BuyBooksActivity.this, HarryPotter.class);

                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent=new Intent(BuyBooksActivity.this, LordOfTheRings.class);


                    startActivity(intent);
                }
                if(position == 0){
                    Intent intent=new Intent(BuyBooksActivity.this, SherlockHolmes.class);


                    startActivity(intent);
                }
            }
        });
    }
}