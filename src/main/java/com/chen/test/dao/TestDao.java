package com.chen.test.dao;

import com.chen.test.entity.TestPhoto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

    public TestPhoto testQuery(@Param("photoId")String id);
}
