package com.example.telegrambot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEndpoint {

    @PostMapping("/getok")
    private @ResponseBody
    HttpStatus getStatus(){
        return HttpStatus.OK;
    }
}
