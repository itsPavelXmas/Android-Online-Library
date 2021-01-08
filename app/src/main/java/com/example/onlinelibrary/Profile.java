package com.example.onlinelibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinelibrary.database.DAOs.SaleDAO;
import com.example.onlinelibrary.database.DAOs.UserDao;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.SaleService;
import com.example.onlinelibrary.database.services.UserService;

public class Profile extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvEmailAddr;
    private TextView tvPhoneNumber;
    private ImageView imageView;
    private CheckBox cbProfileNewsletter;
    private Button btnUedit;
    private Button btnDeleteUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tvUserName=findViewById(R.id.tvName);
        tvEmailAddr=findViewById(R.id.tvEmail);
        tvPhoneNumber=findViewById(R.id.tvPhone);
        imageView=findViewById(R.id.imageView);
        cbProfileNewsletter =findViewById(R.id.cbNewsLetter);
        btnUedit=findViewById(R.id.button10);
        btnDeleteUser=findViewById(R.id.btnDelete);

        tvUserName.setText("Hello, " + Login.getLoggedUser().getUserName());
        tvEmailAddr.setText("Email Address: " + Login.getLoggedUser().getEmail());
        tvPhoneNumber.setText("Phone Number:  " + Login.getLoggedUser().getPhoneNumber());
        if((Login.getLoggedUser().getNewsletter())){
            cbProfileNewsletter.setChecked(true);
        }

        btnUedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.getLoggedUser().setNewsletter(!Login.getLoggedUser().getNewsletter());
                DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(Profile.this);
                UserDao userDao = databaseInstance.getUserDao();
                UserService userService = new UserService(userDao);
                userService.updateUser(Login.getLoggedUser());
                finish();
                startActivity(getIntent());
            }
        });

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                builder.setMessage("Do you want to delete this user?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(Profile.this);
                        UserDao userDao = databaseInstance.getUserDao();// take from User Dao
                        SaleDAO saleDAO=databaseInstance.getSaleDao();//take from Sale Dao
                        UserService userService = new UserService(userDao);
                        SaleService saleService=new SaleService(saleDAO); //
                        saleService.deleteSaleByUserEmail(Login.getLoggedUser().getEmail());
                        userService.deleteUSer(Login.getLoggedUser());

                        Intent intent = new Intent(Profile.this, Login.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });





    }
}