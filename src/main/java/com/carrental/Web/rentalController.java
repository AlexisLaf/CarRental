package com.carrental.Web;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.Cars.Car;
import com.carrental.Exceptions.AlreadyRentedException;
import com.carrental.Exceptions.EmptyDBException;
import com.carrental.Exceptions.NoSuchCarException;
import com.carrental.Service.RentalService;


//There is no need to add a logger to this class as springboot by default logs requests and gives us the information.
@CrossOrigin 
@RestController
@RequestMapping("/cars")
public class RentalController {

    @Autowired
    RentalService rentService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> listOfCars() throws EmptyDBException{
        List<Car> carlist = rentService.listOfCars();
        if (carlist.isEmpty()){
            throw new EmptyDBException("The database does not contain any cars. There may be a connection error.");
        }
        return carlist;
    }

    @GetMapping("/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws NoSuchCarException{
        Optional<Car> maycar = rentService.GetaCarfromPlatenum(plateNumber);
        if (maycar.isPresent()){
            return maycar.get();
        } else {
            throw new NoSuchCarException("The Car you wished to see does not Exist, Did you put the right platenumber?");
        }
    }

    /* This class is an inner class that represents the body that can
     */
    private static class Rentrequestbody{
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start_date;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end_date;

        public Rentrequestbody(Date sdate, Date eDate){
            this.start_date = sdate;
            this.end_date = eDate;
        }

        public Date getEnd_date() {
            return end_date;
        }

        public Date getStart_date() {
            return start_date;
        }

        public void setStart_date(Date sdate){
            this.start_date = sdate;
        }
        public void setEnd_date(Date edate){
            this.start_date = edate;
        }
    }

    @PutMapping(value = "/{plateNumber}")
    public void rent(
        @PathVariable("plateNumber") String plateNumber,
        @RequestParam(value="rent", required = true)boolean rent,
        @RequestBody Rentrequestbody dates) throws NoSuchCarException, AlreadyRentedException {
            //Verification of the existence of the needed parameters.
            Logger.getLogger("RentRequestBodyTestController").info("Start_date :"+dates.start_date+" / End_date :"+dates.end_date);
            if (dates.start_date != null & dates.end_date != null){
                rentService.rent(plateNumber, rent, dates.start_date,dates.end_date);
            } else if (dates.start_date != null & dates.end_date == null){
                rentService.rent(plateNumber, rent, dates.start_date);
            } else {
                rentService.rent(plateNumber, rent);
            }
            //To handle exceptions thrown lower. -> Done in Exception controller.
    }

}
