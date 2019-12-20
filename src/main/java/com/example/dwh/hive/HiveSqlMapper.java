package com.example.dwh.hive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class HiveSqlMapper {
    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    public List<Map<String, Object>> queryWithSql(String sql, Model model){
        double startTime = System.nanoTime();
        List<Map<String, Object>> results = hiveDruidTemplate.queryForList(sql);
        double endTime = System.nanoTime();
        double time =(endTime-startTime)/1000000000;
        model.addAttribute("hiveTime", time);
        model.addAttribute("hiveCount", results.size());
        return results;
    }
}
