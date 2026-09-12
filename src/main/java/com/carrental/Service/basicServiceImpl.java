package com.carrental.Service;

import org.springframework.stereotype.Service;

@Service
public class BasicServiceImpl implements BasicService {
    
    public String hello(){
        return "Hello, World";
    }
}
