package com.chen.test.controllers;

import com.chen.test.entity.TestPhoto;
import com.chen.test.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService ts;

    @RequestMapping( value="/one",method = RequestMethod.GET)
    public Object testO(HttpServletRequest request){
        try{
            String id=request.getParameter("id");

            TestPhoto testPhoto = ts.testCx(id);

            return testPhoto;
        }catch(Exception e){
            e.printStackTrace();
            return "失败了";
        }

    }
}
