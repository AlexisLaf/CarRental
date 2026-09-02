package com.carrental.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carrental.Cars.Car;
import com.carrental.Cars.CarManager;

@Service
public class rentalServiceImpl implements rentalService{
    CarManager mng;

    public rentalServiceImpl(){
        this.mng = new CarManager();
    }

    public List<Car> listOfCars(){
        return mng.getList();
    }
    
    public Optional<Car> GetaCarfromPlatenum(String platenum){
        return mng.getCar(platenum);
    }

    public void rent(String Platenum, boolean StartOrEnd) throws Exception{
        if (StartOrEnd) {//Start to rent a car (bool=1)
            mng.RentaCar(Platenum);
        } else { //Return a Car (bool=0)
            mng.returnaCar(Platenum);
        }
    }

    public void rent(String Platenum, boolean StartOrEnd, Date dates) throws Exception{
        if (StartOrEnd) {//Start to rent a car (bool=1)
            mng.RentaCar(Platenum, dates);
        } else { //Return a Car (bool=0)
            mng.returnaCar(Platenum);
        }
    }

    
}
