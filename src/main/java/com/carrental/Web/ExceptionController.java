package com.carrental.Web;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carrental.Exceptions.AlreadyRentedException;
import com.carrental.Exceptions.EmptyDBException;
import com.carrental.Exceptions.NoSuchCarException;

@RestControllerAdvice 
public class ExceptionController extends ResponseEntityExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchCarException.class)
    ResponseEntity<String> handleNoSuchCarException(
        NoSuchCarException e, WebRequest req
    ){
        Logger.getLogger("RentalService").log(Level.WARNING,e.getMessage(),e);
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyDBException.class)
    ResponseEntity<String> handleEmptyDBException(EmptyDBException e, WebRequest req){
        Logger.getLogger("RentalService").log(Level.WARNING,e.getMessage(),e);
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(AlreadyRentedException.class)
    ResponseEntity<String> handleAlreadyRentedException(AlreadyRentedException e, WebRequest req){
        Logger.getLogger("RentalService").log(Level.WARNING,e.getMessage(),e);
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
    }

}
