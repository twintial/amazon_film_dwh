package com.example.dwh;

import org.junit.runner.RunWith;
import org.neo4j.driver.v1.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class neo4jConnect {

    @org.junit.Test
    public void main3(){
        System.out.println("!!");
        Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "123" ) );
        Session session = driver.session();
        System.out.println("!");
        StatementResult result = session.run("MATCH (n:User) RETURN n.uID,n.profileName LIMIT 25");
        while ( result.hasNext() )
        {
            Record record = result.next();
            System.out.println( record.values() );
        }
        session.close();
        driver.close();
    }
}