package com.example.onlinelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onlinelibrary.database.services.BookService;
import com.example.onlinelibrary.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private Button btnAddDonation;
    private Button btnViewList;
    private Button btnProfile;
    private Button btnWhoWrote;
    private Button btnBooksBuy;

    private static User LOGGED_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);
        Gson newJson = new Gson();
        String json = newJson.toJson(LOGGED_User);
        Log.i("User", json);


        btnWhoWrote=findViewById(R.id.btnSearchBook);
        btnWhoWrote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenu.this,WhoWroteItActivity.class);
                startActivity(intent);
            }
        });

        btnBooksBuy=findViewById(R.id.btnBuyBooks);
        btnBooksBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenu.this,BuyBooksActivity.class);
                startActivity(intent);
            }
        });




       btnAddDonation=findViewById(R.id.btnSellForm);
        btnAddDonation.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
             Intent intent=new Intent(MainMenu.this,AddSaleActivity.class);
              startActivity(intent);
         }
       });

        btnViewList=findViewById(R.id.btnViewBooks);
        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenu.this,DisplayList.class);
                startActivity(intent);
            }
        });

        btnProfile=findViewById(R.id.btnGoToProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainMenu.this,Profile.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
        builder.setMessage("Are you sure you want to logout?").setCancelable(false).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainMenu.this, Login.class);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
        return super.onKeyDown(keyCode, event);
    }
}