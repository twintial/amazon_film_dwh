package com.example.dwh.neo4j;

import com.google.common.collect.Lists;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class Neo4jQuery {
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

    public List<Map<String, Object>> query(String cypher, Model model){
        NeoResult r= new NeoResult();
        System.out.println("开始连接neo4j数据库");
        Driver driver = GraphDatabase.driver( "bolt://192.168.3.32:7687", AuthTokens.basic( "neo4j", "123" ) );
        Session session = driver.session();
        System.out.println("已经连接到neo4j数据库");
        double startTime2 = System.nanoTime();
        StatementResult result = session.run(cypher);
        double endTime2 = System.nanoTime();
        double neo4jTime2 =(endTime2-startTime2)/1000000000;
        List resultList = result.list();
//        long i=0;
//        while ( result.hasNext() ) {
//            i++;
//            Record record = result.next();
//            System.out.println(record.values());
//        }

        session.close();
        driver.close();
        model.addAttribute("neo4jTime", neo4jTime2);
        model.addAttribute("neo4jCount", resultList.size());


//        r.num=i;
//        r.time=neo4jTime2;
//        System.out.println("查询数量:"+r.num);
//        System.out.println("查询时间:"+r.time);
        return toMap(resultList);
    }

}
