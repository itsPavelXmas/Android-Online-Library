package com.example.onlinelibrary.database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.onlinelibrary.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE email=:email")
    User getUser(String email);

    @Insert
    Long addUser(User user);

    @Delete
    void deleteUSer(User user);

    @Update
    Integer updateUser(User user);
}
