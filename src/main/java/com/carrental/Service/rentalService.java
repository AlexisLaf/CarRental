package com.carrental.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carrental.Cars.Car;
import com.carrental.Exceptions.AlreadyRentedException;
import com.carrental.Exceptions.NoSuchCarException;

@Service
public interface RentalService {

    public List<Car> listOfCars();
    
    public Optional<Car> GetaCarfromPlatenum(String platenum);

    public void rent(String Platenum, boolean startorend) throws NoSuchCarException, AlreadyRentedException;

    public void rent(String Platenum, boolean startorEnd, Date dates) throws NoSuchCarException, AlreadyRentedException;

    public void rent(String Platenum, boolean startorEnd, Date sdate, Date edate) throws NoSuchCarException, AlreadyRentedException;

}
