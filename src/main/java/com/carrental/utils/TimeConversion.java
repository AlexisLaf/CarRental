package com.carrental.utils;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeConversion {
    public static LocalDateTime toLocalDateTime(Date time){
        return LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime time){
        return Date.from(time.toInstant(null));
    }

    public static LocalDateTime NewEndLocalDateTime(Date time){
        return LocalDateTime.ofInstant(time.toInstant().plusSeconds(30*24*3600), ZoneId.systemDefault());
        //Return the given date but with 1 month added.
    }

    public static LocalDateTime toLocalDateTime(Instant time){
        return LocalDateTime.ofInstant(time,ZoneId.systemDefault());
    }
}
