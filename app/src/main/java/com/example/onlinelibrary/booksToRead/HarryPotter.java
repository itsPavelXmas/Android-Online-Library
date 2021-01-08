package com.example.onlinelibrary.booksToRead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.onlinelibrary.R;
import com.github.barteksc.pdfviewer.PDFView;

public class HarryPotter extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_harry_potter);

        pdfView=findViewById(R.id.pdfViewer);

        pdfView.useBestQuality(true);
        pdfView.enableSwipe(true);
        pdfView.fitToWidth();
        pdfView.fromAsset("android.pdf").load();
    }
}