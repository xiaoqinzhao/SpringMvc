package com.example.newcoder.service;

import org.springframework.stereotype.Service;

@Service
public class wendaService {
    public String wenda(String name)
    {
        return "Hello !"+name;
    }
}
