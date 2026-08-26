package com.carrental.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carrental.Service.basicService;

@RestController
public class basicController {
    
    @Autowired
    basicService baseService;

    @GetMapping("/")
    public String hello() {
        return baseService.hello();
    }
}
