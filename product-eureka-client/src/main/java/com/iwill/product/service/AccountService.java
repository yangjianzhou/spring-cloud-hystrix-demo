package com.iwill.product.service;

import com.iwill.product.command.AccountCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private RestTemplate restTemplate ;

    @HystrixCommand( fallbackMethod = "fallback",groupKey = "groupKey_1",commandKey = "commandKey_1" ,threadPoolKey = "threadPoolKey")
    public String deductAmount(long userId ,long amount){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        params.put("amount",amount);

        String url = "http://FUND-EUREKA-CLIENT/account/account/balance/{userId}/{amount}";
        String response = restTemplate.postForObject(url,null,String.class,params);
        return response ;
    }

    public String fallback(long userId ,long amount){
        System.out.println("userId =  "+userId + " ,amount = "+ amount);
        return "fallback" ;
    }

    public String deductAmountV1(long userId, long amount) {
        return new AccountCommand(restTemplate, userId, amount).execute();
    }
}
