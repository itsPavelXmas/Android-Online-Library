package com.example.onlinelibrary.booksToRead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.onlinelibrary.R;
import com.github.barteksc.pdfviewer.PDFView;

public class LordOfTheRings extends AppCompatActivity {
    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_of_the_rings);


    }
}