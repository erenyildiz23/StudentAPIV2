package com.student.studentapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }
}
