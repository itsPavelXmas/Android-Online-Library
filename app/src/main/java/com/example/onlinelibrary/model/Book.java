package com.example.onlinelibrary.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    @NonNull
    String title;
    @NonNull
    String author;
    @NonNull
    Integer phoneNumber;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    Integer IBN;

    public Book(String title, String author, int phoneNumber, int IBN) {
        this.title = title;
        this.author = author;
        this.phoneNumber = phoneNumber;
        this.IBN = IBN;
    }
    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIBN() {
        return IBN;
    }

    public void setIBN(Integer IBN) {
        this.IBN = IBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", IBN=" + IBN +
                '}';
    }
}
