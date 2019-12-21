package com.example.dwh.neo4j;

import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;

@Repository
public class Neo4jQuery {
    public NeoResult query(String cypher){
        NeoResult r= new NeoResult();
        System.out.println("开始连接neo4j数据库");
        Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "123" ) );
        Session session = driver.session();
        System.out.println("已经连接到neo4j数据库");
        double startTime2 = System.nanoTime();
        StatementResult result = session.run(cypher);
        double endTime2 = System.nanoTime();
        double mysqlTime2 =(endTime2-startTime2)/1000000000;
        long i=0;
        while ( result.hasNext() ) {
            i++;
            Record record = result.next();
            System.out.println(record.values());
        }
        session.close();
        driver.close();
        r.num=i;
        r.time=mysqlTime2;
        System.out.println("查询数量:"+r.num);
        System.out.println("查询时间:"+r.time);
        return r;
    }

}
