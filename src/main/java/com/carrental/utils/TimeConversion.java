package com.carrental.utils;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeConversion {
    public static LocalDateTime toLocalDateTime(Date time){
        //if (time instanceof java.util.Date){
        //    return LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
        //} else 
        /* FOR SOME REASON, java.util.Date returned TRUE when called from the springboot generated java.sql.Date */
        if (time instanceof java.sql.Date){
            return time.toLocalDate().atStartOfDay();
        }
        throw new  RuntimeException("How tf did you call this function with something other than util and sql Date datatypes?? \nIt is of type:"+time.getClass());
    }

    public static Date toDate(LocalDateTime time){
        return Date.valueOf(time.toLocalDate());
        //Or for java.util.Date :
        //return Date.from(time.toInstant(null));
    }

    public static LocalDateTime NewEndLocalDateTime(Date time){
        //return LocalDateTime.ofInstant(time.toInstant().plusSeconds(30*24*3600), ZoneId.systemDefault());
        //Return the given date but with 1 month added.
        return time.toLocalDate().plusMonths(1).atStartOfDay();
    }

    public static LocalDateTime toLocalDateTime(Instant time){
        return LocalDateTime.ofInstant(time,ZoneId.systemDefault());
    }
}
