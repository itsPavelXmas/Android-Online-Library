package com.example.onlinelibrary.utils;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long timestamp){
        return timestamp != null ? new Date(timestamp):null;
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date!=null? date.getTime() : null;
    }
}
