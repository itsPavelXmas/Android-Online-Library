package com.example.onlinelibrary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.onlinelibrary.database.DAOs.BookDAO;
import com.example.onlinelibrary.database.DAOs.SaleDAO;
import com.example.onlinelibrary.database.DAOs.UserDao;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.Sale;
import com.example.onlinelibrary.model.User;
import com.example.onlinelibrary.utils.DateConverter;


@Database(entities = {User.class, Book.class,Sale.class}, version = 3, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class DatabaseInstance extends RoomDatabase {

    private static volatile DatabaseInstance INSTANCE;

    public abstract UserDao getUserDao();
    public abstract SaleDAO getSaleDao();
    public abstract BookDAO getBooks();

    public static DatabaseInstance getINSTANCE(Context context){

        if(INSTANCE == null){
            synchronized (DatabaseInstance.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseInstance.class,"Library.db").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }


}
