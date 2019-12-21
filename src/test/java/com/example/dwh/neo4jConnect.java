package com.example.dwh;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.neo4j.driver.v1.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
public class neo4jConnect {

    public List<Map<String, Object>> toMap(List resultList){
        List<Map<String, Object>> result = new ArrayList<>();
        HashMap<String, Object> object;
        for (Object a : resultList){
            object = new HashMap<>();
            Record record = (Record) a;
            for (String key : record.keys()){
                List<String> dep2key = Lists.newArrayList(record.get(key).keys());
                if (dep2key.size() != 0){
                    for (String key2 : dep2key){
                        object.put(key2, record.get(key).get(key2));
                    }
                }else {
                    object.put(key, record.get(key));
                }
            }
            result.add(object);
        }
        return result;
    }
    @org.junit.Test
    public void main3(){
        System.out.println("!!");
        Driver driver = GraphDatabase.driver( "bolt://192.168.3.32:7687", AuthTokens.basic( "neo4j", "123" ) );
        Session session = driver.session();
        System.out.println("!");
        StatementResult result = session.run("match (x:Movie)-[:Belong]->()<-[:Belong]-(p:Movie{title:\"Spider-Man\"}) return x,p.title");
        List resultList = result.list();
        log.info(String.valueOf(resultList.size()));
        log.info("start");
        List<Map<String, Object>> l = toMap(resultList);
        System.out.println(l);
//        while ( result.hasNext() )
//        {
//            Record record = result.next();
//            System.out.println( record.values() );
//        }
//        List<String> keys = Lists.newArrayList(((Record)resultList.get(0)).keys());
//        for (String key : keys){
//            log.info(key);
//        }

//        List<String> keys = Lists.newArrayList(((Record)resultList.get(0)).get('x').keys());


//        for (Object a : resultList) {
//            Record r = (Record) a;
//            System.out.println(r.asMap());
//        }
        log.info("end");
        session.close();
        driver.close();
    }
}