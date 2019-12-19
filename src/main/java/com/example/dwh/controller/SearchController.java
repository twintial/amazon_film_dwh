package com.example.dwh.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/")
class SQLController {
    @PostMapping("/sql")
    public String searchWithSQL(@RequestParam("sql") String sql){
        return "search";
    }
}
