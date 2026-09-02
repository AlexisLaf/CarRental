package com.carrental.Cars;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;

import com.carrental.utils.TimeConversion;

public class Car {
    String platenum;
    String brand;
    Integer price;
    boolean rented = false;
    ArrayList<Rents> RentsTimes; 

    Comparator<Rents> sortRentTimes = (r1,r2) -> {
        if (r1.endDate().isBefore(r2.endDate())){
            return 1;
        } else {
            return -1;
        }
    };

    public Car(String platenum, String brand, Integer price){
        this.platenum=platenum;
        this.brand=brand;
        this.price=price;

        this.RentsTimes = new ArrayList<Rents>();
    }

    public void rentCar(Rents rent_time){
        this.rented = true;
        this.RentsTimes.add(rent_time);
    }

    public void rentCar(LocalDateTime rentStart, LocalDateTime rentEnd){
        this.rented=true;
        this.RentsTimes.add(new Rents(rentStart,rentEnd));
    }

    public void EndRent(){
        Logger rentlog = Logger.getGlobal();
        this.rented=false;
        if (lastRent().endDate().isAfter(TimeConversion.toLocalDateTime(Instant.now()))){;
            rentlog.info("The Car "+this.platenum+" has been returned safely."+
            "\nIt will be available for rent after the "+lastRentEndTime()+".");
        } else {
            rentlog.info("The Car "+this.platenum+" has been returned late."+
            "\nIt can be rented immediately.");
        }
    }

    private Rents lastRent(){
        this.RentsTimes.sort(sortRentTimes);//Should be unecessary, but i will keep it in to be sure there are no issues.
        return RentsTimes.getLast();
    }

    private String lastRentEndTime(){
        return lastRent().endDate().toString(); 
    }

    public String GetPlanenum(){
        return this.platenum;
    }
}
