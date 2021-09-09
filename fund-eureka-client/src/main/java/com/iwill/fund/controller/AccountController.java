package com.iwill.fund.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("account")
public class AccountController {

    @PostMapping("account/balance/{userId}/{amount}")
    public String deductingBalance(@PathVariable("userId")long userId ,
                                   @PathVariable("amount")long amount ,
                                   HttpServletRequest request){
        System.out.println("端口："+ request.getServerPort() +"扣减成功");
        try{
            Thread.sleep(6000L);
        }catch (Exception exp){

        }
        return "success";
    }
}
