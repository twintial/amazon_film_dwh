package com.example.dwh.service;

import com.example.dwh.hive.HiveSqlMapper;
import com.example.dwh.mysql.MySQLSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SqlService {
//    @Autowired
//    HiveSqlMapper hiveSqlMapper;
    @Autowired
    MySQLSqlMapper mySQLSqlMapper;

    public Model queryWithSql(String sql, Model model){
//        List<Map<String, Object>> results = hiveSqlMapper.queryWithSql(sql);
        List<LinkedHashMap<String, Object>> results = mySQLSqlMapper.queryWithSql(sql);
        List<String> attrs = new ArrayList<>();
        List<List<Object>> object = new ArrayList<>();
        if (results.size() < 1){
            model.addAttribute("attrs", attrs);
            model.addAttribute("object", object);
            return model;
        }
        Map<String, Object> r1 = results.get(0);
        attrs.addAll(r1.keySet());
        for (Map<String, Object> r : results){
            object.add(new ArrayList<>(r.values()));
        }
        model.addAttribute("attrs", attrs);
        model.addAttribute("object", object);
        return model;
    }
}
