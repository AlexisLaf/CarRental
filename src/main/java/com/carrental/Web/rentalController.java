package com.carrental.Web;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.carrental.Cars.Car;
import com.carrental.Service.rentalService;

public class rentalController {

    @Autowired
    rentalService RentService;

    
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> listOfCars(){
        List<Car> carlist = RentService.listOfCars();
        if (carlist.isEmpty()){
            //Handle special exception :
            //Add some text to explain that there are no cars registered.
        }
        return carlist;
    }

    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception{
        Optional<Car> maycar = RentService.GetaCarfromPlatenum(plateNumber);
        if (maycar.isPresent()){
            return maycar.get();
        } else {
            //Handle noSuchCar Exception in http response.
            throw new Exception("To be replaced");
        }
    }

    @PutMapping(value = "/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrGetBack(
        @PathVariable("plateNumber") String plateNumber,
        @RequestParam(value="rent", required = true)boolean rent) throws Exception{
            //To verify params and more.
            RentService.rent(plateNumber, rent);
            //To handle exceptions thrown lower.
    }

    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(
        @PathVariable("plateNumber") String plateNumber,
        @RequestParam(value="rent", required = true)boolean rent,
        @RequestBody Date dates) throws Exception{
            //To verify params and more.
            RentService.rent(plateNumber, rent, dates);
            //To handle exceptions thrown lower.
    }

}
