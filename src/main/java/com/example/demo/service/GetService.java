package com.example.demo.service;

import com.example.demo.util.FileUtil;
import com.example.demo.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description:
 * @Program: demo
 * @Author: zjx
 * @Create: 2022-02-16 10:44:50
 * @Version: 1.0
 **/
@Service
public class GetService {
    RequestUtil requestUtil;
    FileUtil fileUtil;
    Thread thread;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

    public GetService(RequestUtil requestUtil, FileUtil fileUtil){
        this.requestUtil = requestUtil;
        this.fileUtil = fileUtil;
        thread=new Thread(()->{
            while(true){
                Thread thread1 = new Thread(()->{
                    System.out.println("开始抓取flightaware");
                    getData();
                });
                Thread thread2 = new Thread(()->{
                    System.out.println("开始抓取adsbexchange");
                    getData2();
                });
                thread1.start();
//                thread2.start();
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * 获取数据
     * @return
     */
    public String getData(){
        String fileName = simpleDateFormat.format(new Date())+".txt";
        String path = "C:\\file\\flightaware";
        String url = "https://zh.flightaware.com/ajax/ignoreall/vicinity_airports.rvt?minLon=-180&minLat=-90&maxLon=180&maxLat=90";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String str = requestUtil.getRequest(url);
        stopWatch.stop();
        System.out.println("flightaware请求耗时"+stopWatch.getTotalTimeSeconds());
        fileUtil.saveFile(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)),path,fileName);
        return str;
    }

    public String getData2(){
        String cookie = getCookie();
//        String url1 = "https://globe.adsbexchange.com/globeRates.json?_="+new Date().getTime();
//        MultiValueMap<String,String> map1 = new LinkedMultiValueMap<>();
//        map1.add("cookie",cookie);
//        String str1 = requestUtil.getRequest(url1,map1);

        String fileName = simpleDateFormat.format(new Date())+".txt";
        String path = "C:\\file\\adsbexchange";
        String url = "https://globe.adsbexchange.com/data/globe_6235.binCraft";
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();

        map.add("authority","globe.adsbexchange.com");
        map.add("method","GET");
        map.add("path","/data/globe_6235.binCraft");
        map.add("scheme","https");
        map.add("accept","*/*");
        map.add("accept-encoding","gzip, deflate, br");
        map.add("accept-language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6,zh-TW;q=0.5");
        map.add("cookie","adsbx_sid=1645187603227_dl9144qbi1v; _ga=GA1.2.1967682560.1645014804; _gid=GA1.2.1624720220.1645014804; _gat_gtag_UA_69114603_1=1; __gads=ID=753ed832aabd7235-2277efdea6d000f8:T=1645014807:S=ALNI_MZDIoJDeTO27iIkXRkO4BEddLfaKg; cto_bundle=w4Z_jV9PVlJnRyUyRnZtb2VSODJiVDBsWXlrYVRZcyUyQmIlMkZsJTJCUk1TQzVpbyUyRnB3MzlDa2tiRE9aRHA4WGxrRHlkUTcxdlBTWmRSNkF2JTJGJTJCRlQ3NDVmVkgyNEZzTEVQNkN0andRenNuQWZBVSUyRkN0YXFHRno4YUJlT3hmb2hqJTJCQ3ZpT2JRY0UzZ29iSEtVbzM5QiUyRk1RNlR2S3NVaDVrdyUzRCUzRA");
//        map.add("cookie",cookie);
        map.add("if-none-match","\"620cc7a2-486\"");
        map.add("referer","https://globe.adsbexchange.com/");
        map.add("sec-ch-ua","\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Microsoft Edge\";v=\"98\"");
        map.add("sec-ch-ua-mobile","?0");
        map.add("sec-ch-ua-platform","\"Windows\"");
        map.add("sec-fetch-dest","empty");
        map.add("sec-fetch-mode","cors");
        map.add("sec-fetch-site","same-origin");
        map.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36 Edg/98.0.1108.50");
        map.add("x-requested-with","XMLHttpRequest");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String str = requestUtil.getRequest(url,map);
        stopWatch.stop();
        System.out.println("adsbexchange请求耗时"+stopWatch.getTotalTimeSeconds());
        fileUtil.saveFile(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)),path,fileName);
        return str;
    }

    public String getCookie(){
        long time = new Date().getTime();
        String cookieStr = "adsbx_sid="+time+2*86400*1000+"_"+getRandomStr(11)+";";
        return cookieStr;
    }

    public String getRandomStr(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
