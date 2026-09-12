package com.carrental.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.carrental.Cars.Car;
import com.carrental.Exceptions.AlreadyRentedException;
import com.carrental.Exceptions.NoSuchCarException;
import com.carrental.utils.TimeConversion;

@Service
public class RentalServiceImpl implements RentalService{
    List<Car> Registered_Cars;

    public RentalServiceImpl(){
        this.Registered_Cars= new ArrayList<Car>();
        Logger.getLogger("RentalService").info("The Registered_cars list has been created.");
        //Adding some basic cars to not have exceptions all the time.
        NewCar("A1B2", "Tesla", 45678.99);
        NewCar("A1B3", "Tesla", 99999.99);
        NewCar("A2V2", "BMW", 6900.36);
        NewCar("C20L", "Honda", 29900.80);
        NewCar("F8C9", "Ford", 34020.99);
        Logger.getLogger("RentalService").info("Added five different basic cars.");
    }

    public void NewCar(String platenum, String brand, Double price){
        this.Registered_Cars.add(new Car(platenum, brand, price));
    }

    public Optional<Car> GetaCarfromPlatenum(String Platenumber){
        return this.Registered_Cars.stream().filter(Car -> Car.getPlanenum().equals(Platenumber)).findFirst();
    }

    public List<Car> listOfCars(){
        Logger.getLogger("RentalService").fine("Fetched the list of cars.");
        return this.Registered_Cars;
    }

    private void rentCar(Car c, LocalDateTime start, LocalDateTime end) throws AlreadyRentedException {
        Logger.getLogger("RentalService").fine("Renting Car :"+c.getPlanenum()+" from "+start+" to "+end);
        c.rentCar(start,end);
    }

    public void returnaCar(String Platenumber) throws NoSuchCarException{
        Optional<Car> rented = GetaCarfromPlatenum(Platenumber);
        if (rented.isPresent()){
            rented.get().EndRent();
        } else {
            throw new NoSuchCarException("The Specified car does not exist.");
        }
    }

    public void rent(String Platenum, boolean StartOrEnd) throws NoSuchCarException, AlreadyRentedException{
        if (StartOrEnd) {//Start to rent a car (bool=1)
            Optional<Car> rented = GetaCarfromPlatenum(Platenum);
            if (rented.isPresent()){
                rentCar(rented.get(), TimeConversion.toLocalDateTime(Instant.now()), TimeConversion.toLocalDateTime(Instant.now().plusSeconds(30*24*3600)));
            } else {
                throw new NoSuchCarException("The Specified car does not exist.");
            }
        } else { //Return a Car (bool=0)
            returnaCar(Platenum);
        }
    }

    public void rent(String Platenum, boolean StartOrEnd, Date date) throws NoSuchCarException, AlreadyRentedException{
        if (StartOrEnd) {//Start to rent a car (bool=1)
            Optional<Car> rented = GetaCarfromPlatenum(Platenum);
            if (rented.isPresent()){
                rentCar(rented.get(), TimeConversion.toLocalDateTime(date), TimeConversion.NewEndLocalDateTime(date));
            } else {
                throw new NoSuchCarException("The Specified car does not exist.");
            }
        } else { //Return a Car (bool=0)
            returnaCar(Platenum);
        }
    }

    public void rent(String Platenum, boolean StartOrEnd, Date sdate, Date eDate) throws NoSuchCarException, AlreadyRentedException{
        if (StartOrEnd) {//Start to rent a car (bool=1)
            Optional<Car> rented = GetaCarfromPlatenum(Platenum);
            if (rented.isPresent()){
                rentCar(rented.get(), TimeConversion.toLocalDateTime(sdate), TimeConversion.toLocalDateTime(eDate));
            } else {
                throw new NoSuchCarException("The Specified car does not exist.");
            }
        } else { //Return a Car (bool=0)
            returnaCar(Platenum);
        }
    }
    
}
