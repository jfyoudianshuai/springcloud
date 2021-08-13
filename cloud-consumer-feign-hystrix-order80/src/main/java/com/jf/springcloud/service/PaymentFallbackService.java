package com.jf.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "Payment FallbackService paymentInfo_OK O(∩_∩)O哈哈~ ";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "Payment FallbackService paymentInfo_TimeOut ┭┮﹏┭┮ ";
    }
}
