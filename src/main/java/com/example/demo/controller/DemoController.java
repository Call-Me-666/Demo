package com.example.demo.controller;

import com.example.demo.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Program: demo
 * @Author: zjx
 * @Create: 2022-02-16 11:05:34
 * @Version: 1.0
 **/
@RestController
public class DemoController {

    @Autowired
    GetService getService;

//    @GetMapping("/get")
//    public String get(){
////        String str = getService.getData();
//        String str = getService.getData2();
//        return str;
//    }
}
