package com.example.onlinelibrary;

public class GenreItem {
    private String mGenreName;
    private int mIconImage;

    public GenreItem(String GenreName, int IconImage){
        mGenreName=GenreName;
        mIconImage=IconImage;
    }

    public String getGenreName(){
        return mGenreName;
    }

    public int getmIconImage(){
        return mIconImage;
    }
}
