package com.batch.springbatch.model;

import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
//    int insert(Person Person);
//    int updateIgnoreNullById(@Param("student") Student student);
    Person selectById(@Param("person_id") int personId);
    
    int deleteById(@Param("person_id") int personId);
}