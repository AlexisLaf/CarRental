package com.carrental.Service;

import org.springframework.stereotype.Service;

@Service
public class basicServiceImpl implements basicService {
    
    public String hello(){
        return "Hello, World";
    }
}
