package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Program: demo
 * @Author: zjx
 * @Create: 2022-02-16 10:54:27
 * @Version: 1.0
 **/
@Component
public class RequestUtil {
    @Autowired
    RestTemplate restTemplate;

    public String postRequest(String url, MultiValueMap<String,String> params){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(headers,params);
        ResponseEntity result = restTemplate.postForEntity(url, httpEntity,String.class);
        return result.getBody().toString();
    }

    public String getRequest(String url){

        ResponseEntity result = restTemplate.getForEntity(url,String.class);

        return  result.getBody().toString();
    }

    public String getRequest(String url, MultiValueMap<String,String> params){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (String key :params.keySet()) {
            headers.set(key,String.valueOf(params.get(key)));
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(null,headers);
        ResponseEntity result = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);
        return result.getBody().toString();
    }
}
