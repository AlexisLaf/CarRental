package com.carrental.Cars;

import java.time.Duration;
import java.time.LocalDateTime;

public record Rents(LocalDateTime startDate,LocalDateTime endDate){
    
    public Duration rentTime(){
        return Duration.between(startDate,endDate);
    }
}
