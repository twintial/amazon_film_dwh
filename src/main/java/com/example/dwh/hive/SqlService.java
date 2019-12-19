package com.example.dwh.hive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SqlService {
    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    public Model queryWithSql(String sql, Model model){

        List<Map<String, Object>> results = hiveDruidTemplate.queryForList(sql);

        List<String> attr = new ArrayList<>();
        List<List<Object>> values = new ArrayList<>();
        if (results.size() < 1){
            model.addAttribute("attr", attr);
            model.addAttribute("values", values);
            return model;
        }
        Map<String, Object> r1 = results.get(0);
        attr.addAll(r1.keySet());
        for (Map<String, Object> r : results){
            values.add(new ArrayList<>(r.values()));
        }
        model.addAttribute("attr", attr);
        model.addAttribute("values", values);
        return model;
    }
}
