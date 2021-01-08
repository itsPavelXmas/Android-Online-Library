package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.onlinelibrary.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;
    //Variables
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo,slogan;
    ProgressBar pgbar;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        pgbar=findViewById(R.id.progressBar);

        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView2);
        slogan=findViewById(R.id.textView4);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        final Timer t= new Timer();
        TimerTask tt=new TimerTask() {
            @Override
            public void run() {
                counter++;
                pgbar.setProgress(counter);
                if(counter == 10)
                    t.cancel();
            }

        };
        t.schedule(tt,0,10);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", "J.K. Rowling",0231010,236225));
        books.add(new Book("LOTR", "J.R.R. Tolkien",0223445,26422));
        books.add(new Book("Game of Thrones", "J.R. Martin",031452,2413));
        books.add(new Book("Star Wars ", "George Lucas",02372,25));
        JSONArray jsonArray = new JSONArray();
        for (Book bookie: books) {
            JSONObject jsonBooks = new JSONObject();
            try {
                jsonBooks.put("BookTitle", bookie.getTitle());
                jsonBooks.put("BookAuthor", bookie.getAuthor());
                jsonBooks.put("BookNumberPhone", bookie.getPhoneNumber());
                jsonBooks.put("BookIBN", bookie.getIBN());


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(jsonBooks);
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Craciun Pavel",jsonArray);

            JSONObject bookie = new JSONObject();
            bookie.put("Library",jsonArray);

            JSONObject sendJSONobj = new JSONObject();
            sendJSONobj.put("Craciun Pavel", bookie);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Jsoane", jsonObject.toString());



    }
}
