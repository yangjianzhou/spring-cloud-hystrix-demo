package com.iwill.product.controller;

import com.iwill.product.command.AccountCommand;
import com.iwill.product.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private AccountService accountService ;

    @GetMapping("purchase/{userId}/{productId}/{amount}")
    public String purchaseProduct(@PathVariable("userId")long userId ,
                                  @PathVariable("productId")long productId ,
                                  @PathVariable("amount")long amount ,
                                  HttpServletRequest request){
        System.out.println("扣减产品余额");
        String response = accountService.deductAmount(userId ,amount);
        System.out.println("请求fund扣款结果:"+response);
        System.out.println("记录交易信息");
        return "success";
    }

    @GetMapping("purchase/v1/{userId}/{productId}/{amount}")
    public String purchaseProductV1(@PathVariable("userId")long userId ,
                                  @PathVariable("productId")long productId ,
                                  @PathVariable("amount")long amount ,
                                  HttpServletRequest request){
        System.out.println("扣减产品余额");
        String response = accountService.deductAmountV1(userId ,amount);
        System.out.println("请求fund扣款结果:"+response);
        System.out.println("记录交易信息");
        return "success";
    }
}
