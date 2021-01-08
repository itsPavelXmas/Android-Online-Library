package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.onlinelibrary.database.DAOs.UserDao;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.UserService;
import com.example.onlinelibrary.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    //Initialize Vars

    private ArrayList<GenreItem> mGenreList;
    private GenreAdapter mAdapter;

    //TextInputLayout
private TextInputEditText etFullname;
private TextInputEditText etUserName;
private TextInputEditText etEmail;
private TextInputEditText etPhone;
private TextInputEditText etPassword;
private Switch swNewsletter;
private Button btnSignUp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        initList();

        etFullname=findViewById(R.id.etFullNameSignUp);
        etUserName=findViewById(R.id.etUserNameSignUp);
        etEmail=findViewById(R.id.etEmailSignUp);
        etPhone=findViewById(R.id.etPhoneSignUp);
        etPassword=findViewById(R.id.etPasswordSignUp);
        swNewsletter=findViewById(R.id.swNewsLetterSignUp);
        btnSignUp=findViewById(R.id.btnSaveSignUp);



        Spinner spinnerGenres = findViewById(R.id.spinner_genres);
        mAdapter = new GenreAdapter(this, mGenreList);
        spinnerGenres.setAdapter(mAdapter);

        spinnerGenres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GenreItem clickedItem = (GenreItem) parent.getItemAtPosition(position);
                String clickedGenreName = clickedItem.getGenreName();
                Toast.makeText(SignUp.this, clickedGenreName + " Favorite Genre", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(validateFullName() && validateUsername() && validateEmail() && validatePhoneNumber() && validatePassword()){

                   String fullName=etFullname.getText().toString();
                   String userName=etUserName.getText().toString();
                   String email=etEmail.getText().toString();
                   String phone=etPhone.getText().toString();
                   String password=etPassword.getText().toString();
                   Boolean news=swNewsletter.isChecked();

                   User user= new User(fullName,userName,email,phone,password,news);

                   DatabaseInstance databaseInstance= DatabaseInstance.getINSTANCE(SignUp.this);
                   UserDao userDao=databaseInstance.getUserDao();
                   UserService userService=new UserService(userDao);

                   User existingUser=userService.getUser(email);
                   if(existingUser != null)

                   {
                       Toast.makeText(getApplicationContext(),"Error: There is an account with the same email",Toast.LENGTH_SHORT).show();


                   }
                   else
                       {
                       userService.addUser(user);
                       Intent intent= new Intent(getApplicationContext(),Login.class);
                       startActivity(intent);

                       etFullname.setText(null);
                       etUserName.setText(null);
                       etEmail.setText(null);
                       etPhone.setText(null);
                       etPassword.setText(null);
                       swNewsletter.setChecked(false);
                   }


               }
           }
       });


    }














    private void initList(){
        mGenreList=new ArrayList<>();
        mGenreList.add(new GenreItem("Fantasy",R.drawable.fantasy));
        mGenreList.add(new GenreItem("History",R.drawable.history));
        mGenreList.add(new GenreItem("Galaxy",R.drawable.galaxy));

    }


    //Validation Functions for the fields
    private boolean validateFullName() {
        String name = etFullname.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "INVALID INPUT: FULLNAME MUST NOT BE EMPTY", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validateUsername(){

        String user = etUserName.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(getApplicationContext(),"Invalid Input: username must not be empty!" , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateEmail(){
        String email = etEmail.getText().toString();
        if(email.isEmpty() || !email.contains("@gmail.com")){
            Toast.makeText(getApplicationContext(),"Invalid Input: email must not be empty and must have a valid format -@gmail.com-", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(){
        String phoneNumber = etPhone.getText().toString();
        if(phoneNumber.length()!=10){
            Toast.makeText(getApplicationContext(), "Invalid Input: phone number must contain 10 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        String password = etPassword.getText().toString();
        if(password.length()<8){
            Toast.makeText(getApplicationContext(),"invalid Input: password must be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }








}