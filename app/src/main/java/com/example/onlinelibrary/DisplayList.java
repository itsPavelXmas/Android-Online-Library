package com.example.onlinelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onlinelibrary.Adapters.SaleAdapter;
import com.example.onlinelibrary.database.DAOs.SaleDAO;
import com.example.onlinelibrary.database.DatabaseInstance;
import com.example.onlinelibrary.database.services.SaleService;
import com.example.onlinelibrary.model.Sale;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

public class DisplayList extends AppCompatActivity {


    private ListView listView;
    //public static String PREFERENCES_FILE_NAME = "salePrefFILE";
    private SaleAdapter saleAdapter;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        listView=findViewById(R.id.lvSale);


        DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(DisplayList.this);
        final SaleDAO saleDAO = databaseInstance.getSaleDao();
        final SaleService saleService = new SaleService(saleDAO);

        saleAdapter = new SaleAdapter(getApplicationContext(),saleService.getSales());
        listView.setAdapter(saleAdapter);

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Sale sale = (Sale) saleAdapter.getItem(position);
                final AlertDialog.Builder builder=new AlertDialog.Builder(DisplayList.this);

                if(Login.getLoggedUser().getEmail().equals(sale.getUserEmail())){
                    builder.setMessage("What would you like to do with this book?").setCancelable(true).setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(DisplayList.this,EditBook.class);
                                intent.putExtra("edit",sale);
                                startActivity(intent);
                        }
                    }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseInstance databaseInstance1=DatabaseInstance.getINSTANCE(DisplayList.this);
                            SaleDAO saleDAO1=databaseInstance1.getSaleDao();
                            SaleService saleService1=new SaleService(saleDAO1);
                            saleService1.deleteSale(sale);

                            saleAdapter.deleteBookSale(position);
                            saleAdapter.notifyDataSetChanged();
                        }
                    });
                }
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });







    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.externalfilesmenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        DatabaseInstance databaseInstance = DatabaseInstance.getINSTANCE(DisplayList.this);
        SaleDAO saleDAO = databaseInstance.getSaleDao();
        SaleService saleService = new SaleService(saleDAO);
        List<Sale> sales = saleService.getUserSales(Login.getLoggedUser().getEmail());

        switch (item.getItemId()){
            case R.id.TXT:
            {
                FileOutputStream fileOutputStream = null;
                OutputStreamWriter outputStreamWriter = null;

                try {
                    fileOutputStream = openFileOutput(Login.getLoggedUser().getUserName()+" SalesBook.txt",MODE_PRIVATE);
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    for(Sale sale: sales){
                        outputStreamWriter.write(sale.getBookName());
                        outputStreamWriter.write("\n");
                        outputStreamWriter.write(sale.getAuthorName());
                        outputStreamWriter.write("\n");

                        outputStreamWriter.write(sale.getBookPrice().toString());
                        outputStreamWriter.write("\n");
                        outputStreamWriter.write(sale.getBookCondition().toString());
                        outputStreamWriter.write("\n");
                        outputStreamWriter.write(sale.getPaymentMethod());
                        outputStreamWriter.write("\n");
                        outputStreamWriter.write(sale.getEndingDate().toString());
                        outputStreamWriter.write("\n");
                        if(sale.getAuthorSigned()){
                            outputStreamWriter.write("Book is Signed");
                        }else{
                            outputStreamWriter.write("Book is not Signed");
                        }

                        outputStreamWriter.write("\n\n");
                    }
                    outputStreamWriter.flush();
                    Toast.makeText(DisplayList.this, "Saved to TXT!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(outputStreamWriter != null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(fileOutputStream != null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                return true;
            }

            case R.id.CSV:{

                FileOutputStream fileOutputStream = null;
                OutputStreamWriter  outputStreamWriter = null;

                try {
                    fileOutputStream = openFileOutput(Login.getLoggedUser().getUserName()+ " Books.csv",MODE_PRIVATE);
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    for(Sale sale: sales){
                        outputStreamWriter.write(sale.getBookName());
                        outputStreamWriter.write(",");
                        outputStreamWriter.write(sale.getAuthorName());
                        outputStreamWriter.write(",");
                        outputStreamWriter.write(sale.getBookPrice().toString());
                        outputStreamWriter.write(",");
                        outputStreamWriter.write(sale.getBookCondition().toString());
                        outputStreamWriter.write(",");
                        outputStreamWriter.write(sale.getPaymentMethod());
                        outputStreamWriter.write(",");
                        outputStreamWriter.write(sale.getEndingDate().toString());
                        outputStreamWriter.write(",");
                        if(sale.getAuthorSigned()){
                            outputStreamWriter.write("Book is Signed");
                        }else{
                            outputStreamWriter.write("Book is not Signed");
                        }

                        outputStreamWriter.write("\n");
                    }
                    outputStreamWriter.flush();
                    Toast.makeText(DisplayList.this, "Saved To CSV", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(outputStreamWriter != null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(fileOutputStream != null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DisplayList.this, MainMenu.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}