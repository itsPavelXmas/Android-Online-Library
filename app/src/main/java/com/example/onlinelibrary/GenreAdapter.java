package com.example.onlinelibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GenreAdapter extends ArrayAdapter<GenreItem> {

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public GenreAdapter(Context context, ArrayList<GenreItem>genreList){
        super(context,0,genreList);

    }

    private View initView(int position,  View convertView, ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.genres_spinner_row,parent,false);
        }
        ImageView imageViewGenre=convertView.findViewById(R.id.image_view_genre);
        TextView textViewName= convertView.findViewById(R.id.text_view_name);

        GenreItem currentItem=getItem(position);
if(currentItem!=null) {
    imageViewGenre.setImageResource(currentItem.getmIconImage());
    textViewName.setText(currentItem.getGenreName());

}
        return convertView;
    }
}
