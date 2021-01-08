package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.onlinelibrary.database.DAOs.UserDao;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.UserService;
import com.example.onlinelibrary.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    Button callSignUp;
    Button callContact;
    Button callPreview;
    Button btnLogIn;
    Button callSettings;
    Button Locations;

    private TextInputEditText etUsername,etPassword;
    private static User LOGGED_User;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        etUsername=findViewById(R.id.etUsernameLg);
        etPassword=findViewById(R.id.etPasswordLg);

        Locations=findViewById(R.id.btnLocation);
        Locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Location.class);
                startActivity(intent);
            }
        });


        btnLogIn =findViewById(R.id.btnToBooks);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validateUsername() && validatePassword()){
                   String userN=etUsername.getText().toString();
                   String password=etPassword.getText().toString();

                   DatabaseInstance databaseInstance= DatabaseInstance.getINSTANCE(Login.this);
                   UserDao userDao=databaseInstance.getUserDao();
                   UserService userService=new UserService(userDao);
                   LOGGED_User=userService.getUser(userN);
                   if(LOGGED_User != null){
                       if(LOGGED_User.getPassword().equals(password)){
                           Intent intent=new Intent(getApplicationContext(),MainMenu.class);
                           startActivity(intent);

                           etUsername.setText(null);
                           etPassword.setText(null);
                       }
                       else{
                           Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                       }
                   }
                   else{
                       Toast.makeText(getApplicationContext(), "No user has been found. Please sign up first", Toast.LENGTH_SHORT).show();
                   }

               }
            }
        });

        callPreview=findViewById(R.id.idPreview);

        callPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Preview.class);
                startActivity(intent);
            }
        });
        callSettings=findViewById(R.id.btnSettings);

        callSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SettingsActivity.class);
                startActivity(intent);
            }
        });


        callContact=findViewById(R.id.ContactUs);

        callContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,ContactUs.class);
                startActivity(intent);
            }
        });


        callSignUp=findViewById(R.id.signup_screen);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,SignUp.class);
                startActivity(intent);

            }



        });



    }
    private boolean validateUsername(){
        String user = etUsername.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(getApplicationContext(),"Invalid Input: Username must not be Empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        String pass = etPassword.getText().toString();
        if(pass.length()<8){
            Toast.makeText(getApplicationContext(),"Invalid input: Password must be over 8 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public static User getLoggedUser(){
        return Login.LOGGED_User;
    }
}