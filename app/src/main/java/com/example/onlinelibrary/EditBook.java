package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.onlinelibrary.database.DAOs.SaleDAO;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.SaleService;
import com.example.onlinelibrary.model.Sale;

public class EditBook extends AppCompatActivity {

    private EditText eddPrice;
    private Spinner spSpinner;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        eddPrice=findViewById(R.id.edPrice);
        spSpinner=findViewById(R.id.spinnerPay);
        btnSave=findViewById(R.id.btnSaveChanges);


        Intent intent=getIntent();
        final Sale sale = intent.getParcelableExtra("edit");

        eddPrice.setText(sale.getBookName());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =eddPrice.getText().toString();
                String pay = spSpinner.getSelectedItem().toString();

                sale.setPaymentMethod(pay);
                sale.setBookName(name);

                DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(EditBook.this);
                SaleDAO saleDao = databaseInstance.getSaleDao();
                SaleService saleService = new SaleService(saleDao);

                saleService.updateSale(sale);

                Intent intent1 = new Intent(EditBook.this, DisplayList.class);
                startActivity(intent1);
            }
        });
    }
}