package com.example.dwh.controller;


import com.example.dwh.mysql.MySQLSqlMapper;
import com.example.dwh.service.SqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
public class SQLController {
    @Autowired
    SqlService SqlService;
    @Autowired
    MySQLSqlMapper mySQLSqlMapper;
    @PostMapping("/sql")
    public String searchWithSQL(@RequestParam("sql") String sql, Model model){
        log.info(sql);
        model = SqlService.queryWithSql(sql, model);
        return "result";
    }

    @ResponseBody
    @GetMapping("/test")
    public List<String> test(){
        return mySQLSqlMapper.test();
    }
}
