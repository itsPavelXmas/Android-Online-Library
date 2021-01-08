package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.onlinelibrary.database.DAOs.SaleDAO;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.SaleService;
import com.example.onlinelibrary.model.Sale;
import com.example.onlinelibrary.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddSaleActivity extends AppCompatActivity {

    private TextInputEditText bookName;
    private TextInputEditText authorName;
    private TextInputEditText bookPrice;
    private RatingBar condition;
    private Spinner payment;
    private EditText dateLimit;
    private CheckBox isAuthorSigned;
    private Button btnAddNewSale;

    private final Calendar calendar=Calendar.getInstance();


    private void updateLabel(){

        String format = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        dateLimit.setText(simpleDateFormat.format(calendar.getTime()));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_sale);

        bookName=findViewById(R.id.etBookName);
        authorName=findViewById(R.id.etAuthorName);
        bookPrice=findViewById(R.id.etBookPrice);
        condition=findViewById(R.id.rbBookCondition);
        payment=findViewById(R.id.spPaymentMethod);
        dateLimit=findViewById(R.id.etAvailableDate);
        isAuthorSigned=findViewById(R.id.cbAuthentic);
        btnAddNewSale=findViewById(R.id.btnNewSaleAdd);

        Intent intent=getIntent();
        final User user=intent.getParcelableExtra("user");

        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dateLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddSaleActivity.this,dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnAddNewSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sale sale=new Sale();

                if(validateBookName() && validateBookPrice() && validateEndingDate()){
                    Date setter=null;

                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

                    try{
                        setter=simpleDateFormat.parse(dateLimit.getText().toString());
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    sale.setBookName(bookName.getText().toString());
                    sale.setAuthorName(authorName.getText().toString());
                    sale.setBookPrice(Double.parseDouble(bookPrice.getText().toString()));
                    sale.setBookCondition(Math.round(condition.getRating()));
                    sale.setPaymentMethod(payment.getSelectedItem().toString());
                    sale.setEndingDate(setter);
                    sale.setAuthorSigned(isAuthorSigned.isChecked());
                    sale.setUserEmail(Login.getLoggedUser().getEmail());


                    DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(AddSaleActivity.this);
                    SaleDAO saleDAO = databaseInstance.getSaleDao();
                    SaleService saleService = new SaleService(saleDAO);
                    saleService.addSale(sale);

                    Intent intent = new Intent(getApplicationContext(), DisplayList.class);

                    startActivity(intent);

                }

            }
        });


    }


    private Boolean validateBookName(){
        String name = bookName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(getApplicationContext(),"Empty Book name!" ,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Boolean validateBookPrice(){
        String price = bookPrice.getText().toString();
        if(price.isEmpty()){
            Toast.makeText(getApplicationContext(), "Empty Price!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    private Boolean validateEndingDate(){
        Date parsed = null ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateValue = dateLimit.getText().toString();
        try {
            parsed = dateFormat.parse(dateLimit.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(dateValue.isEmpty() || System.currentTimeMillis()>parsed.getTime()){
            Toast.makeText(getApplicationContext(), "Choose a future ending of the sale" , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}