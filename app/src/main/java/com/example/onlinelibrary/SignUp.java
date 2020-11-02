package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    //Initialize Vars
TextInputLayout RegName,RegUsername,RegEmail,RegPassword,RegPhoneNo;
Button btSubmit;
AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);




        //Assign Variables

        RegName=findViewById(R.id.name);
        RegUsername=findViewById(R.id.username);
        RegEmail=findViewById(R.id.email);
        RegPhoneNo=findViewById(R.id.phoneNo);
        RegPassword=findViewById(R.id.password);
        btSubmit=findViewById(R.id.submit);

        //Initialize Validation style
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation for Name
        awesomeValidation.addValidation(this,R.id.name,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.username,
                RegexTemplate.NOT_EMPTY,R.string.invalid_username);

        awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        awesomeValidation.addValidation(this,R.id.phoneNo, "[5-9]{1}[0-9]{9}$",R.string.invalid_phone);

        awesomeValidation.addValidation(this,R.id.password, ".{6,}",R.string.invalid_password);

        btSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Check Validation
                if (awesomeValidation.validate()) {
                    //On Success
                    Toast.makeText(getApplicationContext(), "Form Validate Succesfully..", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Validation Failed", Toast.LENGTH_SHORT).show();
                }
            }




        });













}}