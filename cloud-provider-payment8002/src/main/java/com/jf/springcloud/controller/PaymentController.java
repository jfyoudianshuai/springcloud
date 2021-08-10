package com.jf.springcloud.controller;

import com.jf.springcloud.entities.CommonResult;
import com.jf.springcloud.entities.Payment;
import com.jf.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/creat")
    public CommonResult<Payment> create(@RequestBody Payment payment){

        int result=paymentService.create(payment);
        log.info("******插入结果"+result);
        if (result>0){
            return new CommonResult(200,"success: server.port :"+serverPort,result);
        }else {
            return new CommonResult(400,"fail",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("******查询结果"+payment);
        if(payment!=null) {
            return new CommonResult(200, "success,serverPort:"+serverPort, payment);
        }else {
            return new CommonResult(400,"fail",null);
        }
    }
    @GetMapping("/payment/lb")
    public  String getPaymentLB(){
        return serverPort;
    }
    @GetMapping("/payment/feign")
    public String paymentFeign(){
        return serverPort;
    }
}
