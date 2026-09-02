package com.carrental.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carrental.Cars.Car;

@Service
public interface rentalService {

    public List<Car> listOfCars();
    
    public Optional<Car> GetaCarfromPlatenum(String platenum);

    public void rent(String Platenum, boolean startorend) throws Exception;

    public void rent(String Platenum, boolean startorEnd, Date dates) throws Exception;


}
