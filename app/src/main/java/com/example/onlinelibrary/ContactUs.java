package com.example.onlinelibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    int images[] = {R.drawable.facebook, R.drawable.whatsapp, R.drawable.twitter, R.drawable.instagram, R.drawable.youtube};
    //images set in array
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact_us);

        listView=findViewById(R.id.listView);

        MyAdapter adapter=new MyAdapter(this,mTitle,mDescription,images);
        listView.setAdapter(adapter);



        //set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){

                    Toast.makeText(ContactUs.this, "Facebook Description", Toast.LENGTH_SHORT).show();
                    Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pavel.craciun.7"));
                    startActivity(browserIntent);

                }
                if (position == 1){
                    Toast.makeText(ContactUs.this, "WhatsApp Description", Toast.LENGTH_SHORT).show();
                }
                if (position == 2){
                    Toast.makeText(ContactUs.this, "Twitter  Description", Toast.LENGTH_SHORT).show();
                }
                if (position == 3){
                    Toast.makeText(ContactUs.this, "Instagram Description", Toast.LENGTH_SHORT).show();

                }
                if (position == 4){
                    Toast.makeText(ContactUs.this, "Youtube  Description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{


        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]){
            super(c,R.layout.row,R.id.textView1,title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);


            return row;


        }
    }





}