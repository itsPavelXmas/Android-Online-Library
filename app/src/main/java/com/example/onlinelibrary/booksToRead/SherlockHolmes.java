package com.example.onlinelibrary.booksToRead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.onlinelibrary.R;
import com.github.barteksc.pdfviewer.PDFView;

public class SherlockHolmes extends AppCompatActivity {
    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sherlock_holmes);

        pdfView=findViewById(R.id.pdfViewSherlock);

        pdfView.useBestQuality(true);
        pdfView.enableSwipe(true);
        pdfView.fitToWidth();
        pdfView.fromAsset("SherlockHolmes.pdf").load();
    }
}