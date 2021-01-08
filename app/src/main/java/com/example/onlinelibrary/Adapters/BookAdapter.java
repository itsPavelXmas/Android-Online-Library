package com.example.onlinelibrary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.onlinelibrary.R;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.Sale;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BookAdapter extends BaseAdapter {


    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookHolder bookHolder;

        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.lvbookbuy,parent,false);
            bookHolder = new BookHolder(convertView);
            convertView.setTag(bookHolder);
        }
        else{
            bookHolder = (BookHolder) convertView.getTag();
        }

        Book item = (Book) getItem(position);
        bookHolder.title.setText(item.getTitle());
        bookHolder.author.setText(item.getAuthor());
        bookHolder.phoneNumber.setText("Phone Number: " + item.getPhoneNumber().toString());
        bookHolder.IBN.setText("ISBN: " + item.getIBN().toString());




        return convertView;
    }


    public class BookHolder{
        private TextView title;
        private TextView author;
        private TextView phoneNumber;
        private TextView IBN;

        public BookHolder(View convertView){
            title=convertView.findViewById(R.id.tvTitle);
            author=convertView.findViewById(R.id.tvAuthor);
            phoneNumber=convertView.findViewById(R.id.tvPhoneNumber);
            IBN=convertView.findViewById(R.id.tvIBN);

        }

    }
}
