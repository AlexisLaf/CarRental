package com.carrental.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carrental.Service.BasicService;

@CrossOrigin 
@RestController
public class BasicController {
    
    @Autowired
    BasicService baseService;

    @GetMapping("/")
    public String hello() {
        return baseService.hello();
    }
}
