package com.carrental.Cars;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carrental.utils.TimeConversion;

/**
 * Or Car Factory?
 */
public class CarManager {
    List<Car> Registered_Cars;

    public CarManager(){
        this.Registered_Cars= new ArrayList<Car>();
    }

    public void NewCar(String platenum,String brand,Integer price){
        this.Registered_Cars.add(new Car(platenum, brand, price));
    }

    public Optional<Car> getCar(String Platenumber){
        return this.Registered_Cars.stream().filter(Car -> Car.GetPlanenum().equals(Platenumber)).findFirst();
    }

    public List<Car> getList(){
        return this.Registered_Cars;
    }

    public void RentaCar(String Platenumber) throws Exception{
        Optional<Car> rented = getCar(Platenumber);
        if (rented.isPresent()){
            rentCar(rented.get(), TimeConversion.toLocalDateTime(Instant.now()), TimeConversion.toLocalDateTime(Instant.now().plusSeconds(30*24*3600)));
        } else {
            throw new Exception("To be changed.");
        }
    }

    public void RentaCar(String Platenumber, Date startdate) throws Exception{
        Optional<Car> rented = getCar(Platenumber);
        if (rented.isPresent()){
            rentCar(rented.get(), TimeConversion.toLocalDateTime(startdate), TimeConversion.NewEndLocalDateTime(startdate));
        } else {
            throw new Exception("To be changed.");
        }
    }

    public void RentaCar(String Platenumber, Date startdate, Date endDate) throws Exception{
        Optional<Car> rented = getCar(Platenumber);
        if (rented.isPresent()){
            rentCar(rented.get(), TimeConversion.toLocalDateTime(startdate), TimeConversion.toLocalDateTime(endDate));
        } else {
            throw new Exception("To be changed.");
        }
    }

    private void rentCar(Car c, LocalDateTime start, LocalDateTime end){
        c.rentCar(start,end);
    }

    public void returnaCar(String Platenumber) throws Exception{
        Optional<Car> rented = getCar(Platenumber);
        if (rented.isPresent()){
            rented.get().EndRent();
        } else {
            throw new Exception("To be changed.");
        }
    }
}
