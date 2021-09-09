package com.iwill.product.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AccountCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate ;

    private long userId ;

    private long amount ;

    public AccountCommand(RestTemplate restTemplate ,long userId , long amount) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.restTemplate = restTemplate ;
        this.userId = userId ;
        this.amount = amount ;
    }

    @Override
    protected String run() throws Exception {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        params.put("amount",amount);

        String url = "http://FUND-EUREKA-CLIENT/account/account/balance/{userId}/{amount}";
        String response = restTemplate.postForObject(url,null,String.class,params);
        return response ;
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }
}
