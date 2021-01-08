package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WhoWroteItActivity extends AppCompatActivity {
    private static final String TAG=WhoWroteItActivity.class.getSimpleName();
    private EditText mBookInput;
    private TextView mAuthorText, mTitleText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_wrote_it);
        mBookInput =(EditText)findViewById(R.id.bookInput);
        mAuthorText =(TextView) findViewById(R.id.authorText);
        mTitleText =(TextView) findViewById(R.id.titleText);


    }

    public void searchBooks(View view){
        String queryString= mBookInput.getText().toString();
        ConnectivityManager connMgr=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();


        if(networkInfo != null && networkInfo.isConnected() && queryString.length() !=0){
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText("Loading..");
        }else {
            if(queryString.length() ==0 ){
                mAuthorText.setText("");
                mTitleText.setText("Please enter a term");

            }else{

                mAuthorText.setText("");
                mTitleText.setText("Please check your network connection");
            }

        }




    }
}