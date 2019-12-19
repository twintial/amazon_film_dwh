package com.example.dwh.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
@Mapper
public interface MySQLSqlMapper {
    List<LinkedHashMap<String, Object>> queryWithSql(@Param("sql") String sql);
    List<String> test();
}
