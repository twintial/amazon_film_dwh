package com.example.dwh.service;

import com.example.dwh.hive.HiveSqlMapper;
import com.example.dwh.mysql.MySQLSqlMapper;
import com.example.dwh.neo4j.Neo4jQuery;
import com.example.dwh.neo4j.NeoResult;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Slf4j
@Service
public class SqlService {
    @Autowired
    HiveSqlMapper hiveSqlMapper;
    @Autowired
    MySQLSqlMapper mySQLSqlMapper;
    @Autowired
    Neo4jQuery neo4jQuery;

    public void addResultToModel(List<Map<String, Object>> result, Model model){
        List<String> attrs = new ArrayList<>();
        List<List<Object>> object = new ArrayList<>();
        if (result.size() < 1){
            model.addAttribute("attrs", attrs);
            model.addAttribute("object", object);
            return;
        }
        Map<String, Object> r1 = result.get(0);
        attrs.addAll(r1.keySet());
        for (Map<String, Object> r : result){
            object.add(new ArrayList<>(r.values()));
        }
        model.addAttribute("attrs", attrs);
        model.addAttribute("object", object);
    }

    public Model queryWithSql(String sql,String cypher, Model model){

        double startTime = System.nanoTime();
//        List<LinkedHashMap<String, Object>> mysqlResults = mySQLSqlMapper.queryWithSql(sql);
        List<LinkedHashMap<String, Object>> mysqlResults=new ArrayList<>();
        double endTime = System.nanoTime();
        double mysqlTime =(endTime-startTime)/1000000000;
        model.addAttribute("mysqlTime", mysqlTime);
        model.addAttribute("mysqlCount", mysqlResults.size());

        model.addAttribute("hiveTime", mysqlTime);
        model.addAttribute("hiveCount", mysqlResults.size());

        List<Map<String, Object>> neo4jResult = neo4jQuery.query(cypher, model);
        addResultToModel(neo4jResult, model);
        System.out.println(model.asMap());
//        List<Map<String, Object>> hiveResults = hiveSqlMapper.queryWithSql(sql, model);
//


        return model;
    }
}
