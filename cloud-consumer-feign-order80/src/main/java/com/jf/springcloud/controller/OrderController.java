package com.jf.springcloud.controller;

import com.jf.springcloud.entities.CommonResult;
import com.jf.springcloud.entities.Payment;

import com.jf.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/feign")
    public String paymentFeign(){
        return paymentFeignService.paymentFeign();
    }
    @GetMapping("/consumer/payment/timeout")
    public String timeOut(){
        return paymentFeignService.timeOut();
    }
}
