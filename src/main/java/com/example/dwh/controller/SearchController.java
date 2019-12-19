package com.example.dwh.controller;


import com.example.dwh.hive.SqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/search")
class SQLController {
    @Autowired
    SqlService sqlService;
    @PostMapping("/sql")
    public String searchWithSQL(@RequestParam("sql") String sql, Model model){
        model = sqlService.queryWithSql(sql, model);
        return "search";
    }
}
