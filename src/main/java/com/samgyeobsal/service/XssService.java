package com.samgyeobsal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class XssService {
    public String stringTest(String input){
        return input;
    }
}