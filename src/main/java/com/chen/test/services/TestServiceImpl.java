package com.chen.test.services;

import com.chen.test.dao.TestDao;
import com.chen.test.entity.TestPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao td;

    @Override
    public TestPhoto testCx(String id) {
        TestPhoto testPhoto = td.testQuery(id);
        return testPhoto;
    }
}
